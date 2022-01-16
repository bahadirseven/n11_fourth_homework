package com.n11.fourthhomeworkbahadirseven.service;

import com.n11.fourthhomeworkbahadirseven.constant.ExceptionConstant;
import com.n11.fourthhomeworkbahadirseven.constant.LoanType;
import com.n11.fourthhomeworkbahadirseven.dto.payment.PaymentRequestDTO;
import com.n11.fourthhomeworkbahadirseven.dto.payment.PaymentResponseDTO;
import com.n11.fourthhomeworkbahadirseven.exception.EntityNotFoundException;
import com.n11.fourthhomeworkbahadirseven.exception.PaymentNotEqualException;
import com.n11.fourthhomeworkbahadirseven.model.Loan;
import com.n11.fourthhomeworkbahadirseven.model.User;
import com.n11.fourthhomeworkbahadirseven.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final LoanRepository loanRepository;

    @Transactional
    public PaymentResponseDTO payment(PaymentRequestDTO paymentRequestDTO) {
        Loan loan = loanRepository.findById(paymentRequestDTO.getId()).orElseThrow(() -> new EntityNotFoundException(ExceptionConstant.LOAN_NOT_FOUND.getValue()));

        BigDecimal totalLoan = Loan.calculateTotalPaymentWithLateFeeFromLoan(loan);

        if (totalLoan.compareTo(paymentRequestDTO.getValue()) != 0) {
            throw new PaymentNotEqualException(ExceptionConstant.PAYMENT_MUST_EQUAL.getValue());
        }

        Date date = new Date();

        loan.setRemaining(BigDecimal.ZERO);
        loan.setPaymentDate(date);
        Loan savedLoan = loanRepository.save(loan);

        BigDecimal diffValue = totalLoan.subtract(loan.getValue());

        PaymentResponseDTO paymentResponseDTO = PaymentResponseDTO.builder()
                .parentLoanId(loan.getId())
                .userId(loan.getUser().getId())
                .totalValue(totalLoan)
                .remainingValue(savedLoan.getRemaining())
                .paymentDate(date)
                .build();

        if (diffValue.compareTo(BigDecimal.ZERO) > 0) {
            Loan expiredLoan = Loan.builder()
                    .value(diffValue)
                    .remaining(BigDecimal.ZERO)
                    .type(LoanType.EXPIRE_LOAN)
                    .paymentDate(date)
                    .parentLoan(Loan.builder().id(loan.getId()).build())
                    .user(User.builder().id(paymentRequestDTO.getUserId()).build())
                    .build();
            Loan expiredSavedLoan = loanRepository.save(expiredLoan);
            paymentResponseDTO.setExpiredLoanId(expiredSavedLoan.getId());
        }

        return paymentResponseDTO;
    }

    public List<PaymentResponseDTO> getPaymentByUserId(Long userId) {
        List<Loan> collectedLoanList = loanRepository.findLoanByRemainingEqualsAndUserId(BigDecimal.ZERO, userId);
        if (collectedLoanList.isEmpty()) {
            throw new EntityNotFoundException(String.format(ExceptionConstant.PAYMENT_NOT_FOUND_BY_USER_ID.getValue(), userId));
        }
        return collectedLoanList.stream().map(PaymentResponseDTO::fromLoan).collect(Collectors.toList());
    }

    public List<PaymentResponseDTO> getPaymentBetweeenDates(Date startDate, Date endDate) {
        List<Loan> collectedLoanList = loanRepository.findByPaymentDateBetween(startDate, endDate);
        if (collectedLoanList.isEmpty()) {
            throw new EntityNotFoundException(ExceptionConstant.PAYMENT_NOT_FOUND_WITH_DATES.getValue());
        }
        return collectedLoanList.stream().map(PaymentResponseDTO::fromLoan).collect(Collectors.toList());
    }

    public List<PaymentResponseDTO> getLatePaymentByUserId(Long userId) {
        List<Loan> collectedLateLoanList = loanRepository.findLoanByUserIdAndType(userId, LoanType.EXPIRE_LOAN);
        if (collectedLateLoanList.isEmpty()) {
            throw new EntityNotFoundException(String.format(ExceptionConstant.PAYMENT_NOT_FOUND_BY_USER_ID.getValue(), userId));
        }
        return collectedLateLoanList.stream().map(PaymentResponseDTO::fromLoan).collect(Collectors.toList());
    }
}
