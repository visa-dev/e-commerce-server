package com.fast_food.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private  Long id;

    @ManyToOne(cascade =CascadeType.ALL ,optional = true)
    private User owner;

    private String name;

    private String description;
    private String cuisineType;

    @OneToOne
    private Address address;

    @Embedded //part of restaurant entity
    private ContactInformation contactInformation;

    private String openingHours;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL ,orphanRemoval = true)
    private List<Order> orders=new ArrayList<>();

    @ElementCollection
    @Column(length = 1000)
    @Cascade(value = org.hibernate.annotations.CascadeType.REMOVE)
    private  List<String> images;

    private LocalDateTime registrationDateTime;

    private boolean open;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL,orphanRemoval = true)
    private  List<Food> foods=new ArrayList<>();

    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Event> events=new ArrayList<>();

}
