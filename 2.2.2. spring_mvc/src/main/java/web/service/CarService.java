package web.service;

import org.springframework.stereotype.Service;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    public List<Car> carList(){
        List<Car> list = new ArrayList<>();

        list.add(new Car(1L, "bmw", "x5"));
        list.add(new Car(2L, "mers", "amg"));
        list.add(new Car(3L, "tayota", "camry"));
        list.add(new Car(4L, "nissan", "maxima"));

        return list;
    }
}
