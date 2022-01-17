package com.buraktuysuz.fourthhomework.entitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "debtor_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements  BaseEntity{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;
    private String phoneNumber;
    private String address;

}
