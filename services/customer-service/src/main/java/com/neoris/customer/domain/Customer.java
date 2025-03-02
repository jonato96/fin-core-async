package com.neoris.customer.domain;

import com.neoris.customer.helper.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Table(name = "customer")
public class Customer extends Person {

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private boolean active;

    public Customer(Integer id, String name, Gender gender, Integer age, String identification, String address, String phone, String password, boolean active) {
        super(id, name, gender, age, identification, address, phone);
        this.password = password;
        this.active = active;
    }

}
