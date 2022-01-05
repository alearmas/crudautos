package com.aarmas.udemyautos.repositories;

import com.aarmas.udemyautos.dto.CarDTO;
import com.aarmas.udemyautos.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<CarDTO> findByModel(String model);
}
