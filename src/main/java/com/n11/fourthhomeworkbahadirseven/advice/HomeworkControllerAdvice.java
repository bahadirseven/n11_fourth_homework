package com.n11.fourthhomeworkbahadirseven.advice;

import com.n11.fourthhomeworkbahadirseven.constant.ExceptionConstant;
import com.n11.fourthhomeworkbahadirseven.dto.exception.ExceptionDTO;
import com.n11.fourthhomeworkbahadirseven.exception.EntityNotFoundException;
import com.n11.fourthhomeworkbahadirseven.exception.PaymentNotEqualException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HomeworkControllerAdvice {
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleException(EntityNotFoundException exception) {
        return new ResponseEntity<>(new ExceptionDTO(HttpStatus.NOT_FOUND.value(), exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = PaymentNotEqualException.class)
    public ResponseEntity<ExceptionDTO> handleException(PaymentNotEqualException exception) {
        return new ResponseEntity<>(new ExceptionDTO(HttpStatus.BAD_REQUEST.value(), exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ExceptionDTO> handleException(Exception exception) {
        return new ResponseEntity<>(new ExceptionDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), ExceptionConstant.UNEXPECTED_STATUS.getValue()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
