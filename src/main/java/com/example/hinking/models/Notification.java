package com.example.hinking.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long notificationID;
    private String content;
    private Instant date;


    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private User recipient;


    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;



    private String type;

}
