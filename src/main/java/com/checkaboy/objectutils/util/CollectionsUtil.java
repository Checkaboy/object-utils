package com.checkaboy.objectutils.util;

import com.checkaboy.objectutils.model.Identifier;

import java.util.*;
import java.util.function.Function;

public class CollectionsUtil {

    /**
     * Из списка идентифицируемых объектов создается список идентификаторов
     *
     * @param collection список объектов
     * @return список идентификаторов
     */
    public static <G, T extends Identifier<G>> List<G> buildListIds(Collection<T> collection) {
        List<G> c = new ArrayList<>();
        if (collection == null)
            return c;

        collection.forEach(t -> {
            if (t.getId() != null)
                c.add(t.getId());
        });

        return c;
    }

    /**
     * Из списка идентифицируемых объектов создается список идентификаторов не приведенного типа
     *
     * @param collection список объектов
     * @return список идентификаторов
     */
    public static List<Object> buildObjectListIds(Collection<? extends Identifier<?>> collection) {
        List<Object> c = new ArrayList<>();
        if (collection == null)
            return c;

        collection.forEach(t -> {
            if (t.getId() != null)
                c.add(t.getId());
        });

        return c;
    }

    public static <G, T extends Identifier<G>> List<T> buildEmptyObjects(Collection<G> collection, Function<G, T> creator) {
        List<T> c = new ArrayList<>();
        if (collection == null)
            return c;

        collection.forEach(t -> {
            if (t != null)
                c.add(creator.apply(t));
        });

        return c;
    }

    public static <T> List<List<T>> paginateList(List<T> inputList, int pageSize) {
        List<List<T>> pages = new ArrayList<>();
        if (pageSize <= 0) throw new IllegalArgumentException("Page size must be greater than 0");

        for (int i = 0; i < inputList.size(); i += pageSize) {
            int end = Math.min(i + pageSize, inputList.size());
            pages.add(new ArrayList<>(inputList.subList(i, end)));
        }

        return pages;
    }

}
