package com.lexolent.carservice.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarCompanyRequest {
   
	 
	private String name;
   
    private Double overchargedInterestRate;

}
