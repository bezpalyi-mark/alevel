package com.alevel.java.nix.homeworks.lesson12.groups;

import java.util.Iterator;

public class Group implements Iterable<Student> {
    private Student[] students;

    public Group(Student[] students) {
        this.students = students;
    }

    @Override
    public Iterator<Student> iterator() {

        return new Iterator<>() {
            int counter = 0;

            @Override
            public boolean hasNext() {
                return counter != students.length;
            }

            @Override
            public Student next() {
                return students[counter++];
            }
        };
    }
}
