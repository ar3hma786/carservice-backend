package com.lexolent.carservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lexolent.carservice.exception.CarLoanException;
import com.lexolent.carservice.model.CarLoan;
import com.lexolent.carservice.request.CarLoanRequest;
import com.lexolent.carservice.service.CarLoanService;

@RestController
@RequestMapping("/api/carloan")
public class CarLoanController {

    @Autowired
    private CarLoanService carLoanService;

    @PostMapping
    public ResponseEntity<CarLoan> createCarLoan(@RequestBody CarLoanRequest req) throws CarLoanException {
        CarLoan carLoan = carLoanService.createCarLoan(req);
        return new ResponseEntity<CarLoan>(carLoan, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarLoan(@PathVariable Long id) throws CarLoanException {
        carLoanService.deleteCarLoan(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarLoan> updateCarLoan(@PathVariable Long id, @RequestBody CarLoan carLoanDetails) throws CarLoanException {
        CarLoan updatedCarLoan = carLoanService.updateCarLoan(id, carLoanDetails);
        return new ResponseEntity<CarLoan>(updatedCarLoan, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CarLoan>> getAllCarLoans() {
        List<CarLoan> carLoans = carLoanService.getAllCarLoans();
        return new ResponseEntity<List<CarLoan>>(carLoans, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarLoan> getCarLoanById(@PathVariable Long id) throws CarLoanException {
        CarLoan carLoan = carLoanService.getCarLoanById(id);
        return new ResponseEntity<CarLoan>(carLoan, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CarLoan>> getCarLoansByUserId(@PathVariable Long userId) {
        List<CarLoan> carLoans = carLoanService.getCarLoansByUserId(userId);
        return new ResponseEntity<List<CarLoan>>(carLoans, HttpStatus.OK);
    }
}
