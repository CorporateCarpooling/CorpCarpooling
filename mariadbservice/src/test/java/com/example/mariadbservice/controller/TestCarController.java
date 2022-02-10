package com.example.mariadbservice.controller;

import com.example.mariadbservice.mappers.CarMapperImpl;
import com.example.mariadbservice.service.CarService;
import com.example.model.Car;
import com.example.model.CarBrand;
import com.example.model.YearModel;
import com.example.request.CarRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CarMapperImpl.class, CarController.class})
class TestCarController {
  public static final String BENSIN = "Bensin";
  public static final String REGISTRATIONNUMBER = "ABC123";
  public static final String BRAND_NAME = "Volvo";
  public static final String YEAR_MODEL = "2018";
  @Autowired
  private CarController underTest;

  @MockBean
  private CarService carService;

  @DisplayName("Should return ResponsEntity.ok when registerd")
  @Test
  void addCar() {
    //Given
    CarRequest carRequest = new CarRequest();
    carRequest.setUserId(2L);
    CarBrand carBrand = new CarBrand();
    carBrand.setBrandName(BRAND_NAME);
    carRequest.setCarBrand(carBrand);
    carRequest.setRegistrationNumber(REGISTRATIONNUMBER);
    YearModel yearModel = new YearModel();
    yearModel.setYearModel(YEAR_MODEL);
    carRequest.setYearModel(yearModel);
    carRequest.setFuelType(BENSIN);
    carRequest.setAvailableSeats(5);

    //when
    var response = underTest.addCar(carRequest);


    //Then
    Car expectedCar = new Car();
    expectedCar.setFuelType(BENSIN);
    expectedCar.setRegistrationNumber(REGISTRATIONNUMBER);
    expectedCar.setAvailableSeats(5);
    CarBrand carBrand1 = new CarBrand();
    carBrand1.setBrandName(BRAND_NAME);
    expectedCar.setCarBrand(carBrand1);
    YearModel yearModel1 = new YearModel();
    yearModel1.setYearModel(YEAR_MODEL);
    expectedCar.setYearModel(yearModel1);


    Mockito.verify(carService, Mockito.times(1)).createCar(eq(expectedCar), eq(carRequest.getUserId()));
    assertEquals(200, response.getStatusCodeValue());


  }

  @DisplayName("should return respons entity ok with the right value")
  @Test
  void getCar() {
    //Given
    Long userId = 200L;

    Car car = new Car();
    car.setId(userId);
    car.setFuelType(BENSIN);
    car.setRegistrationNumber(REGISTRATIONNUMBER);
    car.setAvailableSeats(5);
    CarBrand carBrand1 = new CarBrand();
    carBrand1.setBrandName(BRAND_NAME);
    car.setCarBrand(carBrand1);
    YearModel yearModel1 = new YearModel();
    yearModel1.setYearModel(YEAR_MODEL);
    car.setYearModel(yearModel1);


    Mockito.when(carService.getCarByRegistrationNumber(REGISTRATIONNUMBER, userId)).thenReturn(car);

    //When
    var response = underTest.getCar(REGISTRATIONNUMBER, userId);

    //Then
    Mockito.verify(carService, Mockito.times(1)).getCarByRegistrationNumber(eq(REGISTRATIONNUMBER), eq(userId));
    assertEquals(200, response.getStatusCodeValue());
    assertEquals(REGISTRATIONNUMBER, response.getBody().getRegistrationNumber());

  }

  @DisplayName("should return respons entity ok with allCars in body")
  @Test
  void getAllCars() {
    //Given
    Long userId = 200L;
    List<Car> cars = new ArrayList<>();
    Car expectedCar = new Car();
    expectedCar.setId(userId);
    expectedCar.setFuelType(BENSIN);
    expectedCar.setRegistrationNumber(REGISTRATIONNUMBER);
    expectedCar.setAvailableSeats(5);
    CarBrand carBrand1 = new CarBrand();
    carBrand1.setBrandName(BRAND_NAME);
    expectedCar.setCarBrand(carBrand1);
    YearModel yearModel1 = new YearModel();
    yearModel1.setYearModel(YEAR_MODEL);
    expectedCar.setYearModel(yearModel1);
    cars.add(expectedCar);
    Mockito.when(carService.getAllCars(userId)).thenReturn(cars);

    //when
    var response = underTest.getAllCars(userId);

    //Then
    assertEquals(ResponseEntity.ok(cars), response);
    assertEquals(200, response.getStatusCodeValue());

  }

  @DisplayName("should update car with the right value")
  @Test
  void updateCar() {
    //Given
    Long userId = 200L;
    CarRequest carRequest = new CarRequest();
    carRequest.setUserId(2L);
    CarBrand carBrand = new CarBrand();
    carBrand.setBrandName(BRAND_NAME);
    carRequest.setCarBrand(carBrand);
    carRequest.setRegistrationNumber(REGISTRATIONNUMBER);
    YearModel yearModel = new YearModel();
    yearModel.setYearModel(YEAR_MODEL);
    carRequest.setYearModel(yearModel);
    carRequest.setFuelType(BENSIN);
    carRequest.setAvailableSeats(5);

    Car carUpdated = new Car();
    carUpdated.setFuelType(BENSIN);
    carUpdated.setRegistrationNumber(REGISTRATIONNUMBER);
    carUpdated.setAvailableSeats(5);
    CarBrand carBrand1 = new CarBrand();
    carBrand1.setBrandName(BRAND_NAME);
    carUpdated.setCarBrand(carBrand1);
    YearModel yearModel1 = new YearModel();
    yearModel1.setYearModel(YEAR_MODEL);
    carUpdated.setYearModel(yearModel1);

    //When
    var response = underTest.updateCar(carRequest);

    //Then
    Mockito.verify(carService, Mockito.times(1)).updateCar(eq(carUpdated), eq(carRequest.getUserId()));
    assertEquals(200, response.getStatusCodeValue());
  }

  @DisplayName("Should return ResponsEntity.ok when car deleted")
  @Test
  void deleteCar() {
    //Given
    Long userId = 200L;

    //When
    var response = underTest.deleteCar(REGISTRATIONNUMBER, userId);

    //Then
    Mockito.verify(carService, Mockito.times(1)).deleteCarByRegistrationNumber(eq(REGISTRATIONNUMBER), eq(userId));
    assertEquals(200, response.getStatusCodeValue());
  }
}