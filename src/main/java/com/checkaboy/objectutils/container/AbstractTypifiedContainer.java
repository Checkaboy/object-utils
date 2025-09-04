package com.checkaboy.objectutils.container;

/**
 * @author Taras Shaptala
 */
public abstract class AbstractTypifiedContainer<O>
        implements ITypified<O> {

    private final Class<O> type;

    protected AbstractTypifiedContainer(Class<O> type) {
        this.type = type;
    }

    @Override
    public Class<O> getType() {
        return type;
    }

}
