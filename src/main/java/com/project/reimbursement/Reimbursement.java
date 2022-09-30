package com.project.reimbursement;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ers_reimbursements")
public class Reimbursement {

    @Id
    @Column(name = "reimb_id", nullable = false)
    private UUID reimbId;

    @GeneratedValue
    @Column(columnDefinition = "NUMERIC(6,2)", nullable = false)
    private double amount;

    @Column(nullable = false)
    private LocalDateTime submitted;

    @Column
    private LocalDateTime resolved;

    @Column(nullable = false)
    private String description;
    
    @Column(name = "author_id", nullable = false)
    private UUID authorId;

    @Column(name = "resolver_id")
    private UUID resolverId;
    
    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private Status statusId;
    
    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private Type typeId;
    
    public Reimbursement() {
        super();
    }

    public Reimbursement(UUID reimbId, double amount, LocalDateTime submitted, LocalDateTime resolved,
             String description, UUID authorId, UUID resolverId, Status statusId, Type typeId) {
        this.reimbId = reimbId;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.authorId = authorId;
        this.resolverId = resolverId;
        this.statusId = statusId;
        this.typeId = typeId;
    }

    public UUID getId() {
        return reimbId;
    }

    public void setId(UUID reimbId) {
        this.reimbId = reimbId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getSubmitted() {
        return submitted;
    }

    public void setSubmitted(LocalDateTime submitted) {
        this.submitted = submitted;
    }

    public LocalDateTime getResolved() {
        return resolved;
    }

    public void setResolved(LocalDateTime resolved) {
        this.resolved = resolved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getAuthorId() {
        return authorId;
    }

    public void setAuthorId(UUID authorId) {
        this.authorId = authorId;
    }

    public UUID getResolverId() {
        return resolverId;
    }

    public void setResolverId(UUID resolverId) {
        this.resolverId = resolverId;
    }

    public Status getStatusId() {
        return statusId;
    }

    public void setStatusId(Status statusId) {
        this.statusId = statusId;
    }

    public Type getTypeId() {
        return typeId;
    }

    public void setTypeId(Type typeId) {
        this.typeId = typeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reimbursement reimbursement = (Reimbursement) o;
        return Objects.equals(reimbId, reimbursement.reimbId) && Objects.equals(amount, reimbursement.amount)
            && Objects.equals(submitted, reimbursement.submitted) && Objects.equals(resolved, reimbursement.resolved) 
            && Objects.equals(description, reimbursement.description) && Objects.equals(authorId, reimbursement.authorId) 
            && Objects.equals(resolverId, reimbursement.resolverId) && Objects.equals(statusId, reimbursement.statusId)
            && Objects.equals(typeId, reimbursement.typeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reimbId, amount, submitted, resolved, description, authorId, resolverId, statusId, typeId);
    }

    @Override
    public String toString() {
        return "Reimbursement [ " +
               "amount = '" + amount + "', " +
               "authorId = '" + authorId + "', " +
               "description = '" + description + "', " +
               "id = '" + reimbId + "', " + 
               "resolved = '" + resolved + "', " +
               "resolverId = '" + resolverId + "', " +
               "statusId = '" + statusId + "', " +
               "submitted = '" + submitted + ", " + 
               "typeId = '" + typeId + "' " +
               "]";
    }

    
}
