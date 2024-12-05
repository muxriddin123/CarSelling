package uz.pdp.spring.homrwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.spring.homrwork.enttiy.Car;
import uz.pdp.spring.homrwork.enttiy.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    //bu repository databasga ozi avto query yozish uchun faqat query
    // nomini yozsak boldi va bu car obekti va uning idsi tipli generik qabul qiladi
   Optional<User> findByEmail(String email);
   public Optional<User> findAllByEmailContains(String emil);
   public Optional<User> findAllByPasswordContains(String password);

}
