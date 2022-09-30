package com.project.reimbursement;

import java.util.UUID;

import com.project.common.Request;

public class NewRequest implements Request<Reimbursement> {

    private double amount;
    private String description;

    // Used as a string to set the type in ReimbService
    private String type;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "New Reimbursement Request {" +
               "amount = '" + amount + "', " +
               "description = '" + description + "' " +
               "type = '" + type + "'}";
    }

    @Override
    public Reimbursement extractEntity() {
        Reimbursement extractedEntity = new Reimbursement();
        extractedEntity.setId(UUID.randomUUID());
        extractedEntity.setAmount(this.amount);
        extractedEntity.setDescription(this.description);

        return extractedEntity;
    }
}
