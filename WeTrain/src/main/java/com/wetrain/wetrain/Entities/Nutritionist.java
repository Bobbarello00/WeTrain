package com.wetrain.wetrain.Entities;

import java.time.LocalDate;

public class Nutritionist extends User {

    public Nutritionist(String name, LocalDate dateOfBirth, String fc, String email){
        super(name, dateOfBirth, fc, email);
    }
}
