package com.bon.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "CustomerDetails",
        description="Schema to hold Customer,Accounts,Cards and Loans"
)
public class CustomerDetailsDto {

    @Schema(
            description = "Name of the Customer",example = "Bon"
    )
    @NotEmpty(message = "Name can not be null or empty")
    @Size(min=3, max=30,message = "The length of the customer name should be between 3 and 20")
    private String name;

    @Schema(
            description = "Email of the Customer",example = "bon@ejemplo.com"
    )
    @NotEmpty(message = "Email can not be null or empty")
    @Email(message = "Email address should be a valid chain")
    private String email;

    @Schema(
            description = "Mobile Number  of the Customer",example = "3335554441"
    )
    @NotEmpty
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be of 10 numeric digits")
    private String mobileNumber;

    @Schema(
            description = "Account details of the Customer"
    )
    private AccountsDto accountsDto;


    @Schema(
            description = "Cards details of the Customer"
    )
    private CardsDto cardsDto;

    @Schema(
            description = "Loans details of the Customer"
    )
    private LoansDto loansDto;


}
