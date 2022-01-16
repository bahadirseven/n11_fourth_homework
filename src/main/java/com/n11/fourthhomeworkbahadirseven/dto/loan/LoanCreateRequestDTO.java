package com.n11.fourthhomeworkbahadirseven.dto.loan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoanCreateRequestDTO {
    private BigDecimal value;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expireDate;
    private Long userId;
}
