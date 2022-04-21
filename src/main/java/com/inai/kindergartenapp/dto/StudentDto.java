package com.inai.kindergartenapp.dto;

import com.inai.kindergartenapp.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String accountType;

}

//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//public class DishDto {
//    public static DishDto from(Dish dish){
//        return builder()
//                .id(dish.getId())
//                .name(dish.getName())
//                .type(dish.getType())
//                .price(dish.getPrice())
//                .place(dish.getPlace())
//                .build();
//    }
//
//    private Long id;
//    private String name;
//    private String type;
//    private Double price;
//    private Place place;
//
//
//}
