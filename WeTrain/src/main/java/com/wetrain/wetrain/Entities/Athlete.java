package com.wetrain.wetrain.Entities;

import java.time.LocalDate;

public class Athlete extends User {

    public Athlete(String name, LocalDate dateOfBirth, String fc, String email){
        super(name, dateOfBirth, fc, email);
    }
}
