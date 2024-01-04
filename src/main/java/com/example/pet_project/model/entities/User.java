package com.example.pet_project.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name="user_generator", sequenceName = "seq_user_id", allocationSize=1)
    private Long id;

    private String userName;

    private String password;

    private String firstName;

    private String lastName;

    private String address;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false, foreignKey = @ForeignKey(name = "user_city_fk"))
    private City city;

}
