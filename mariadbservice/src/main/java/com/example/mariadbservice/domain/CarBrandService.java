package com.example.mariadbservice.domain;

import com.example.mariadbservice.mappers.CarBrandMapper;
import com.example.mariadbservice.model.CarBrand;
import com.example.mariadbservice.repository.CarBrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarBrandService {

    private CarBrandRepository carBrandRepository;
    private CarBrandMapper carBrandMapper;

    public Long createCarBrand(CarBrand carBrand) {
        var carBrandEntity = carBrandMapper.carBrandToCarBrandDto(carBrand);
        System.out.println("in service class car brand");
        return carBrandRepository.save(carBrandEntity).getId();
    }
}
