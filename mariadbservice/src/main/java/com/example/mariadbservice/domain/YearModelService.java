//package com.example.mariadbservice.domain;
//
//import com.example.mariadbservice.mappers.YearModelMapper;
//import com.example.mariadbservice.model.YearModel;
//import com.example.mariadbservice.repository.YearModelRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class YearModelService {
//
//    private YearModelRepository yearModelRepository;
//    private YearModelMapper yearModelMapper;
//
//    public Long createYearModel(YearModel yearModel) {
//        var yearModelEntity = yearModelMapper.yearModelToYearModelEntity(yearModel);
//        System.out.println("in service class YM");
//        return yearModelRepository.save(yearModelEntity).getId();
//
//    }
//}
