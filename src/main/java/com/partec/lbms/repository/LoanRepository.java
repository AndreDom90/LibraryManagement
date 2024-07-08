package com.partec.lbms.repository;

import com.partec.lbms.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.time.LocalDate;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByUserId(Long userId);
    List<Loan> findByDueDateBefore(LocalDate dueDate);
}
