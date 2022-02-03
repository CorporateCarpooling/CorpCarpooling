package com.example.carpoolservice.service;

import com.example.carpoolservice.clientapi.CarDataApi;
import com.example.carpoolservice.clientapi.CarpPoolApi;
import com.example.carpoolservice.mappers.CarPoolMapper;
import com.example.model.Car;
import com.example.model.Carpool;
import com.example.model.Passenger;
import com.example.request.CarPoolRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CarPoolService {

    private CarpPoolApi carpPoolApi;
    private CarPoolMapper carPoolMapper;
    private CarDataApi carDataApi;

    public void registerRoute(Carpool carpool, String registrationNumber, Long userId) {
        CarPoolRequest carPoolRequest = carPoolMapper.carPoolToCarPoolRequest(carpool);
        Optional<Car> carOptional = carDataApi.getCarByRegistrationNumber(registrationNumber, userId);

        if (!carOptional.isPresent()) {
            throw new RuntimeException("Car does not exist");
        } else {
            carPoolRequest.setCarId(carOptional.get().getId());
            carPoolRequest.setDriverUserId(userId);
            carpPoolApi.postRoute(carPoolRequest);
        }
    }

    public List<Carpool> getCarPools(String earliestDepartureTimeLocalDateTime, String latestDepartureTimeLocalDateTime) {
        List<Carpool> carpoolsInDataBase = carpPoolApi.getCarPoolByDate(earliestDepartureTimeLocalDateTime, latestDepartureTimeLocalDateTime);
        return carpoolsInDataBase;
    }

    private Carpool getCarpoolById(Long carpoolId) {
        Optional<Carpool> carpoolIndataBase = carpPoolApi.getCarPoolById(carpoolId);
        if (carpoolIndataBase.isEmpty()) {
            throw new RuntimeException("There is no carpool");
        }
        return carpoolIndataBase.get();
    }

    public void deleteCarpool(Long carpoolId, Long driverId) {

        Carpool carpool = getCarpoolById(carpoolId);
        if (carpool.getDriverId().longValue() != driverId.longValue()) {
            throw new RuntimeException("Not your carpool");
        }

        boolean anyApprovedPassenger = isAnyPassengerApproved(carpool);
        if (anyApprovedPassenger) {
            throw new RuntimeException("you can't remove carpool. please contact passengers");
        }

        carpPoolApi.deleteCarpoolById(carpoolId);
    }

    private boolean isAnyPassengerApproved(Carpool carpool) {
        return carpool.getPassengers().stream().anyMatch(Passenger::getApproved);
    }
}
