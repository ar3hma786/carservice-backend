package com.lexolent.carservice.service;

import java.util.List;

import com.lexolent.carservice.exception.CarCompanyException;
import com.lexolent.carservice.model.CarCompany;
import com.lexolent.carservice.request.CarCompanyRequest;

public interface CarCompanyService {
	
	public CarCompany createCarCompany(CarCompanyRequest carCompanyRequest) throws CarCompanyException;

    public void deleteCarCompany(Long carCompanyId) throws CarCompanyException;

    public List<CarCompany> getAllCarCompanies();

    public CarCompany getCarCompanyById(Long carCompanyId) throws CarCompanyException;

    public CarCompany updateCarCompany(Long carCompanyId, CarCompany carCompanyDetails) throws CarCompanyException;

}
