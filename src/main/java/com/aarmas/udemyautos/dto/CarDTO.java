package com.aarmas.udemyautos.dto;

import com.aarmas.udemyautos.entities.Car;
import lombok.*;
import org.modelmapper.ModelMapper;

@Data
public class CarDTO extends Car {

    private Long id;
    private String name;
    private String model;

    public static CarDTO create(Car car) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(car, CarDTO.class);
    }
}
