package com.lexolent.carservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexolent.carservice.exception.CarLoanException;
import com.lexolent.carservice.model.CarLoan;
import com.lexolent.carservice.repository.CarLoanRepository;
import com.lexolent.carservice.request.CarLoanRequest;
import com.lexolent.carservice.model.User;
import com.lexolent.carservice.model.CarCompany;
import com.lexolent.carservice.repository.UserRepository;
import com.lexolent.carservice.repository.CarCompanyRepository;

@Service
public class CarLoanServiceImpl implements CarLoanService {

    @Autowired
    private CarLoanRepository carLoanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarCompanyRepository carCompanyRepository;

    @Override
    public CarLoan createCarLoan(CarLoanRequest req) throws CarLoanException {
        try {
            CarLoan loan = new CarLoan();
            loan.setAmount(req.getAmount());
            loan.setInterestRate(req.getInterestRate());
            loan.setLoanTerm(req.getLoanTerm());
            
            User user = userRepository.findById(req.getUserId())
                    .orElseThrow(() -> new CarLoanException("User not found with id: " + req.getUserId()));
            CarCompany carCompany = carCompanyRepository.findById(req.getCarCompanyId())
                    .orElseThrow(() -> new CarLoanException("CarCompany not found with id: " + req.getCarCompanyId()));

            
            loan.setUser(user);
            loan.setCarCompany(carCompany);

           
            loan.setStartDate(req.getStartDate());
            loan.setEndDate(req.getEndDate());     

            return carLoanRepository.save(loan);
        } catch (Exception e) {
            throw new CarLoanException("Failed to create loan");
        }
    }

    @Override
    public void deleteCarLoan(Long id) throws CarLoanException {
        try {
            carLoanRepository.deleteById(id);
        } catch (Exception e) {
            throw new CarLoanException("Failed to delete loan with id: " + id);
        }
    }

    @Override
    public List<CarLoan> getAllCarLoans() {
        return carLoanRepository.findAll();
    }

    @Override
    public CarLoan getCarLoanById(Long id) throws CarLoanException {
        Optional<CarLoan> optionalLoan = carLoanRepository.findById(id);
        return optionalLoan.orElseThrow(() -> new CarLoanException("Loan not found with id " + id));
    }

    @Override
    public List<CarLoan> getCarLoansByUserId(Long userId) {
        return carLoanRepository.findByUserId(userId);
    }

    @Override
    public CarLoan updateCarLoan(Long id, CarLoan carLoanDetails) throws CarLoanException {
        try {
            CarLoan carLoan = getCarLoanById(id);

         
            carLoan.setAmount(carLoanDetails.getAmount());
            carLoan.setInterestRate(carLoanDetails.getInterestRate());
            carLoan.setLoanTerm(carLoanDetails.getLoanTerm());
          
            if (carLoanDetails.getUser() != null) {
                carLoan.setUser(carLoanDetails.getUser());
            }
            if (carLoanDetails.getCarCompany() != null) {
                carLoan.setCarCompany(carLoanDetails.getCarCompany());
            }

            return carLoanRepository.save(carLoan);
        } catch (Exception e) {
            throw new CarLoanException("Failed to update loan with id: " + id);
        }
    }
}
