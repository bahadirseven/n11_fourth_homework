package com.n11.fourthhomeworkbahadirseven.model;

import com.n11.fourthhomeworkbahadirseven.constant.LoanType;
import com.n11.fourthhomeworkbahadirseven.dto.loan.LoanCreateRequestDTO;
import com.n11.fourthhomeworkbahadirseven.util.DateUtil;
import com.n11.fourthhomeworkbahadirseven.util.RateUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "n11_loan")
public class Loan implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    @Column(name = "loan_value")
    private BigDecimal value;

    @Column(name = "remaining")
    private BigDecimal remaining;

    @Column(name = "loan_type")
    @Enumerated(EnumType.STRING)
    private LoanType type;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "expire_date")
    private Date expireDate;

    @Column(name = "payment_date")
    private Date paymentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_loan_id")
    private Loan parentLoan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public static Loan createNewLoanFromLoanRequest(LoanCreateRequestDTO loanRequest) {
        return Loan.builder()
                .value(loanRequest.getValue())
                .remaining(loanRequest.getValue())
                .type(LoanType.MAIN_LOAN)
                .expireDate(loanRequest.getExpireDate())
                .user(User.builder().id(loanRequest.getUserId()).build())
                .build();
    }

    public static BigDecimal calculateTotalPaymentWithLateFeeFromLoan(Loan loan) {
        BigDecimal result = loan.getRemaining();
        result = result.setScale(2, RoundingMode.HALF_UP);

        long numberOfLateDays = DateUtil.getNumberOfDaysWithCurrentDate(loan.getExpireDate());

        if (numberOfLateDays > 0L) {
            BigDecimal rate = RateUtil.calculateRateFromCurrentDate(DateUtil.getCurrentYear());

            BigDecimal lateFeePerDate = rate.multiply(loan.getRemaining()).divide(BigDecimal.valueOf(100));

            BigDecimal totalLateFee = lateFeePerDate.multiply(BigDecimal.valueOf(numberOfLateDays));

            result = loan.getRemaining().add(totalLateFee);
        }

        return result;
    }

    public static BigDecimal calculateTotalLateFeeFromLoan(Loan loan) {
        BigDecimal result = BigDecimal.ZERO;
        result = result.setScale(2, RoundingMode.HALF_UP);

        long numberOfLateDays = DateUtil.getNumberOfDaysWithCurrentDate(loan.getExpireDate());

        BigDecimal rate = RateUtil.calculateRateFromCurrentDate(DateUtil.getCurrentYear());
        BigDecimal lateFeePerDate = rate.multiply(loan.getRemaining()).divide(BigDecimal.valueOf(100));

        return lateFeePerDate.multiply(BigDecimal.valueOf(numberOfLateDays));
    }

    @PrePersist
    protected void onCreate() {
        createDate = new Date();
        value = value.setScale(2, RoundingMode.HALF_UP);
        remaining = remaining.setScale(2, RoundingMode.HALF_UP);
    }

    @PreUpdate
    protected void onUpdate() {
        value = value.setScale(2, RoundingMode.HALF_UP);
        remaining = remaining.setScale(2, RoundingMode.HALF_UP);
    }

}
