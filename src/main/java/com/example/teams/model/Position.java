package com.example.teams.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Entity(name = "positions")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id")
    long id;

    @Column
    String name;

    @OneToMany(mappedBy = "position", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    List<Player> players;
}
