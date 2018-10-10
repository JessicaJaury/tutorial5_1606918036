package com.apap.tutorial5.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tutorial5.model.CarModel;
import com.apap.tutorial5.repository.CarDb;

@Service
@Transactional
public class CarServiceImpl implements CarService {
	@Autowired
	private CarDb carDb;
	
	@Override
	public void addCar(CarModel car) {
		carDb.save(car);
	}
	
	@Override
	public void deleteCar(Long id) {
		carDb.deleteById(id);
		
	}

	@Override
	public void updateCar(Long carId, CarModel car) {
		CarModel carUpdated = carDb.getOne(carId);
		carUpdated.setBrand(car.getBrand());
		carUpdated.setType(car.getType());
		carUpdated.setPrice(car.getPrice());
		carUpdated.setAmount(car.getAmount());
		carDb.save(carUpdated);
	}
	
	@Override
	public CarModel getCar(Long id) {
		return carDb.findById(id).get();
	}

}
