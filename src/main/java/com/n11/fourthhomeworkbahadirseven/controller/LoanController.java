package com.n11.fourthhomeworkbahadirseven.controller;

import com.n11.fourthhomeworkbahadirseven.dto.loan.LoanCreateRequestDTO;
import com.n11.fourthhomeworkbahadirseven.dto.loan.LoanResponseDTO;
import com.n11.fourthhomeworkbahadirseven.dto.loan.LoanTotalAmountResponseDTO;
import com.n11.fourthhomeworkbahadirseven.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/loan")
public class LoanController {
    private final LoanService loanService;

    @PostMapping("/create")
    public ResponseEntity<LoanResponseDTO> createLoan(@RequestBody LoanCreateRequestDTO loanRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(loanService.createLoan(loanRequest));
    }

    @GetMapping("/loan-between-date")
    public ResponseEntity<List<LoanResponseDTO>> getLoanBetweenDates
            (@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
             @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate){
        return ResponseEntity.ok().body(loanService.getLoanBetweenDates(startDate,endDate));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<LoanResponseDTO>> getLoanByUserId(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(loanService.getLoanByUserId(id));
    }

    @GetMapping("/user/expired-loan/{id}")
    public ResponseEntity<List<LoanResponseDTO>> getExpiredLoanByUserId(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(loanService.getExpiredLoanByUserId(id));
    }

    @GetMapping("/user/all-loan-amount/{id}")
    public ResponseEntity<LoanTotalAmountResponseDTO> getAllLoanAmountByUserId(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(loanService.getAllLoanAmountByUserId(id));
    }

    @GetMapping("/user/all-expired-loan-amount/{id}")
    public ResponseEntity<LoanTotalAmountResponseDTO> getAllExpiredLoanAmountByUserId(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(loanService.getAllExpiredLoanAmountByUserId(id));
    }

    @GetMapping("/user/late-fee-amount/{id}")
    public ResponseEntity<LoanTotalAmountResponseDTO> getAllLateFeeAmountByUserId(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(loanService.getAllLateFeeAmountByUserId(id));
    }
}
