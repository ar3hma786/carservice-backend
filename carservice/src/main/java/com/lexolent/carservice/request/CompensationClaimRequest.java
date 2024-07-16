package com.lexolent.carservice.request;

import java.util.Date;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompensationClaimRequest {
   
    private Long userId;
    
    private Double claimedAmount;

    private String claimReason;

 
    private Date submissionDate;

}
