package com.wetrain.wetrain.entities;

import java.time.LocalDate;

public abstract class User {
    private String name;
    private LocalDate dateOfBirth;
    private String fiscalCode;
    private String email;

    protected User(String name, LocalDate dateOfBirth, String fc, String email){
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.fiscalCode = fc;
        this.email = email;
    }
}
