package com.example.hinking.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    private String name;

    @OneToMany(mappedBy = "recipient")
    private Set<Notification> notifications;

    private String email ;
    private String password;
    private String profilePic;

    @ManyToMany(mappedBy ="members")
    private Set<Group> groups ;

    @OneToMany(mappedBy = "sender")
    private Set<Message> messages;




    @OneToMany(mappedBy = "Creator", cascade = CascadeType.ALL)
    private Set<Hike> hikesCreated;

}
