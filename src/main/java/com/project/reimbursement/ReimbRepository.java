package com.project.reimbursement;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ReimbRepository extends JpaRepository<Reimbursement, UUID> {

    // save to database
    void save(NewRequest newRequest);

    Optional<Reimbursement> findReimbByReimbId(UUID reimbId);
    List<Reimbursement> findReimbByAuthorId(UUID authorId);
    
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "SELECT * FROM ers_reimbursements WHERE status_id = ?1")
    List<Reimbursement> findByStatus(UUID statusId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE ers_reimbursements SET resolver_id = ?1, resolved = ?2, status_id = ?3 WHERE reimb_id = ?4")
    void setStatus(UUID resolverId, LocalDateTime resolved, UUID statusId, UUID reimbId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE ers_reimbursements SET amount = ?1 WHERE reimb_id = ?2")
    void updateReimbAmount(double amount, UUID reimbId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE ers_reimbursements SET description = ?1 WHERE reimb_id = ?2")
    void updateReimbDescription(String description, UUID reimbId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE ers_reimbursements SET type_id = ?1 WHERE reimb_id = ?2")
    void updateReimbType(UUID type, UUID reimbId);
}
