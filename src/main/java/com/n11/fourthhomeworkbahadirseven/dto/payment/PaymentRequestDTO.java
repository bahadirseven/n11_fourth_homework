package com.n11.fourthhomeworkbahadirseven.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentRequestDTO {
    private Long id;
    private Long userId;
    private BigDecimal value;
}
