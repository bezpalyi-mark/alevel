package com.alevel.java.nix.generics.implementation;

import org.junit.jupiter.api.Test;

import java.net.UnknownServiceException;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class MaxAggregatorTest {

    @Test
    void MaxAggregateTest() {
        class User implements Comparable<User> {
            Comparator<User> COMPARE_BY_NAME = Comparator.comparing(User::getName);
            Comparator<User> COMPARE_BY_AGE = Comparator.comparingInt(User::getAge);

            private String name;

            private Integer age;

            public User(String name, Integer age) {
                this.name = name;
                this.age = age;
            }

            public String getName() {
                return name;
            }

            public Integer getAge() {
                return age;
            }

            @Override
            public int compareTo(User user) {
                return COMPARE_BY_AGE.compare(this, user);
            }
        }

        User user1 = new User("Alex", 23);
        User user2 = new User("Bob", 45);
        User user3 = new User("Gregor", 33);
        User[] users = {user1, user2, user3};

        MaxAggregator<User> userMaxAggregator = new MaxAggregator<>(user1.COMPARE_BY_AGE);
        assertEquals(user2, userMaxAggregator.aggregate(users));

        userMaxAggregator = new MaxAggregator<>(user1.COMPARE_BY_NAME);
        assertEquals(user3, userMaxAggregator.aggregate(users));

        assertThrows(IllegalArgumentException.class,
                () -> new MaxAggregator<User>(user1.COMPARE_BY_AGE).aggregate(null),
                "Array reference of items is null");

        User[] users1 = new User[0];
        assertThrows(IllegalArgumentException.class,
                () -> new MaxAggregator<User>(user1.COMPARE_BY_AGE).aggregate(users1),
                "Array of items have 0 items");
    }
}