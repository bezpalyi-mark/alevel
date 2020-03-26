package com.alevel.java.nix.stream;

import java.util.Collection;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCutter {
    public Collection<String> cutMatchesRegions(Collection<String> strings, Pattern regex) {
        return strings.stream().map(s -> regex.matcher(s).replaceAll("")).collect(Collectors.toList());
    }
}
