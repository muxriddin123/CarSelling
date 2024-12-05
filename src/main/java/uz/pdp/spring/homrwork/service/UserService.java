package uz.pdp.spring.homrwork.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.spring.homrwork.enttiy.Car;
import uz.pdp.spring.homrwork.enttiy.User;

import uz.pdp.spring.homrwork.payload.PurchaseDTO;
import uz.pdp.spring.homrwork.payload.RepositoryMessage;
import uz.pdp.spring.homrwork.repository.CarRepository;
import uz.pdp.spring.homrwork.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    final UserRepository userRepository;
    final CarRepository carRepository;

    public RepositoryMessage purchase(PurchaseDTO purchaseDTO) {
        // optionalUser purchesDtodan userni olib keladi
        Optional<User> optionalUser = userRepository.findById(purchaseDTO.getUserId());
        // optionalUser purchesDtodan car olib keladi
        Optional<Car> optionalCar = carRepository.findById(purchaseDTO.getCarId());
        if (optionalCar.isPresent() && optionalUser.isPresent()) {
            User user = optionalUser.get();
            Car car = optionalCar.get();
            // car.setOwner(user);
            // carni egasiga shu userni set qiladi
            // car.setOwner(user);
            //carni soldiga falsmi beradi
            car.setSold(false);
             car.setOwner(user);
            purchaseDTO.setSoldCar(false);
            carRepository.save(car);
            return new RepositoryMessage("car buying", true, car);
        }
        return new RepositoryMessage("user not found", false, null);
    }
    public RepositoryMessage myCars(HttpServletRequest request) {
        // Session-dan foydalanuvchini olish
        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            return new RepositoryMessage("User not logged in", false, null);
        }

        // Foydalanuvchiga tegishli avtomobillarni olish
        List<Car> carsByOwnerId = carRepository.findCarsByOwnerId(user.getId());

        if (carsByOwnerId.isEmpty()) {
            return new RepositoryMessage("No cars found for this user", false, null);
        }

        return new RepositoryMessage("Cars found", true, carsByOwnerId);
    }







}