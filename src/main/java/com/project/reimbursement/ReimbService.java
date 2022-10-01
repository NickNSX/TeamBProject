package com.project.reimbursement;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    // Get reimbursements by author's id
    public List<ReimbResponse> getAllReimbById  (UUID id) {

        List<ReimbResponse> result = new ArrayList<>();
        List<Reimbursement> reimb = reimbRepo.findReimbByAuthorId(id);

        if (reimb.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        for (Reimbursement r : reimb) {
            result.add(new ReimbResponse(r));
        }

        return result;
    }

    // Get all reimbursements
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

    // Get reimbursement by status
    public List<ReimbResponse> getAllReimbursementsByStatus(String status) {

        if (status.toUpperCase().equals("APPROVE")) {
            status = "UUID for Approve"; // TODO Enter UUID for Approve
        } else if (status.toUpperCase().equals("DENIED")) {
            status = "UUID for Deined"; // TODO Enter UUID for Denied
        } else if (status.toUpperCase().equals("PENDING")) {
            status = "UUID for Pending"; // TODO Enter UUID for Pending
        } else {
            throw new InvalidRequestException("Status must be Approve, Denied, or Pending");
        }
        
        List<ReimbResponse> result = new ArrayList<>();
        List<Reimbursement> reimb = reimbRepo.findByStatus(UUID.fromString(status));

        for (Reimbursement reimbursement : reimb) {
            result.add(new ReimbResponse(reimbursement));
        }

        if (result.isEmpty()) {
            throw new ResourceNotFoundException("No request found.");
        }

        return result;
    }

    // Create a new reimbursement request
    public ResourceCreationResponse createNewRequest(NewRequest newRequest, UUID authorId) {

        if (newRequest.getAmount() == 0 || newRequest.getAmount() > 10000 || newRequest.getDescription() == null || newRequest.getType() == null) {
            throw new InvalidRequestException("Must provide an amount between 0 and 10,000, a description, and a type.");
        }

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
            type.setTypeId(UUID.fromString("UUID for Travel")); // TODO add uuid for Travel 8-4-4-4-12
            type.setType("Travel");
            insertRequest.setTypeId(type);
        }

        if (newRequest.getType().toUpperCase().equals("FOOD")){
            
            Type type = new Type();
            type.setTypeId(UUID.fromString("UUID for Food")); // TODO add uuid for Food 8-4-4-4-12
            type.setType("Food");
            insertRequest.setTypeId(type);
        }

        if (newRequest.getType().toUpperCase().equals("LODGING")){
            
            Type type = new Type();
            type.setTypeId(UUID.fromString("UUID for Lodging")); // TODO add uuid for Lodging 8-4-4-4-12
            type.setType("Lodging");
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
        if(reimbRepo.findReimbByReimbId(UUID.fromString(updateReimbRequest.getReimbId())).get().getAuthorId().equals(resolverId)) {
            throw new InvalidRequestException("Can not change the status of own request.");
        }

        // TODO Apply the uuid for the status
        if (updateReimbRequest.getStatus().toUpperCase().equals("APPROVED")) {
            updateReimbRequest.setStatus("UUID for Approved"); // TODO add uuid for Approved 8-4-4-4-12
        } else if (updateReimbRequest.getStatus().toUpperCase().equals("DENIED")) {
            updateReimbRequest.setStatus("UUID for denied"); // TODO add uuid for Denied 8-4-4-4-12
        } else {
            throw new InvalidRequestException("Status not found. Enter 'Approved' or 'Denied'.");
        }

        LocalDateTime resolved = LocalDateTime.now();

        reimbRepo.setStatus(resolverId, resolved, UUID.fromString(updateReimbRequest.getStatus()), UUID.fromString(updateReimbRequest.getReimbId()));
    }

    public void updateReimb (UpdateReimbRequest updateReimbRequest) {

        if(!reimbRepo.findReimbByReimbId(UUID.fromString(updateReimbRequest.getReimbId())).isPresent()) {
            throw new ResourceNotFoundException("Reimbursement not found.");
        }
        if (!reimbRepo.findReimbByReimbId(UUID.fromString(updateReimbRequest.getReimbId())).get().getStatusId().getStatus().toUpperCase().equals("PENDING")) {
            throw new InvalidRequestException("Request is no longer Pending");
        }
        
        if (updateReimbRequest.getAmount() > 0 && updateReimbRequest.getAmount() < 10000) {
            reimbRepo.updateReimbAmount(updateReimbRequest.getAmount(), UUID.fromString(updateReimbRequest.getReimbId()));
        }
        if (updateReimbRequest.getDescription() != null) {
            reimbRepo.updateReimbDescription(updateReimbRequest.getDescription(), UUID.fromString(updateReimbRequest.getReimbId()));
        }
        if(updateReimbRequest.getType() != null) {
            if (updateReimbRequest.getType().toUpperCase().equals("LODGING")) {
                updateReimbRequest.setType("UUID for Lodging"); // TODO add UUID for lodging
            } else if (updateReimbRequest.getType().toUpperCase().equals("Food")) {
                updateReimbRequest.setType("UUID for Food"); //TODO add UUID for food
            } else if (updateReimbRequest.getType().toUpperCase().equals("Travel")) {
                updateReimbRequest.setType("UUID for Travel"); // TODO add UUID for Travel
            } else {
                updateReimbRequest.setType("UUID for Other"); // TODO add UUID for Other
            }
            reimbRepo.updateReimbType(UUID.fromString(updateReimbRequest.getType()), UUID.fromString(updateReimbRequest.getReimbId()));
        }

    }
   
}
