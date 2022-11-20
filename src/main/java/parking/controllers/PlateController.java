package parking.controllers;

import org.springframework.stereotype.Controller;
import pl.nobilites.parking.domain.database.car.Car;
import pl.nobilites.parking.domain.database.car.Plate;
import pl.nobilites.parking.domain.database.user.Identifier;
import pl.nobilites.parking.repositories.CarRepository;

import java.util.List;

@Controller
public class PlateController {

    private final CarRepository carRepository;

    public PlateController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car add(Identifier identifier, Plate plate){
        return carRepository.save(new Car(identifier, plate));
    }

    public List<Identifier> find(Plate plate){
        return carRepository.findAllByPlate(plate).stream().map(Car::getIdentifier).toList();
    }


    public boolean isRegistered(Plate plate){
        return carRepository.existsByPlate(plate);
    }

    public void deleteCarPlate(Plate plate) {
        carRepository.deleteByPlate(plate);
    }



}
