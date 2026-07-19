package com.example.traveling.util;

import java.util.Collections;
import java.util.function.Function;
import java.util.List;

public class Mapper {

    private Mapper() {

    }

    public static <S, T> T map(S source, Function<S, T> mapper) {
        if (source == null) {
            return null;
        }
        return mapper.apply(source);
    }

    public static <S, T> List<T> map(List<S> source, Function<S, T> mapper) {
        if (source == null || source.isEmpty()) {
            return Collections.emptyList();
        }
        return source.stream()
                .map(mapper)
                .toList();
    }
}
