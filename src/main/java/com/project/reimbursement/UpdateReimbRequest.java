package com.project.reimbursement;

import com.project.common.Request;

public class UpdateReimbRequest implements Request<Reimbursement> {

    //? Search for reimbursement
    private String reimbId;

    //? Finance Manager
    private String status;
    
    //? User
    private double amount;
    private String description;
    private String type;

    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }
    
    public String getReimbId() {
        return reimbId;
    }

    public void setReimbId(String reimbId) {
        this.reimbId = reimbId;
    }
    
    @Override
    public String toString() {
        return "UpdateReimbRequest [" +
               "Reimb = '" + reimbId + "' updated]";
    }
    
    @Override
    public Reimbursement extractEntity() {
        Reimbursement extractedEntity = new Reimbursement();
        extractedEntity.setAmount(this.amount);
        extractedEntity.setDescription(this.description);
        return extractedEntity;
    }
}
