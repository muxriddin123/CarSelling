package uz.pdp.spring.homrwork.enttiy;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//getter,setter,toString
@Data
//hamma fildlar cuhun constructor
@AllArgsConstructor
//default constructor
@NoArgsConstructor
//databaseda tebl sifatida ochish  uchun
@Entity
//bu databasda tablni shu nom bilan ochishda ishlatiladi
@Table(name = "users")
public class User {
    //databasda shu fildni id sifatida belgilash uchun
    @Id
    //shu idga ozi raqam berish uchun
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(unique = true, nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String email;
//BU rolni enum yoki strungda oqish uchun
    @Enumerated
    @JsonIgnore
    private Role role;

}
