package com.apap.tutorial5.controller;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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
public class DealerController {
	@Autowired
	private DealerService dealerService;
	
	@Autowired
	private CarService carService;
	
	@RequestMapping("/")
	private String home() {
		return "home";
	}
	
	@RequestMapping(value="/dealer/add", method=RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("dealer", new DealerModel());
		return "addDealer";
	}
	
	@RequestMapping(value="/dealer/add",method=RequestMethod.POST)
	private String addDealerSubmit(@ModelAttribute DealerModel dealer) {
		dealerService.addDealer(dealer);
		return "add";
	}
	
	@RequestMapping(value="/dealer/view",method=RequestMethod.GET)
	private String viewDealer(@RequestParam(value="dealerId",required=true) Long dealerId, Model model) {
		DealerModel dealerList = dealerService.getDealerDetailById(dealerId).get();
		model.addAttribute("dealer", dealerList);
		return "view-dealer";
	}
	
	@RequestMapping(value = "/dealer/update/{dealerId}", method = RequestMethod.GET)
	private String updateDealer(@PathVariable(value = "dealerId") long dealerId, Model model) {
		DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
		model.addAttribute("dealer",dealer);
		return "updateDealer";
	}
	
	@RequestMapping(value = "/dealer/update/{dealerId}", method = RequestMethod.POST)
	private String updateDealerSubmit(@PathVariable (value = "dealerId") long dealerId, @ModelAttribute Optional<DealerModel> dealer) {
		if(dealer.isPresent()) {
			dealerService.updateDealer(dealerId, dealer);
			return "update";
		}
		return "not-found";
	}
	
	@RequestMapping(value = "/dealer/viewAll", method = RequestMethod.GET)
	private String viewAll (Model model) {
		List<DealerModel> cars = dealerService.getAllDetailDealer();
		model.addAttribute("dealer", cars);
		return "viewAllDealers";
	}
	
	public static Comparator<CarModel> comparePrice = new Comparator<CarModel>() {
		public int compare(CarModel o1, CarModel o2) {
			Long price1 = o1.getPrice();
			Long price2 = o2.getPrice();
			return price1.compareTo(price2);
		}
	};
	
	@RequestMapping (value = "/dealer/delete/{dealerId}", method = RequestMethod.GET)
	private String deleteDealer (@PathVariable(value = "dealerId") String dealerId, Model model) {
		DealerModel dealer =  dealerService.getDealerDetailById(Long.parseLong(dealerId)).get();
		dealerService.deleteDealer(dealer);
		return "delete";
	}
} 
