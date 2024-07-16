package com.lexolent.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lexolent.carservice.model.CarCompany;

public interface CarCompanyRepository extends JpaRepository<CarCompany, Long>{

}
