package com.apap.tutorial5.service;
import java.util.Optional;

import com.apap.tutorial5.model.CarModel;

public interface CarService {
	void addCar(CarModel car);
	void deleteCar(Long carId);
	void updateCar(Long carId, CarModel car);
	CarModel getCar(Long id);
}
