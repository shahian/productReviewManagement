package com.shahian.productreviewmanagement.model.entity;

import com.shahian.productreviewmanagement.model.Base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "reviewEnabled")
    private boolean reviewEnabled;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private Set<Review> reviews;


}
