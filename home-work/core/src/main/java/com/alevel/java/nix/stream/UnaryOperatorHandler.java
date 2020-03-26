package com.alevel.java.nix.stream;

import java.util.Collection;
import java.util.function.UnaryOperator;
import static java.util.stream.Collectors.*;

public class UnaryOperatorHandler {
    public <T> UnaryOperator<T> union(Collection<UnaryOperator<T>> operators) {
        return operators.stream()
                .reduce((p, b) -> (t) -> p.apply(b.apply(t))).orElse((t) -> t);
    }
}
