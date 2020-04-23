package com.alevel.java.nix.annotations.csv;

import com.alevel.java.nix.ionio.HandlerCSV;

import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

public class AppStudents {
    public static void main(String[] args) throws NoSuchFileException {
        HandlerCSV handlerCSV = new HandlerCSV(Paths.get("core/src/main/resources/fileToReadCSV.csv"));
        InitFromCSV<Student> initializer = new InitFromCSV<>(handlerCSV, Student.class);
        List<Student> students = initializer.getList();
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
