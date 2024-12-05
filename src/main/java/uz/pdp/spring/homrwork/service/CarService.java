package uz.pdp.spring.homrwork.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.spring.homrwork.enttiy.Car;
import uz.pdp.spring.homrwork.enttiy.Role;
import uz.pdp.spring.homrwork.enttiy.User;
import uz.pdp.spring.homrwork.payload.RepositoryMessage;
import uz.pdp.spring.homrwork.repository.CarRepository;

import java.util.List;
import java.util.Optional;

//bu natasiyamiz classni serivce ekanini bildiladi buni qoynach
// @Component anatatsiyaisi qoyish shartmas
// @Service ni ichida component anatasiyasi ishlatilgan
@Service
//bu final ozgaruvchilar uchun constructor
@RequiredArgsConstructor
public class CarService {
    //bu servisda hamma logikalar yoziladi
    final CarRepository carRepository;

    //bu method carni add qilish uchun
    public RepositoryMessage save(Car car, HttpServletRequest request) {
        Optional<Car> byModelContains = carRepository.findByModelContains(car.getModel());
        //bu yerda sessiondagi user olinadi va roli admin yoki userlikka tekshiradi
        User user = (User) request.getSession().getAttribute("user");
        //carRepository dan yozilgan ByMOdelContains metodi orqali shunda modelli qar bor yoqligini tekshrib save qiladi

        if (byModelContains.isEmpty() && user.getRole().equals(Role.ADMIN)) {
            carRepository.save(car);
            return new RepositoryMessage("car added", null, null);
        }
        return new RepositoryMessage("car not added", null, null);
    }

    public RepositoryMessage getAllCars(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user.getRole().equals(Role.ADMIN)) {
            List<Car> soldCars = carRepository.findAllBySold(true);
            return new RepositoryMessage("sotilmagan carlar", null, soldCars);
        } else if (user.getRole().equals(Role.USER)) {
            List<Car> soldCars = carRepository.findAllBySold(true);
            return new RepositoryMessage("siz sotib olishingiz mumkin bolgan carlar", null, soldCars);

        }
        return new RepositoryMessage("xatolik sodie boldi", null, null);
    }

    public Car findById(Integer id) {
        return carRepository.findById(id).get();
    }

    public RepositoryMessage deleteCar(Integer id) {
        //faqat admin ochra olsin
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return new RepositoryMessage("Car deleted", true, null);
        }
        return new RepositoryMessage("Car nor found", false, null);
    }

    public Car updateCar(Integer id, Car temp) {
        Car car = carRepository.findById(id).orElseThrow();
        car.setBrand(temp.getBrand());
        car.setModel(temp.getModel());
        car.setPrice(temp.getPrice());
        car.setColor(temp.getColor());
        return carRepository.save(car);
    }

    public RepositoryMessage findBySpecifications(String model, String brand) {
        List<Car> all = carRepository.findAllByModelOrBrand(model, brand);
        return new RepositoryMessage("Here is car list", !all.isEmpty(), all);
    }
//
//    public RepositoryMessage delete(Car car, HttpServletRequest request){
//        Optional<Car> byModelContains = carRepository.findByModelContains(car.getModel());
//    }

}
