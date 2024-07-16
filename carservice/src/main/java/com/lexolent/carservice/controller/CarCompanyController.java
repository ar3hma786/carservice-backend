package com.lexolent.carservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lexolent.carservice.exception.CarCompanyException;
import com.lexolent.carservice.model.CarCompany;
import com.lexolent.carservice.request.CarCompanyRequest;
import com.lexolent.carservice.service.CarCompanyService;

@RestController
@RequestMapping("/api/carcompany")
public class CarCompanyController {

    @Autowired
    private CarCompanyService carCompanyService;

    @PostMapping
    public ResponseEntity<CarCompany> createCarCompany(@RequestBody CarCompanyRequest carCompanyRequest) throws CarCompanyException {
        CarCompany carCompany = carCompanyService.createCarCompany(carCompanyRequest);
        return new ResponseEntity<CarCompany>(carCompany, HttpStatus.CREATED);
    }

    @DeleteMapping("/{carCompanyId}")
    public ResponseEntity<Void> deleteCarCompany(@PathVariable Long carCompanyId) throws CarCompanyException {
        carCompanyService.deleteCarCompany(carCompanyId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<CarCompany>> getAllCarCompanies() {
        List<CarCompany> carCompanies = carCompanyService.getAllCarCompanies();
        return new ResponseEntity<List<CarCompany>>(carCompanies, HttpStatus.OK);
    }

    @GetMapping("/{carCompanyId}")
    public ResponseEntity<CarCompany> getCarCompanyById(@PathVariable Long carCompanyId) throws CarCompanyException {
        CarCompany carCompany = carCompanyService.getCarCompanyById(carCompanyId);
        return new ResponseEntity<CarCompany>(carCompany, HttpStatus.OK);
    }

    @PutMapping("/{carCompanyId}")
    public ResponseEntity<CarCompany> updateCarCompany(@PathVariable Long carCompanyId, @RequestBody CarCompany carCompanyDetails) throws CarCompanyException {
        CarCompany updatedCarCompany = carCompanyService.updateCarCompany(carCompanyId, carCompanyDetails);
        return new ResponseEntity<CarCompany>(updatedCarCompany, HttpStatus.OK);
    }
}
