package com.marshallslee.golangormexampleclientandroid;

import lombok.Getter;
import lombok.Setter;

public class Student {

    @Getter
    @Setter
    private String firstName, middleName, lastName, studentNumber, gender, major;

    public Student(String firstName, String middleName, String lastName,
                   String studentNumber, String gender, String major) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.studentNumber = studentNumber;
        this.gender = gender;
        this.major = major;
    }
}
