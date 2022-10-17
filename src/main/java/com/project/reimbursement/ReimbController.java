package com.project.reimbursement;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.common.ResourceCreationResponse;

@RestController
@RequestMapping("/reimb")
@CrossOrigin(origins = "http://localhost:4200/", allowCredentials = "true")
public class ReimbController {

    private static Logger logger = LogManager.getLogger(ReimbController.class);
        
    private final ReimbService reimbService;

    @Autowired
    public ReimbController(ReimbService reimbService) {
        this.reimbService = reimbService;
    }

    //* Open for all employees

    //? Get all reimbursements for the login user
    @GetMapping(value="/employee/{id}", produces = "application/json")
    public List<ReimbResponse> getAllReimbById(@PathVariable String id) {

        logger.info("A GET request was received by /reimb/employee/{id} at {}", LocalDateTime.now());

        return reimbService.getAllReimbById(id);
    } 
    
    //? Create new request
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResourceCreationResponse newRequest(@RequestBody NewRequest newRequest) {
        
        logger.info("A POST request was received by /reimb at {}", LocalDateTime.now());

        return reimbService.createNewRequest(newRequest);
    } 

    @PutMapping(value="/employee")
    public void updateReimb(@RequestBody UpdateReimbRequest updateReimbRequest) {
        
        logger.info("A PUT request was received by /reimb/employee at {}", LocalDateTime.now());
        
        reimbService.updateReimb(updateReimbRequest);
    } 
    
    //* Manager access

    //? Search for reimbursements by reimbursements id
    @GetMapping(value = "/{id}", produces = "application/json")
    public ReimbResponse getReimbById(@PathVariable String id) {

        logger.info("A GET request was received by /reimb/{id} at {}", LocalDateTime.now());
        
        return reimbService.getReimbById(id);
    } 

    //? Get all reimbursements
    @GetMapping(value ="/manager", produces = "application/json")
    public List<ReimbResponse> getAllReimbursements(HttpServletRequest req) {

        logger.info("A GET request was received by /reimb/manager at {}", LocalDateTime.now());

        return reimbService.getAllReimbursements();
    } 

    //? Get reimbursement by status
    @GetMapping(value ="/status/{status}", produces = "application/json")
    public List<ReimbResponse> getAllReimbursementsByStatus(@PathVariable String status, HttpServletRequest req) {

        logger.info("A GET request was received by /reimb/status/{status} at {}", LocalDateTime.now());

        return reimbService.getAllReimbursementsByStatus(status);
    } 

    //? Finance Manger can update reimbursement status
    @PutMapping(value="/manager")
    public void updateReimbStatus(@RequestBody UpdateReimbRequest updateReimbStatus, HttpServletRequest req) {

        logger.info("A PUT request was received by /reimb/manager at {}", LocalDateTime.now());

        reimbService.updateStatus(updateReimbStatus);
    } 
    
}
