package com.marshallslee.golangormexampleclientandroid.model;

import com.google.gson.annotations.SerializedName;
import com.marshallslee.golangormexampleclientandroid.consts.Consts;

import lombok.Getter;
import lombok.Setter;

public class Student extends ViewItem {

    @Getter
    @Setter
    @SerializedName(Consts.FIRST_NAME)
    private String firstName;

    @Getter
    @Setter
    @SerializedName(Consts.MIDDLE_NAME)
    private String middleName;

    @Getter
    @Setter
    @SerializedName(Consts.LAST_NAME)
    private String lastName;

    @Getter
    @Setter
    @SerializedName(Consts.STUDENT_NUMBER)
    private String studentNumber;

    @Getter
    @Setter
    @SerializedName(Consts.GENDER)
    private String gender;

    @Getter
    @Setter
    @SerializedName(Consts.MAJOR)
    private String major;

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
