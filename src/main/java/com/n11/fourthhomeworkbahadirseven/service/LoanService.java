package com.n11.fourthhomeworkbahadirseven.service;

import com.n11.fourthhomeworkbahadirseven.constant.ExceptionConstant;
import com.n11.fourthhomeworkbahadirseven.dto.loan.LoanCreateRequestDTO;
import com.n11.fourthhomeworkbahadirseven.dto.loan.LoanResponseDTO;
import com.n11.fourthhomeworkbahadirseven.dto.loan.LoanTotalAmountResponseDTO;
import com.n11.fourthhomeworkbahadirseven.exception.EntityNotFoundException;
import com.n11.fourthhomeworkbahadirseven.model.Loan;
import com.n11.fourthhomeworkbahadirseven.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoanService {
    private final LoanRepository loanRepository;

    public LoanResponseDTO createLoan(LoanCreateRequestDTO loanRequest) {
        Loan loan = Loan.createNewLoanFromLoanRequest(loanRequest);

        Loan savedLoan = loanRepository.save(loan);

        return LoanResponseDTO.fromLoan(savedLoan);
    }

    public List<LoanResponseDTO> getLoanBetweenDates(Date startDate, Date endDate) {
        List<Loan> loanList = loanRepository.findByCreateDateBetween(startDate, endDate);
        if (loanList.isEmpty()) {
            throw new EntityNotFoundException(ExceptionConstant.LOAN_NOT_FOUND_WITH_DATES.getValue());
        }
        return loanList.stream().map(LoanResponseDTO::fromLoan).collect(Collectors.toList());
    }

    public List<LoanResponseDTO> getLoanByUserId(Long userId) {
        List<Loan> loanList = loanRepository.findLoanByUserIdAndRemainingGreaterThan(userId, BigDecimal.ZERO);
        if (loanList.isEmpty()) {
            throw new EntityNotFoundException(String.format(ExceptionConstant.LOAN_NOT_FOUND_BY_USER_ID.getValue(), userId));
        }
        return loanList.stream().map(LoanResponseDTO::fromLoan).collect(Collectors.toList());
    }

    public List<LoanResponseDTO> getExpiredLoanByUserId(Long userId) {
        List<Loan> loanList = loanRepository.findLoanByUserIdAndExpireDateLessThanAndRemainingGreaterThan(userId, new Date(), BigDecimal.ZERO);
        if (loanList.isEmpty()) {
            throw new EntityNotFoundException(String.format(ExceptionConstant.EXPIRED_LOAN_NOT_FOUND_BY_USER_ID.getValue(), userId));
        }
        return loanList.stream().map(LoanResponseDTO::fromLoan).collect(Collectors.toList());
    }

    public LoanTotalAmountResponseDTO getAllLoanAmountByUserId(Long userId) {
        List<Loan> loanList = loanRepository.findLoanByUserIdAndRemainingGreaterThan(userId, BigDecimal.ZERO);
        if (loanList.isEmpty()) {
            throw new EntityNotFoundException(String.format(ExceptionConstant.LOAN_NOT_FOUND_BY_USER_ID.getValue(), userId));
        }
        BigDecimal totalRemaining = loanList.stream().map(Loan::calculateTotalPaymentWithLateFeeFromLoan).reduce(BigDecimal.ZERO, BigDecimal::add);
        return new LoanTotalAmountResponseDTO(totalRemaining, userId);
    }

    public LoanTotalAmountResponseDTO getAllExpiredLoanAmountByUserId(Long userId) {
        List<Loan> loanList = loanRepository.findLoanByUserIdAndExpireDateLessThanAndRemainingGreaterThan(userId, new Date(), BigDecimal.ZERO);
        if (loanList.isEmpty()) {
            throw new EntityNotFoundException(String.format(ExceptionConstant.EXPIRED_LOAN_NOT_FOUND_BY_USER_ID.getValue(), userId));
        }
        BigDecimal totalRemaining = loanList.stream().map(Loan::calculateTotalPaymentWithLateFeeFromLoan).reduce(BigDecimal.ZERO, BigDecimal::add);
        return new LoanTotalAmountResponseDTO(totalRemaining, userId);
    }

    public LoanTotalAmountResponseDTO getAllLateFeeAmountByUserId(Long userId) {
        List<Loan> loanList = loanRepository.findLoanByUserIdAndExpireDateLessThanAndRemainingGreaterThan(userId, new Date(), BigDecimal.ZERO);
        if (loanList.isEmpty()) {
            throw new EntityNotFoundException(String.format(ExceptionConstant.LATE_FEE_NOT_FOUND_BY_USER_ID.getValue(), userId));
        }
        BigDecimal totalRemaining = loanList.stream().map(Loan::getRemaining).reduce(BigDecimal.ZERO, BigDecimal::add);
        return new LoanTotalAmountResponseDTO(totalRemaining, userId);
    }
}
