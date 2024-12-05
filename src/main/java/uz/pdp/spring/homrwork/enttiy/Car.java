package uz.pdp.spring.homrwork.enttiy;
import com.fasterxml.jackson.annotation.JsonIgnore;
import uz.pdp.spring.homrwork.enttiy.User;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//databaseda tebl sifatida ochish uchum
@Entity
//qiymat qabul qilmadigan constructor
@NoArgsConstructor
//hamma fildalar uchun constructor
@AllArgsConstructor
// detter,setter toString uchun
@Data


public class Car {
    //databaseda idni id qilib belishlash uchun
    @Id
    //id columinga kema ket raqamlar berish uchun
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String brand;
    private String model;
    private String color;
    private Double price;
    private Boolean sold;

     @ManyToOne
     @JsonIgnore
     private User owner;
}
