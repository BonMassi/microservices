package com.bon.accounts.services.client;

import com.bon.accounts.dto.CardsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("cards")
public interface CardsFeignClient {

    @GetMapping(value="/api/fetch/{mobileNumber}",consumes = "application/json")
    public ResponseEntity<CardsDto> fetchCardDetails(@PathVariable(name = "mobileNumber") String mobileNumber);
}
