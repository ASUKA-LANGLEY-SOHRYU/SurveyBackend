package com.prosvirnin.alphabackend.model.user;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Person {
    private Sex sex;
    private Date dateOfBirth;

    private EducationLevel educationLevel;

    private Income income;

    private Address address;
}
