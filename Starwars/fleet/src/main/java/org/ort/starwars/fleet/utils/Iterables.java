package org.ort.starwars.fleet.utils;

import java.lang.reflect.Array;
import java.util.List;
import java.util.stream.StreamSupport;

public class Iterables {

    public static <T> List<T> toList(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false).toList();
    }

    public static <T> T[] toArray(Iterable<T> iterable, Class<T> clazz) {
        var list = toList(iterable);
        var size = list.size();

        @SuppressWarnings("unchecked")
        T[] array = (T[]) Array.newInstance(clazz, size);
        return list.toArray(array);
    }
}