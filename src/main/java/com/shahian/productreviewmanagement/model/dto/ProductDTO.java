package com.shahian.productreviewmanagement.model.dto;


import com.shahian.productreviewmanagement.model.Base.BaseEntityDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

public class ProductDTO extends BaseEntityDTO {
    private Long id;
    private String name;
    private Double price;
    private boolean reviewEnabled;
    private Double averageRating;
    private Long reviewCount;
}
