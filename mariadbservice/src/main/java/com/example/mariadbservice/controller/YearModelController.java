//package com.example.mariadbservice.controller;
//
//import com.example.mariadbservice.domain.YearModelService;
//import com.example.mariadbservice.mappers.YearModelMapper;
//import com.example.mariadbservice.model.YearModel;
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
//@RequestMapping("/year")
//public class YearModelController {
//
//    private YearModelService yearModelService;
//    private YearModelMapper yearModelMapper;
//
//    @PostMapping("/add")
//    public ResponseEntity<String> addYearModel(@RequestBody YearModelRequest yearModelRequest) {
//        YearModel yearModel = yearModelMapper.entityToYearModel(yearModelRequest);
//        Long id = yearModelService.createYearModel(yearModel);
//        return ResponseEntity.ok("The year is added");
//    }
//
//    /*
//    @PostMapping
//    public void registerCar(@RequestBody CarRegistrationRequest carRegistrationRequest) {
//        log.info("new car registration {}", carRegistrationRequest);
//        carService.registerCar(carRegistrationRequest);
//    }
//
//     */
//
//
//}