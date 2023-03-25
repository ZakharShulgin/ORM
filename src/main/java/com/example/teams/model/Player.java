package com.example.teams.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity(name = "players")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    long id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "second_name")
    String secondName;

    @Column(name = "last_name")
    String lastName;

    @Column
    int age;

    @Column
    String country;

    @ManyToOne
    @JoinColumn(name="team_id")
    Team team;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="position_id")
    Position position;

    @Column
    int salary;

    @Column(name = "is_leader")
    boolean isLeader;
}
