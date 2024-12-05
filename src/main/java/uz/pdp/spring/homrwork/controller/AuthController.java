package uz.pdp.spring.homrwork.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.spring.homrwork.enttiy.Role;
import uz.pdp.spring.homrwork.enttiy.User;
import uz.pdp.spring.homrwork.payload.AuthDTO;
import uz.pdp.spring.homrwork.payload.RepositoryMessage;
import uz.pdp.spring.homrwork.repository.UserRepository;

import java.util.List;
import java.util.Optional;

//bu finl fildlar uchun controllerda farqi finl va  nullarni avto constructor yozadi
@RequiredArgsConstructor
//bu calsni controller ekanini bildradi
@RestController
//bu user yoli ekanini biladiradi
@RequestMapping("/user")
public class AuthController {
    final UserRepository userRepository;

    //bu signup yoliga psot billan kelganda javob beradi
    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(User user) {
        //bu yerda userRepositoryda yozilgan methodlar orqali userning passwordi va emailni unicega tekshirish yozilgan
        Optional<User> allByEmailContains = userRepository.findAllByEmailContains(user.getEmail());
        Optional<User> allByPasswordContains = userRepository.findAllByPasswordContains(user.getPassword());
        if (!(allByEmailContains.isPresent() && allByPasswordContains.isPresent())) {
            user.setRole(Role.USER);
            return ResponseEntity.ok(userRepository.save(user));

        }
        return ResponseEntity.status(200).body(new RepositoryMessage(
                "bunday email yoki password bilan user ro'yhatdan o'tgan",
                false,
                null));
    }

    //bu signIn yoliga post bilan kelganda javob beradi
    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(@RequestBody AuthDTO authDTO, HttpServletRequest request) {
        //@RequestBody  JSON yoki boshqa formatdagi ma’lumotlarni Java obyektiga deserializatsiya qilish
        //HttpServletRequest bu saytimizda foydalanayotgan usrni olish uchun
        HttpSession session = request.getSession();
        //bu ham userni olish uchun sessiondagi
        Optional<User> byEmail = userRepository.findByEmail(authDTO.getEmail());
//bu repositoridagi email boyicha topib kelgan emailni tekshiradi
        if (byEmail.isPresent()) {
            //emil orqali useni olish
            User user = byEmail.get();
            if (user.getPassword().equals(authDTO.getPassword())) {
                session.setAttribute("user", user);
                //bu yerda bajarilgan ishga qarab meggage yuboriladi
                return ResponseEntity.status(200).body(new RepositoryMessage("welcome", true, user));
            }
        }
        return ResponseEntity.status(404).body(new RepositoryMessage("user not found", false, null));
    }
}
