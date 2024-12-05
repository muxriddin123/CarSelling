package uz.pdp.spring.homrwork.payload;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PurchaseDTO {
    private Integer userId;
    private Integer carId;
   private Boolean soldCar;
}
