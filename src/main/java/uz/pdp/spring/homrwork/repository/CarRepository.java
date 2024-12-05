package uz.pdp.spring.homrwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.spring.homrwork.enttiy.Car;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Integer> {
    //bu repository databasga ozi avto query yozish uchun faqat query
    // nomini yozsak boldi va bu car obekti va uning idsi tipli generik qabul qiladi

    //modeli trng bolganlarni topib beradi
   Optional<Car> findByModelContains(String brand);
   //soldi larini toib beradi
   List<Car> findAllBySold(boolean sold);
   //carlarni modeli va brandiga qarab olib keladi
    List<Car> findAllByModelOrBrand(String model, String brand);
    List<Car> findCarsByOwnerId(int ownerId);
}
