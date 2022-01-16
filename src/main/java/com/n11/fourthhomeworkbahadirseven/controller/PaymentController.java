package com.n11.fourthhomeworkbahadirseven.controller;

import com.n11.fourthhomeworkbahadirseven.dto.payment.PaymentRequestDTO;
import com.n11.fourthhomeworkbahadirseven.dto.payment.PaymentResponseDTO;
import com.n11.fourthhomeworkbahadirseven.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> payment(@RequestBody PaymentRequestDTO paymentRequestDTO) {
        return ResponseEntity.ok().body(paymentService.payment(paymentRequestDTO));
    }

    @GetMapping("/payment-between-date")
    public ResponseEntity<List<PaymentResponseDTO>> getPaymentBetweenDates
            (@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate){
        return ResponseEntity.ok().body(paymentService.getPaymentBetweeenDates(startDate,endDate));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<PaymentResponseDTO>> getPaymentByUserId(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(paymentService.getPaymentByUserId(id));
    }

    @GetMapping("/user/{id}/late-payment-amount")
    public ResponseEntity<List<PaymentResponseDTO>> getLatePaymentByUserId(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(paymentService.getLatePaymentByUserId(id));
    }
}
