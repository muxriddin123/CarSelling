package uz.pdp.spring.homrwork.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.spring.homrwork.enttiy.Car;
import uz.pdp.spring.homrwork.enttiy.User;
import uz.pdp.spring.homrwork.payload.CarDTO;
import uz.pdp.spring.homrwork.payload.PurchaseDTO;
import uz.pdp.spring.homrwork.service.CarService;
import uz.pdp.spring.homrwork.service.UserService;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {
    final CarService carService;
    final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Car car, HttpServletRequest request) {
        return ResponseEntity.status(200).body(carService.save(car, request));
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAll(HttpServletRequest request) {
        return ResponseEntity.ok(carService.getAllCars(request));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Car car) {
        return ResponseEntity.status(200).body(carService.updateCar(id, car));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(carService.deleteCar(id));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(carService.findById(id));
    }

    @GetMapping("/modelAndBrend")
    public ResponseEntity<?> getModelBreadCar(@RequestParam(
            required = false, defaultValue = "") String model, @RequestParam(
            required = false, defaultValue = "") String brand) {
        return ResponseEntity.status(200).body(carService.findBySpecifications(model, brand));
    }
    @PostMapping("/buy")
    public ResponseEntity<?> buyCar(PurchaseDTO purchaseDTO){
        return ResponseEntity.status(200).body(userService.purchase(purchaseDTO));
    }
    @GetMapping("/myCars")
    public ResponseEntity<?> getMyCars(  HttpServletRequest request) {
        return ResponseEntity.status(200).body(userService.myCars(request));
    }

}
