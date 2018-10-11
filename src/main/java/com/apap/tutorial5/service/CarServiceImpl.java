package com.apap.tutorial5.service;

import java.util.List;

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
	public void deleteById(Long id) {
		carDb.deleteById(id);
	}
	
	@Override
	public void updateCar(Long id, CarModel newCar) {
		
		CarModel carUpdated = carDb.getOne(id);
		carUpdated.setBrand(newCar.getBrand());
		carUpdated.setType(newCar.getType());
		carUpdated.setPrice(newCar.getPrice());
		carUpdated.setAmount(newCar.getAmount());
		carDb.save(carUpdated);
	}
	
	public CarModel getCar(Long id) {
		return carDb.findById(id).get();
	}

	@Override
	public List<CarModel> getListCarOrderByPriceAsc(Long dealerId) {
		return carDb.findByDealerIdOrderByPriceAsc(dealerId);
	}

	@Override
	public void deleteCar(CarModel car) {
		carDb.delete(car);
	}

}
