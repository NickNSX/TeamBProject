package com.project.reimbursement;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ReimbRepository extends JpaRepository<Reimbursement, UUID> {

    // save to database
    void save(NewRequest newRequest);

    Optional<Reimbursement> findReimbByReimbId(UUID reimbId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE ers_reimbursements SET resolver_id = :aId, resolved = :res, status_id = :sId WHERE reimb_id = :rId")
    void setStatus(@Param("aId") UUID resolverId, @Param("res") LocalDateTime resolved, @Param("sId") UUID statusId, @Param("rId") UUID reimbId);
}
