package com.alevel.java.nix.annotations.csv;

public class Student {

    @FillByCSV("First name")
    public String firstName;

    @FillByCSV("Last name")
    public String lastName;

    @FillByCSV("SSN")
    public String SSN;

    @FillByCSV("Test1")
    public float test1;

    @FillByCSV("Test2")
    public float test2;

    @FillByCSV("Test3")
    public float test3;

    @FillByCSV("Test4")
    public float test4;

    @FillByCSV("Final")
    public float finalMark;

    @FillByCSV("Grade")
    public String grade;

    public Student() {

    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", SSN='" + SSN + '\'' +
                ", test1=" + test1 +
                ", test2=" + test2 +
                ", test3=" + test3 +
                ", test4=" + test4 +
                ", finalMark=" + finalMark +
                ", grade='" + grade + '\'' +
                '}';
    }
}
