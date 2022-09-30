package com.project.reimbursement;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ers_reimbursement_types")
public class Type {
    
    @Id
    @Column(name = "type_id")
    private UUID typeId;

    @Column
    private String type;

    public Type() {
        super();
    }

    public Type (UUID typeId, String type) {
        this.typeId = typeId;
        this.type = type;
    }

    public UUID getTypeId() {
        return typeId;
    }

    public void setTypeId(UUID typeId) {
        this.typeId = typeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type typ = (Type) o;
        return Objects.equals(typeId, typ.typeId) && Objects.equals(type, typ.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeId, type);
    }

    @Override
    public String toString() {
        return "Role [" +
                "type_ID = '" + typeId + "', " +
                "type = '" + type + "', " +
                "]";
    }
}
