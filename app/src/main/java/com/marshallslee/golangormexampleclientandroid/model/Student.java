package com.marshallslee.golangormexampleclientandroid.model;

import lombok.Getter;
import lombok.Setter;

public class Student extends ViewItem {

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

    @Override
    public int getListItemType() {
        return 0;
    }
}
