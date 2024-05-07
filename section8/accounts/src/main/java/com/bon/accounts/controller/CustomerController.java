package com.bon.accounts.controller;

import com.bon.accounts.dto.CustomerDetailsDto;
import com.bon.accounts.dto.ErrorResponseDto;
import com.bon.accounts.services.ICustomersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(
        name = "REST APIs for Customers in BonProject",
        description = "REST APIs in BonBank to FETCH customer details"
)
@RestController
@RequestMapping(path = "/api",produces ={MediaType.APPLICATION_JSON_VALUE} )
@Validated
public class CustomerController {

    private final ICustomersService iCustomersService;

    public CustomerController(ICustomersService iCustomersService){
        this.iCustomersService = iCustomersService;
    }

    @Operation(
            summary = "Fetch Customer Details REST API",
            description = "REST API to fetch Customer details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                responseCode = "200",
                description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }

    )
    @GetMapping("/fetchCustomerDetails/{mobileNumber}")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@PathVariable(name = "mobileNumber")
                                                                       @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be of 10 numeric digits")
                                                                       String mobileNumber){
        CustomerDetailsDto customerDetailsDto = iCustomersService.fetchCustomerDetails(mobileNumber);

       return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);
    }
}
