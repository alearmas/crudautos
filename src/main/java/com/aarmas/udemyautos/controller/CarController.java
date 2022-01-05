package com.aarmas.udemyautos.controller;

import com.aarmas.udemyautos.dto.CarDTO;
import com.aarmas.udemyautos.entities.Car;
import com.aarmas.udemyautos.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService service;

    @GetMapping()
    public ResponseEntity get() {
        return ResponseEntity.ok(service.getCars());
        //return new ResponseEntity<>(service.getCars(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<CarDTO> car =  service.getCarById(id);
        return car.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/model/{model}")
    public ResponseEntity getByModel(@PathVariable("model") String model) {
        List<CarDTO> cars = service.getCarByModel(model);
        return cars.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(cars);
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody Car car) {
        try {
            CarDTO c = service.saveCar(car);
            URI location = getUri(c.getId());
            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody CarDTO car) {
        CarDTO c = service.update(car, id);
        return c != null ? ResponseEntity.ok(c) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        boolean ok = service.delete(id);
        return ok ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id")
                .buildAndExpand(id)
                .toUri();
    }

}
