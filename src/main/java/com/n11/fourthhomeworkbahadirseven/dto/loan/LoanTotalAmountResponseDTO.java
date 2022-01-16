package com.n11.fourthhomeworkbahadirseven.dto.loan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoanTotalAmountResponseDTO {
    private BigDecimal totalRemaining;
    private Long userId;
}
