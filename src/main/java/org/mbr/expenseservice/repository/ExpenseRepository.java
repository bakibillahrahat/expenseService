package org.mbr.expenseservice.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.mbr.expenseservice.entities.Expense;
import org.springframework.data.repository.CrudRepository;

public interface ExpenseRepository extends CrudRepository<Expense, Long> {
    List<Expense> findByExternalId(String externalId);
    List<Expense> findByUserId(String userId);
    List<Expense> findByUserIdAndCreatedAtBetween(String userId, Timestamp startDate, Timestamp endDate);
    Optional<Expense> findByUserIdAndExternalId(String userId, String externalId);
    
}
