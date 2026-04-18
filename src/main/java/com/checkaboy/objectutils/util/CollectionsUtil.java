package com.checkaboy.objectutils.util;

import java.util.*;
import java.util.function.*;

public class CollectionsUtil {

    public static <E, V> void setValueIntoCollectionElementsWithFilter(
            Collection<E> inputCollection,
            V value,
            BiConsumer<E, V> setter,
            Predicate<E> filter
    ) {
        if (inputCollection == null) return;
        inputCollection.forEach(e -> {
            if (filter.test(e))
                setter.accept(e, value);
        });
    }

    public static <E, V> void setValueIntoAllCollectionElements(
            Collection<E> inputCollection,
            V value,
            BiConsumer<E, V> setter
    ) {
        if (inputCollection == null) return;
        inputCollection.forEach(e ->
                setter.accept(e, value)
        );
    }

    // =================================================================================================================

    public static <C extends Collection<S>, S, V> C getSubClassCollectionWithNull(
            Collection<V> inputCollection,
            Function<V, S> subclassExtractor,
            Function<Integer, C> collectionConstructorWithSize
    ) {
        if (inputCollection == null) return null;
        C c = collectionConstructorWithSize.apply(inputCollection.size());
        inputCollection.forEach(v ->
                c.add(subclassExtractor.apply(v))
        );
        return c;
    }

    public static <C extends Collection<S>, S, V> C getSubClassCollectionWithoutNull(
            Collection<V> inputCollection,
            Function<V, S> subclassExtractor,
            Function<Integer, C> collectionConstructorWithSize
    ) {
        return getSubClassCollection(inputCollection, subclassExtractor, collectionConstructorWithSize, false);
    }

    public static <C extends Collection<S>, S, V> C getSubClassCollection(
            Collection<V> inputCollection,
            Function<V, S> subclassExtractor,
            Function<Integer, C> collectionConstructorWithSize,
            boolean addNull
    ) {
        return getSubClassCollectionWithFilter(inputCollection, subclassExtractor, collectionConstructorWithSize, s -> addNull || s != null);
    }

    /**
     * @param inputCollection               input collection with V type values
     * @param subclassExtractor             subclass S extractor from V value
     * @param collectionConstructorWithSize constructor for C collection with size param
     * @param filter                        additional filter for S subclass
     * @param <C>                           return collection type
     * @param <S>                           subclass type
     * @param <V>                           value type
     * @return new collection with subclasses from input collection
     */
    public static <C extends Collection<S>, S, V> C getSubClassCollectionWithFilter(
            Collection<V> inputCollection,
            Function<V, S> subclassExtractor,
            Function<Integer, C> collectionConstructorWithSize,
            Predicate<S> filter
    ) {
        if (inputCollection == null)
            return null;

        C c = collectionConstructorWithSize.apply(inputCollection.size());

        inputCollection.forEach(v -> {
            S s = subclassExtractor.apply(v);
            if (filter.test(s))
                c.add(s);
        });

        return c;
    }

    public static <C extends Collection<P>, P extends Collection<V>, V> C paginateCollection(
            Collection<V> inputCollection,
            Function<Integer, C> containerCollectionConstructorWithSize,
            Function<Integer, P> pageCollectionConstructorWithSize,
            int pageSize
    ) {
        if (pageSize <= 0) throw new IllegalArgumentException("Page size must be greater than 0");
        if (inputCollection == null) return null;

        int countPages = (inputCollection.size() + pageSize - 1) / pageSize;
        C pages = containerCollectionConstructorWithSize.apply(countPages);

        Iterator<V> iterator = inputCollection.iterator();
        while (iterator.hasNext()) {
            P page = pageCollectionConstructorWithSize.apply(pageSize);
            for (int i = 0; i < pageSize && iterator.hasNext(); i++) {
                page.add(iterator.next());
            }
            pages.add(page);
        }

        return pages;
    }

    public static <C extends Collection<V>, V> C findMissingObjects(
            Collection<V> source,
            Collection<V> target,
            Function<Integer, C> collectionConstructorWithSize
    ) {
        return findMissingObjects(source, target, collectionConstructorWithSize, (BiFunction<V, V, Boolean>) Objects::equals);
    }

    public static <C extends Collection<V>, V> C findMissingObjects(
            Collection<V> source,
            Collection<V> target,
            Function<Integer, C> collectionConstructorWithSize,
            BiFunction<V, V, Boolean> comparator
    ) {
        C dump = collectionConstructorWithSize.apply(source.size());
        dump.addAll(source);

        for (V targetValue : target) {
            Iterator<V> iterator = dump.iterator();
            while (iterator.hasNext()) {
                V sourceValue = iterator.next();
                if (BooleanUtil.is(comparator.apply(sourceValue, targetValue))) {
                    iterator.remove();
                    break;
                }
            }
        }

        return dump;
    }

    public static <C extends Collection<V>, V> C findMissingObjects(
            Collection<V> source,
            Collection<V> target,
            Function<Integer, C> collectionConstructorWithSize,
            Comparator<V> comparator
    ) {
        C dump = collectionConstructorWithSize.apply(source.size());
        dump.addAll(source);

        for (V targetValue : target) {
            Iterator<V> iterator = dump.iterator();
            while (iterator.hasNext()) {
                V sourceValue = iterator.next();
                if (comparator.compare(sourceValue, targetValue) != 0) {
                    iterator.remove();
                    break;
                }
            }
        }

        return dump;
    }

    public static <C extends Collection<V>, V, F> C findDiffByFeature(
            C source,
            C target,
            Supplier<C> collectionConstructor,
            Function<V, F> featureExtractor,
            BiFunction<V, V, Boolean> comparator
    ) {
        C result = collectionConstructor.get();

        for (V sourceValue : source) {
            F feature = featureExtractor.apply(sourceValue);
            if (feature == null) continue;

            C foundElements = findObjectsByFeature(
                    target,
                    featureExtractor,
                    collectionConstructor,
                    f -> Objects.equals(f, feature)
            );

            if (foundElements.size() == 1) {
                V targetValue = foundElements.iterator().next();
                if (!comparator.apply(targetValue, sourceValue)) {
                    result.add(sourceValue);
                }
            } else if (foundElements.size() > 1) {
                throw new IllegalArgumentException("Identifier comparator found more than 1 object for feature: " + feature);
            }
        }

        return result;
    }

    public static <C extends Collection<V>, F, V> C findObjectsByFeature(
            Collection<V> collection,
            Function<V, F> extractor,
            Supplier<C> collectionConstructor,
            Predicate<F> filter
    ) {
        if (collection == null)
            return null;

        C c = collectionConstructor.get();

        collection.forEach(v -> {
            F i = extractor.apply(v);
            if (filter.test(i))
                c.add(v);
        });

        return c;
    }

}
