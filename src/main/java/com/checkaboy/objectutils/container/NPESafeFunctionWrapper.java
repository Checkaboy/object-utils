package com.checkaboy.objectutils.container;

import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class NPESafeFunctionWrapper<O, V>
        implements Function<O, V> {

    private final Function<O, V> extractor;

    public NPESafeFunctionWrapper(Function<O, V> extractor) {
        this.extractor = extractor;
    }

    @Override
    public V apply(O o) {
        if (o == null) return null;
        return extractor.apply(o);
    }

}
