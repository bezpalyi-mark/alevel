package com.alevel.java.nix.stream;

import java.time.Year;
import java.util.Collection;
import java.util.stream.Collectors;

public class LeapYears {
    public Collection<Year> getLeapYears(Collection<Year> years) {
        return years.stream().filter(Year::isLeap).sorted().collect(Collectors.toList());
    }
}
