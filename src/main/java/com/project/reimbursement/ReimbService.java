package com.project.reimbursement;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.common.ResourceCreationResponse;
import com.project.common.exceptions.InvalidRequestException;
import com.project.common.exceptions.ResourceNotFoundException;



@Service
public class ReimbService {

    private final ReimbRepository reimbRepo;

    @Autowired
    public ReimbService(ReimbRepository reimbRepo) {
        this.reimbRepo = reimbRepo;
    }

    public List<ReimbResponse> getAllReimbursements() {
        return reimbRepo.findAll()
                        .stream()
                        .map(ReimbResponse::new)
                        .collect(Collectors.toList());
    }

    public ReimbResponse getReimbById(String id) {
        try {
            return reimbRepo.findById(UUID.fromString(id))
                             .map(ReimbResponse::new)
                             .orElseThrow(ResourceNotFoundException::new);
        } catch (IllegalArgumentException e) {
            throw new InvalidRequestException("A valid uuid must be provided!");
        }
    }

    // Create a new reimbursement request
    public ResourceCreationResponse createNewRequest(NewRequest newRequest, UUID authorId) {

        Reimbursement insertRequest = newRequest.extractEntity();

        // Set the author id from the login user
        insertRequest.setAuthorId(authorId);

        // Set the submitted time to now
        insertRequest.setSubmitted(LocalDateTime.now());

        // Defaults the status to pending
        Status status = new Status();
        status.setStatusId(UUID.fromString("UUID for Pending")); // TODO add uuid for Pending 8-4-4-4-12
        status.setStatus("Pending");
        insertRequest.setStatusId(status);

        // Set the type.
        if (newRequest.getType().toUpperCase().equals("OTHER")){
            
            Type type = new Type();
            type.setTypeId(UUID.fromString("UUID for Other")); // TODO add uuid for Other 8-4-4-4-12
            type.setType("Other");
            insertRequest.setTypeId(type);
        }

        if (newRequest.getType().toUpperCase().equals("TRAVEL")){
            
            Type type = new Type();
            type.setTypeId(UUID.fromString("UUID for type Travel")); // TODO add uuid for Travel 8-4-4-4-12
            type.setType("Travel");
            insertRequest.setTypeId(type);
        }

        if (newRequest.getType().toUpperCase().equals("FOOD")){
            
            Type type = new Type();
            type.setTypeId(UUID.fromString("UUID for type Food")); // TODO add uuid for Food 8-4-4-4-12
            type.setType("Travel");
            insertRequest.setTypeId(type);
        }

        if (newRequest.getType().toUpperCase().equals("LODGING")){
            
            Type type = new Type();
            type.setTypeId(UUID.fromString("UUID for type Lodging")); // TODO add uuid for Lodging 8-4-4-4-12
            type.setType("Travel");
            insertRequest.setTypeId(type);
        }

        reimbRepo.save(insertRequest);

        // Return uuid for reimbursement request
        return new ResourceCreationResponse("ReimbID: " + insertRequest.getId());
    }

    // Update the status
    public void updateStatus(UpdateReimbRequest updateReimbRequest, UUID resolverId) {

        // Check if Reimbursement exists
        if(!reimbRepo.findReimbByReimbId(UUID.fromString(updateReimbRequest.getReimbId())).isPresent()) {
            throw new InvalidRequestException("Reimbursement not found.");
        }

        // TODO Apply the uuid for the status
        if (updateReimbRequest.getStatus().toUpperCase().equals("APPROVED")) {
            updateReimbRequest.setStatus("UUID for Approved"); // TODO add uuid for Approved 8-4-4-4-12
        }

        if (updateReimbRequest.getStatus().toUpperCase().equals("DENIED")) {
            updateReimbRequest.setStatus("UUID for Denied"); // TODO add uuid for Denied 8-4-4-4-12
        }

        LocalDateTime resolved = LocalDateTime.now();

        reimbRepo.setStatus(resolverId, resolved, UUID.fromString(updateReimbRequest.getStatus()), UUID.fromString(updateReimbRequest.getReimbId()));
    }
   
}
