package com.bon.accounts.services.client;

import com.bon.accounts.dto.CardsDto;
import com.bon.accounts.dto.LoansDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("loans")
public interface LoansFeignClient {

    @GetMapping(value="/api/fetch/{mobileNumber}",consumes = "application/json")
    public ResponseEntity<LoansDto> fetchLoanDetails(@PathVariable(name = "mobileNumber") String mobileNumber);
}
