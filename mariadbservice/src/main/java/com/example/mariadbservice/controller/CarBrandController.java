//package com.example.mariadbservice.controller;
//
//import com.example.mariadbservice.mappers.CarBrandMapper;
//import com.example.mariadbservice.domain.CarBrandService;
//import com.example.mariadbservice.model.CarBrand;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
//@RestController
//@AllArgsConstructor
//@RequestMapping("/carbrand")
//public class CarBrandController {
//
//    private CarBrandService carBrandService;
//    private CarBrandMapper carBrandMapper;
//
//    @PostMapping("/add")
//    public ResponseEntity<String> addCarBrand(@RequestBody CarBrandRequest carBrandRequest) {
//        CarBrand carBrand = carBrandMapper.dtoToCarBrand(carBrandRequest);
//        Long id = carBrandService.createCarBrand(carBrand);
//        return ResponseEntity.ok("Car brand Registered.");
//    }
//
//}