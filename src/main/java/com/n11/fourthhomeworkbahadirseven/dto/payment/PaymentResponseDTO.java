package com.n11.fourthhomeworkbahadirseven.dto.payment;

import com.n11.fourthhomeworkbahadirseven.model.Loan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PaymentResponseDTO {
    private Long parentLoanId;
    private Long expiredLoanId;
    private Long userId;
    private BigDecimal totalValue;
    private BigDecimal remainingValue;
    private Date paymentDate;

    public static PaymentResponseDTO fromLoan(Loan loan) {
        return PaymentResponseDTO.builder()
                .parentLoanId(loan.getParentLoan() != null ? loan.getParentLoan().getId() : null)
                .userId(loan.getUser().getId())
                .totalValue(loan.getValue())
                .remainingValue(loan.getRemaining())
                .paymentDate(loan.getPaymentDate())
                .build();
    }
}
