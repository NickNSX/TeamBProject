package com.project.reimbursement;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ers_reimbursement_statuses")
public class Status {

    @Id
    @Column(name = "status_id", nullable = false)
    private UUID statusId;
    
    @Column(nullable = false)
    private String status;

    public Status() {
        super();
    }

    public Status (UUID statusId, String status) {
        this.statusId = statusId;
        this.status = status;
    }

    public UUID getStatusId() {
        return statusId;
    }

    public void setStatusId(UUID statusId) {
        this.statusId = statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status stat = (Status) o;
        return Objects.equals(statusId, stat.statusId) && Objects.equals(status, stat.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusId, status);
    }

    @Override
    public String toString() {
        return "Role [" +
                "status_id = '" + statusId + "', " +
                "status = '" + status + "', " +
                "]";
    }
}
