package com.checkaboy.objectutils.container;

/**
 * @author Taras Shaptala
 */
public interface IBiTypified<S, T> {

    Class<S> getSourceType();

    Class<T> getTargetType();

}
