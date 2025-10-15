package com.checkaboy.objectutils.container;

/**
 * @author Taras Shaptala
 */
public abstract class AbstractBiTypifiedContainer<S, T>
        implements IBiTypified<S, T> {

    private final Class<S> sourceType;
    private final Class<T> targetType;

    protected AbstractBiTypifiedContainer(Class<S> sourceType, Class<T> targetType) {
        this.sourceType = sourceType;
        this.targetType = targetType;
    }

    @Override
    public Class<S> getSourceType() {
        return sourceType;
    }

    @Override
    public Class<T> getTargetType() {
        return targetType;
    }

}
