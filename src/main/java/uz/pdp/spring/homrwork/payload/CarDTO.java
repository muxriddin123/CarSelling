package uz.pdp.spring.homrwork.payload;

import lombok.Data;
import uz.pdp.spring.homrwork.enttiy.User;

import java.util.List;

@Data
public class CarDTO {
    //bu class faqat malumot olib yurish uchun
    private Integer carId;
    private Integer userId;
    private List<User> users;
}
