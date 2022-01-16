package com.n11.fourthhomeworkbahadirseven.dto.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExceptionDTO {
    private int status;
    private String message;
}
