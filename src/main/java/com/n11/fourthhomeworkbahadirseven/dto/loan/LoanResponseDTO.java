package com.n11.fourthhomeworkbahadirseven.dto.loan;

import com.n11.fourthhomeworkbahadirseven.constant.LoanType;
import com.n11.fourthhomeworkbahadirseven.model.Loan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoanResponseDTO {
    private Long id;
    private BigDecimal value;
    private BigDecimal remaining;
    private Date createDate;
    private Date expireDate;
    private Date paymentDate;
    private LoanType type;
    private Long parentLoanId;
    private Long userId;

    public static LoanResponseDTO fromLoan(Loan loan) {
        return new LoanResponseDTO(
                loan.getId(),
                loan.getValue(),
                loan.getRemaining(),
                loan.getCreateDate(),
                loan.getExpireDate(),
                loan.getPaymentDate(),
                loan.getType(),
                loan.getParentLoan() != null ? loan.getParentLoan().getId() : null,
                loan.getUser().getId()
        );
    }
}
