package com.apap.tutorial5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial5.model.CarModel;
import com.apap.tutorial5.model.DealerModel;
import com.apap.tutorial5.service.CarService;
import com.apap.tutorial5.service.DealerService;

@Controller
public class CarController {
	@Autowired
	private CarService carService;
	
	@Autowired
	private DealerService dealerService;
	
	@RequestMapping(value="/car/add/{dealerId}", method=RequestMethod.GET)
	private String add(@PathVariable(value= "dealerId") Long dealerId, Model model) {
		CarModel car = new CarModel();
		DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
		car.setDealer(dealer);
		
		model.addAttribute("car",car);
		return "addCar";
	}
	
	/*@RequestMapping(value = "/car/add/{dealerId}", method = RequestMethod.GET)
	private String add(@RequestParam(value = "dealerId") Long dealerId, Model model) {
		CarModel car = new CarModel();
		DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
		car.setDealer(dealer);
		
		model.addAttribute("car", car);
		return "addCar";
	}*/
	
	@RequestMapping(value="/car/add/", method= RequestMethod.POST)
	private String addCarSubmit(@ModelAttribute CarModel car) {
		carService.addCar(car);
		return "add";
	}
	
	@RequestMapping(value = "/car/delete{carId}", method = RequestMethod.GET)
	private String deleteCar(@PathVariable(value="carId") Long id, Model model) {
		carService.deleteCar(id);
		return "delete";
	}
	
	@RequestMapping(value = "/car/update{carId}", method = RequestMethod.GET)
	private String update(@PathVariable(value = "carId") Long carId, Model model) {
		CarModel car = carService.getCar(carId);
		model.addAttribute("newCar", car);
		return "updateCar";
	}
	
	@RequestMapping(value = "/car/update{carId}", method = RequestMethod.POST)
	private String updateCarSubmit(@PathVariable(value="carId") Long carId, @ModelAttribute CarModel car) {
		carService.updateCar(carId, car);
		return "update";
	}

}

