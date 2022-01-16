package com.n11.fourthhomeworkbahadirseven.repository;

import com.n11.fourthhomeworkbahadirseven.constant.LoanType;
import com.n11.fourthhomeworkbahadirseven.model.Loan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {
    List<Loan> findByCreateDateBetween(Date startDate, Date endDate);
    List<Loan> findLoanByUserIdAndRemainingGreaterThan(Long userId, BigDecimal value);
    List<Loan> findLoanByUserIdAndExpireDateLessThanAndRemainingGreaterThan(Long userId, Date currentDate, BigDecimal remaining);
    List<Loan> findLoanByUserIdAndType(Long userId, LoanType type);
    List<Loan> findLoanByRemainingEqualsAndUserId(BigDecimal remainingValue, Long userId);
    List<Loan> findByPaymentDateBetween(Date startDate, Date endDate);
}
