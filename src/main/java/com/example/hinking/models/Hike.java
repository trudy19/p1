package com.example.hinking.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity


@Table(name = "hikes")
public class Hike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long hikeID;
    private String name;
    private String description;
    private String difficulty;
    private Instant dateTime;
    private String startPoint;
    private String endPoint;
    private Float distance;
    private Float duration;
    private String visibiliy;


    @ManyToOne
    @JoinColumn(name = "userID") // C'est ici que se trouve la clé étrangère
    private User Creator ;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
}
