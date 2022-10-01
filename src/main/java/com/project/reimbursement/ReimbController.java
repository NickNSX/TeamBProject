package com.project.reimbursement;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.common.ResourceCreationResponse;
import com.project.user.UserResponse;

import static com.project.common.SecurityUtils.*;

@RestController
@RequestMapping("/reimb")
public class ReimbController {

    private static Logger logger = LogManager.getLogger(ReimbController.class);
        
    private final ReimbService reimbService;

    @Autowired
    public ReimbController(ReimbService reimbService) {
        this.reimbService = reimbService;
    }

    // Open for all employees

    // Get all reimbursements for the login user
    @GetMapping(produces = "application/json")
    public List<ReimbResponse> getAllReimbById(HttpServletRequest req) {

        logger.info("A GET request was received by /reimb at {}", LocalDateTime.now());
        HttpSession userSession = req.getSession(false);

        enforceAuthentication(userSession);

        UserResponse requester = (UserResponse) userSession.getAttribute("authUser");

        return reimbService.getAllReimbById(UUID.fromString(requester.getId()));
    }
    
    // Create new request
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResourceCreationResponse newRequest(@RequestBody NewRequest newRequest, HttpServletRequest req) {
        
        logger.info("A POST request was received by /reimb at {}", LocalDateTime.now());
        HttpSession userSession = req. getSession(false);

        enforceAuthentication(userSession);
        
        UserResponse requester = (UserResponse) userSession.getAttribute("authUser");
        
        return reimbService.createNewRequest(newRequest, UUID.fromString(requester.getId()));
    }

    @PutMapping(value="/employee")
    public void updateReimb(@RequestBody UpdateReimbRequest updateReimbRequest, HttpServletRequest req) {
        
        HttpSession userSession = req.getSession(false);

        enforceAuthentication(userSession);

        reimbService.updateReimb(updateReimbRequest);
    }
    
    // Manager access

    // Search for reimbursements by reimbursements id
    @GetMapping(value = "/{id}", produces = "application/json")
    public ReimbResponse getReimbById(@PathVariable String id, HttpServletRequest req) {

        logger.info("A GET request was received by /reimb/{id} at {}", LocalDateTime.now());
        HttpSession userSession = req.getSession(false);

        enforceAuthentication(userSession);
        enforcePermissions(userSession, "FINANCE MANAGER");
        
        return reimbService.getReimbById(id);
    }

    // Get all reimbursements
    @GetMapping(value ="/manager", produces = "application/json")
    public List<ReimbResponse> getAllReimbursements(HttpServletRequest req) {

        logger.info("A GET request was received by /reimb/manager at {}", LocalDateTime.now());
        HttpSession userSession = req.getSession(false);

        enforceAuthentication(userSession);
        enforcePermissions(userSession, "FINANCE MANAGER");

        return reimbService.getAllReimbursements();
    }

    // Get reimbursement by status
    @GetMapping(value ="/status/{status}", produces = "application/json")
    public List<ReimbResponse> getAllReimbursementsByStatus(@PathVariable String status, HttpServletRequest req) {

        logger.info("A GET request was received by /reimb/status/{status} at {}", LocalDateTime.now());
        HttpSession userSession = req.getSession(false);

        enforceAuthentication(userSession);
        enforcePermissions(userSession, "FINANCE MANAGER");

        return reimbService.getAllReimbursementsByStatus(status);
    }

    // Finance Manger can update reimbursement status
    @PutMapping(value="/manager")
    public void updateReimbStatus(@RequestBody UpdateReimbRequest updateReimbStatus, HttpServletRequest req) {

        logger.info("A PUT request was received by /reimb/manager at {}", LocalDateTime.now());
        HttpSession userSession = req.getSession(false);
        
        enforceAuthentication(userSession);
        enforcePermissions(userSession, "Finance Manager");

        UserResponse requester = (UserResponse) userSession.getAttribute("authUser");

        reimbService.updateStatus(updateReimbStatus, UUID.fromString(requester.getId()));
    }
    
}
