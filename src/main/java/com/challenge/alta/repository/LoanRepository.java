package com.challenge.alta.repository;

import com.challenge.alta.entity.Loan;
import com.challenge.alta.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    Page<Loan> findByUser_Id(long userId, Pageable pageable);
}
