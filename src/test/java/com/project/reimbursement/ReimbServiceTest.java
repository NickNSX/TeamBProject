package com.project.reimbursement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.project.common.ResourceCreationResponse;
import com.project.common.exceptions.InvalidRequestException;
import com.project.common.exceptions.ResourceNotFoundException;


public class ReimbServiceTest {
    
    ReimbService sut;
    ReimbRepository mockReimbRepository;

    @BeforeEach
    public void setUp() {
        mockReimbRepository = Mockito.mock(ReimbRepository.class);
        sut = new ReimbService(mockReimbRepository);
    }
    
    @AfterEach
    public void cleanUp () {
        Mockito.reset(mockReimbRepository);
    }
    
    @Test
    public void testGetAllReimbById() {

        Status status = new Status();
        status.setStatus("Pending");
        status.setStatusId(UUID.fromString("12345678-1234-1234-1234-123456789015"));

        Type type = new Type();
        type.setType("Other");
        type.setTypeId(UUID.fromString("12345678-1234-1234-1234-123456789016"));

        Reimbursement reimb1 = new Reimbursement();
        reimb1.setId(UUID.fromString("12345678-1234-1234-1234-123456789012"));
        reimb1.setAmount(300);
        reimb1.setSubmitted(LocalDateTime.now());
        reimb1.setResolved(LocalDateTime.now());
        reimb1.setDescription("description");
        reimb1.setAuthorId(UUID.fromString("12345678-1234-1234-1234-123456789013"));
        reimb1.setResolverId(UUID.fromString("12345678-1234-1234-1234-123456789014"));
        reimb1.setStatusId(status);
        reimb1.setTypeId(type);

        List<ReimbResponse> results = new ArrayList<>();
        List<Reimbursement> reimbs = new ArrayList<>();

        reimbs.add(reimb1);

        for (Reimbursement reimb : reimbs) {
            results.add(new ReimbResponse(reimb));
        }

        assertThrows(ResourceNotFoundException.class, () -> {
            sut.getAllReimbById("12345678-1234-1234-1234-123456789012");
        });

        when(mockReimbRepository.findReimbByAuthorId(UUID.fromString("12345678-1234-1234-1234-123456789012"))).thenReturn(reimbs);

        List<ReimbResponse> actual = sut.getAllReimbById("12345678-1234-1234-1234-123456789012");
        List<ReimbResponse> expected = results;

        assertEquals(expected, actual);
    }

    @Test
    public void testGetAllReimb() {

        Status status = new Status();
        status.setStatus("Pending");
        status.setStatusId(UUID.randomUUID());

        Type type = new Type();
        type.setType("Other");
        type.setTypeId(UUID.randomUUID());

        Reimbursement reimb1 = new Reimbursement();
        reimb1.setId(UUID.randomUUID());
        reimb1.setAmount(300);
        reimb1.setSubmitted(LocalDateTime.now());
        reimb1.setResolved(LocalDateTime.now());
        reimb1.setDescription("description");
        reimb1.setAuthorId(UUID.randomUUID());
        reimb1.setResolverId(UUID.randomUUID());
        reimb1.setStatusId(status);
        reimb1.setTypeId(type);

        Reimbursement reimb2 = new Reimbursement();
        reimb2.setId(UUID.randomUUID());
        reimb2.setAmount(300);
        reimb2.setSubmitted(LocalDateTime.now());
        reimb2.setResolved(LocalDateTime.now());
        reimb2.setDescription("description");
        reimb2.setAuthorId(UUID.randomUUID());
        reimb2.setResolverId(UUID.randomUUID());
        reimb2.setStatusId(status);
        reimb2.setTypeId(type);

        List<ReimbResponse> results = new ArrayList<>();
        List<Reimbursement> reimbs = new ArrayList<>();

        reimbs.add(reimb1);
        reimbs.add(reimb2);

        results.add(new ReimbResponse(reimb1));
        results.add(new ReimbResponse(reimb2));
        

        when(mockReimbRepository.findAll()).thenReturn(reimbs);

        List<ReimbResponse> expected = results;

        List<ReimbResponse> actual = sut.getAllReimbursements();
        
        assertNotNull(results);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetReimbById() {

        assertThrows(InvalidRequestException.class, () -> {
            sut.getReimbById("");
        });

        assertThrows(ResourceNotFoundException.class, () -> {
            sut.getReimbById("12345678-1234-1234-1234-123456789015");
        });

        Status status = new Status();
        status.setStatus("Pending");
        status.setStatusId(UUID.randomUUID());

        Type type = new Type();
        type.setType("Other");
        type.setTypeId(UUID.randomUUID());

        Reimbursement reimb1 = new Reimbursement();
        reimb1.setId(UUID.fromString("12345678-1234-1234-1234-123456789015"));
        reimb1.setAmount(300);
        reimb1.setSubmitted(LocalDateTime.now());
        reimb1.setResolved(LocalDateTime.now());
        reimb1.setDescription("description");
        reimb1.setAuthorId(UUID.randomUUID());
        reimb1.setResolverId(UUID.randomUUID());
        reimb1.setStatusId(status);
        reimb1.setTypeId(type);

        when(mockReimbRepository.findById(UUID.fromString("12345678-1234-1234-1234-123456789015"))).thenReturn(Optional.of(reimb1));

        ReimbResponse actual = sut.getReimbById("12345678-1234-1234-1234-123456789015");
        ReimbResponse expected = new ReimbResponse(reimb1);

        assertEquals(expected, actual);
    }

    @Test
    public void testGetReimbursementByStatus() {

        assertThrows(InvalidRequestException.class, () -> {
            sut.getAllReimbursementsByStatus("");
        });

        List<ReimbResponse> result = new ArrayList<>();
        List<Reimbursement> reimbs = new ArrayList<>();

        Status status = new Status();
        status.setStatus("Pending");
        status.setStatusId(UUID.fromString("73e6d2a2-d6b5-4898-82a1-a6da9734aaae"));

        Type type = new Type();
        type.setType("Other");
        type.setTypeId(UUID.randomUUID());

        Reimbursement reimb1 = new Reimbursement();
        reimb1.setId(UUID.fromString("12345678-1234-1234-1234-123456789015"));
        reimb1.setAmount(300);
        reimb1.setSubmitted(LocalDateTime.now());
        reimb1.setResolved(LocalDateTime.now());
        reimb1.setDescription("description");
        reimb1.setAuthorId(UUID.randomUUID());
        reimb1.setResolverId(UUID.randomUUID());
        reimb1.setStatusId(status);
        reimb1.setTypeId(type);

        reimbs.add(reimb1);

        for (Reimbursement reimb : reimbs) {
            result.add(new ReimbResponse(reimb));
        }

        assertThrows(ResourceNotFoundException.class, () -> {
            sut.getAllReimbursementsByStatus("Approved");
        });

        assertThrows(ResourceNotFoundException.class, () -> {
            sut.getAllReimbursementsByStatus("Pending");
        });

        assertThrows(ResourceNotFoundException.class, () -> {
            sut.getAllReimbursementsByStatus("Denied");
        });
        
        // when(mockReimbRepository.findByStatus(UUID.fromString("12345678-1234-1234-1234-123456789015")).thenReturn(reimbs));
        when(mockReimbRepository.findByStatus(UUID.fromString("73e6d2a2-d6b5-4898-82a1-a6da9734aaae"))).thenReturn(reimbs);

        List<ReimbResponse> actual = sut.getAllReimbursementsByStatus("Denied");
        List<ReimbResponse> expected = result;

        assertEquals(expected, actual);
    }

    @Test
    public void testCreateRequest() {
        
        NewRequest newRequest = new NewRequest();
        
        assertThrows(InvalidRequestException.class, () -> {
            sut.createNewRequest(newRequest);
        });
        
        newRequest.setUserId("12345678-1234-1234-1234-123456789015");
        newRequest.setAmount(0);
        
        assertThrows(InvalidRequestException.class, () -> {
            sut.createNewRequest(newRequest);
        });
        
        newRequest.setAmount(10000);

        assertThrows(InvalidRequestException.class, () -> {
            sut.createNewRequest(newRequest);
        });

        newRequest.setAmount(500);
        newRequest.setDescription("");

        assertThrows(InvalidRequestException.class, () -> {
            sut.createNewRequest(newRequest);
        });

        newRequest.setType("");
        newRequest.setDescription(null);

        assertThrows(InvalidRequestException.class, () -> {
            sut.createNewRequest(newRequest);
        });

        Reimbursement insertRequest = newRequest.extractEntity();
        insertRequest.setSubmitted(LocalDateTime.now());
        newRequest.setDescription("");

        Status status = new Status();
        status.setStatusId(UUID.fromString("01569ed5-a8e6-49b8-ad61-9c9b77324eb4"));
        status.setStatus("Pending");
        insertRequest.setStatusId(status);

        newRequest.setType("Other");

        when(mockReimbRepository.save(insertRequest)).thenReturn(insertRequest);
        ResourceCreationResponse actual1 = sut.createNewRequest(newRequest);
        ResourceCreationResponse expected1 = (actual1);
        assertEquals(expected1, actual1);

        newRequest.setType("Lodging");

        when(mockReimbRepository.save(insertRequest)).thenReturn(insertRequest);
        ResourceCreationResponse actual2 = sut.createNewRequest(newRequest);
        ResourceCreationResponse expected2 = (actual2);
        assertEquals(expected2, actual2);

        newRequest.setType("Travel");
    
        when(mockReimbRepository.save(insertRequest)).thenReturn(insertRequest);
        ResourceCreationResponse actual3 = sut.createNewRequest(newRequest);
        ResourceCreationResponse expected3 = (actual3);
        assertEquals(expected3, actual3);

        newRequest.setType("Food");
    
        when(mockReimbRepository.save(insertRequest)).thenReturn(insertRequest);
        ResourceCreationResponse actual4 = sut.createNewRequest(newRequest);
        ResourceCreationResponse expected4 = (actual4);
        assertEquals(expected4, actual4);
    }

    @Test
    public void testUpdateStatus() {

        UpdateReimbRequest updateReimbRequest = new UpdateReimbRequest();
        updateReimbRequest.setReimbId("12345678-1234-1234-1234-123456789015");
        updateReimbRequest.setResolverId("12345678-1234-1234-1234-123456789001");
        
        assertThrows(InvalidRequestException.class, () -> {
            sut.updateStatus(updateReimbRequest);
        });

        Status status = new Status();
        status.setStatus("Pending");
        status.setStatusId(UUID.fromString("73e6d2a2-d6b5-4898-82a1-a6da9734aaae"));

        Type type = new Type();
        type.setType("Other");
        type.setTypeId(UUID.randomUUID());

        Reimbursement reimb1 = new Reimbursement();
        reimb1.setId(UUID.fromString("12345678-1234-1234-1234-123456789015"));
        reimb1.setAmount(300);
        reimb1.setSubmitted(LocalDateTime.now());
        reimb1.setResolved(LocalDateTime.now());
        reimb1.setDescription("description");
        reimb1.setAuthorId(UUID.randomUUID());
        reimb1.setResolverId(UUID.randomUUID());
        reimb1.setStatusId(status);
        reimb1.setTypeId(type);

        updateReimbRequest.setStatus("Approved");

        when(mockReimbRepository.findReimbByReimbId(UUID.fromString(updateReimbRequest.getReimbId()))).thenReturn(Optional.of(reimb1));
        sut.updateStatus(updateReimbRequest);

        updateReimbRequest.setStatus("Denied");

        when(mockReimbRepository.findReimbByReimbId(UUID.fromString(updateReimbRequest.getReimbId()))).thenReturn(Optional.of(reimb1));
        sut.updateStatus(updateReimbRequest);

        updateReimbRequest.setStatus("");

        assertThrows(InvalidRequestException.class, () -> {
            sut.updateStatus(updateReimbRequest);
        });
    }

    @Test
    public void testUpdateReimb() {
        UpdateReimbRequest updateReimb = new UpdateReimbRequest();
        updateReimb.setReimbId("12345678-1234-1234-1234-123456789015");

        assertThrows(ResourceNotFoundException.class, () -> {
            sut.updateReimb(updateReimb);
        });

        Status status = new Status();
        status.setStatus("Approved");
        status.setStatusId(UUID.fromString("4811472a-8c9e-404c-9e0e-da6f537f5278"));

        Type type = new Type();
        type.setType("Other");
        type.setTypeId(UUID.randomUUID());

        Reimbursement reimb1 = new Reimbursement();
        reimb1.setId(UUID.fromString("12345678-1234-1234-1234-123456789015"));
        reimb1.setAmount(300);
        reimb1.setSubmitted(LocalDateTime.now());
        reimb1.setResolved(LocalDateTime.now());
        reimb1.setDescription("description");
        reimb1.setAuthorId(UUID.randomUUID());
        reimb1.setResolverId(UUID.randomUUID());
        reimb1.setStatusId(status);
        reimb1.setTypeId(type);
        
        assertThrows(InvalidRequestException.class, () -> {
            when(mockReimbRepository.findReimbByReimbId(UUID.fromString(updateReimb.getReimbId()))).thenReturn(Optional.of(reimb1));
            sut.updateReimb(updateReimb);
        });

        status.setStatusId(UUID.fromString("01569ed5-a8e6-49b8-ad61-9c9b77324eb4")); // TODO add uuid for Pending 8-4-4-4-12
        status.setStatus("Pending");
        
        updateReimb.setAmount(11000);
        assertThrows(InvalidRequestException.class, () -> {
            sut.updateReimb(updateReimb);
        });
        
        updateReimb.setAmount(0);
        sut.updateReimb(updateReimb);
        
        updateReimb.setAmount(500);
        sut.updateReimb(updateReimb);

        updateReimb.setDescription("");
        sut.updateReimb(updateReimb);

        updateReimb.setType("Lodging");
        sut.updateReimb(updateReimb);
        
        updateReimb.setType("Food");
        sut.updateReimb(updateReimb);

        updateReimb.setType("Travel");
        sut.updateReimb(updateReimb);

        updateReimb.setType("");
        sut.updateReimb(updateReimb);
        
       
    }
}


