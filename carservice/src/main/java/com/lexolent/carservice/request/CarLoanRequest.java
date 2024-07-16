package com.lexolent.carservice.request;

import java.util.Date;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarLoanRequest {

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private Double amount;

    @NotNull(message = "Interest rate is required")
    @DecimalMin(value = "0.01", message = "Interest rate must be greater than 0")
    private Double interestRate;

    private String loanTerm; 
    
    private Double overchargedPrice; 

    @NotBlank(message = "User ID is required")
    private Long userId;
    
    private Date startDate;

    private Date endDate;


    private Long carCompanyId;

   

}
