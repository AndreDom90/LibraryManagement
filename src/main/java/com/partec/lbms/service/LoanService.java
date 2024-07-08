package com.partec.lbms.service;

import com.partec.lbms.entity.Loan;
import com.partec.lbms.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;


    public List<Loan> getLoansDueSoon() {
        LocalDate today = LocalDate.now();
        LocalDate dueDateThreshold = today.plusDays(3);
        return loanRepository.findByDueDateBefore(dueDateThreshold);
    }

    public Loan borrowBook(Loan loan) {
        return loanRepository.save(loan);
    }

    public Optional<Loan> getLoanById(Long id) {
        return loanRepository.findById(id);
    }

    public List<Loan> getLoansByUserId(Long userId) {
        return loanRepository.findByUserId(userId);
    }
}
