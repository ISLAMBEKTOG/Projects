package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.model.Car;
import web.service.CarService;

import java.util.List;

@Controller
@RequestMapping("/")
public class CarController {

    @Autowired
    private CarService carService;

    @RequestMapping(value = "cars", method = RequestMethod.GET)
    public String printCars(ModelMap model) {
        List<Car> cars = carService.carList();
        model.addAttribute("carList", cars);

        return "cars";
    }
}
