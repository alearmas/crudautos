package com.aarmas.udemyautos.services;

import com.aarmas.udemyautos.dto.CarDTO;
import com.aarmas.udemyautos.entities.Car;
import com.aarmas.udemyautos.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {

    @Autowired
    private CarRepository repository;

    public List<CarDTO> getCars() {
        return repository.findAll()
                         .stream()
                         .map(CarDTO::create)
                         .collect(Collectors.toList());
    }

    public Optional getCarById(Long id) {
        return repository.findById(id).map(CarDTO::create);
    }

    public List<CarDTO> getCarByModel(String model) {
        return repository.findByModel(model)
                         .stream()
                         .map(CarDTO::create)
                         .collect(Collectors.toList());
    }

    public CarDTO saveCar(Car car) {
        Assert.isNull((car.getId()), "We could not save a car");
        return CarDTO.create(repository.save(car));
    }

    public CarDTO update(CarDTO car, Long id) {
        Assert.notNull(id, "We could not update this car");
        Optional<CarDTO> optional = getCarById(id);
        if(optional.isPresent()) {
            CarDTO db = optional.get();
            db.setName(car.getName());
            db.setModel(car.getModel());
            repository.save(db);
            return CarDTO.create(db);
        } else {
            throw new RuntimeException("We could not update this car");
        }
    }

    public boolean delete(Long id) {
        if(getCarById(id).isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

}
