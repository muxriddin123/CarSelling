package uz.pdp.spring.homrwork.payload;

import lombok.Data;

@Data
public class AuthDTO {
    //bu class faqat malumot olib yurish uchun
    private String email;
    private String password;

}
