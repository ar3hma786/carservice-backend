package com.lexolent.carservice.service;

import com.lexolent.carservice.exception.CarCompanyException;
import com.lexolent.carservice.model.CarCompany;
import com.lexolent.carservice.repository.CarCompanyRepository;
import com.lexolent.carservice.request.CarCompanyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarCompanyServiceImpl implements CarCompanyService {

    @Autowired
    private CarCompanyRepository carCompanyRepository;

    @Override
    public CarCompany createCarCompany(CarCompanyRequest carCompanyRequest) throws CarCompanyException {
        try {
            CarCompany carCompany = new CarCompany();
            carCompany.setName(carCompanyRequest.getName());
            carCompany.setOverchargedInterestRate(carCompanyRequest.getOverchargedInterestRate());

            return carCompanyRepository.save(carCompany);
        } catch (Exception e) {
            throw new CarCompanyException("Failed to create car company");
        }
    }

    @Override
    public void deleteCarCompany(Long carCompanyId) throws CarCompanyException {
        try {
            carCompanyRepository.deleteById(carCompanyId);
        } catch (Exception e) {
            throw new CarCompanyException("Failed to delete car company with id: " + carCompanyId);
        }
    }

    @Override
    public List<CarCompany> getAllCarCompanies() {
        return carCompanyRepository.findAll();
    }

    @Override
    public CarCompany getCarCompanyById(Long carCompanyId) throws CarCompanyException {
        Optional<CarCompany> optionalCarCompany = carCompanyRepository.findById(carCompanyId);
        return optionalCarCompany.orElseThrow(() -> new CarCompanyException("Car company not found with id: " + carCompanyId));
    }

    @Override
    public CarCompany updateCarCompany(Long carCompanyId, CarCompany carCompany) throws CarCompanyException {
        try {
            CarCompany existingCarCompany = getCarCompanyById(carCompanyId);
            existingCarCompany.setName(carCompany.getName());
            existingCarCompany.setOverchargedInterestRate(carCompany.getOverchargedInterestRate());
            return carCompanyRepository.save(existingCarCompany);
        } catch (Exception e) {
            throw new CarCompanyException("Failed to update car company with id: " + carCompanyId);
        }
    }
}
