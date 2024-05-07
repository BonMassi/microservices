package com.bon.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
@Data
public class AccountsDto {

    @NotEmpty(message = "Account number can not be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Account number must be of 10 numeric digits")
    @Schema(
            description = "Account Number of BonBank account",example = "1234567891"
    )
    private Long accountNumber;

    @NotEmpty(message = "Branch address must not be null or empty")
    @Schema(
            description = "BonBank branch address", example = "123 New York"
    )
    private String branchAddress;

    @NotEmpty(message="Account type must not be null or empty")
    @Schema(
            description = "Account type of BonBank account",example = "Savings"
    )
    private String accountType;
}
