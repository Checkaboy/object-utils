package com.checkaboy.objectutils.model;

import java.util.Objects;

/**
 * @author Taras Shaptala
 */
public abstract class AbstractIdentifier<T>
        implements Identifier<T>, Comparable<AbstractIdentifier<T>> {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractIdentifier<?> that = (AbstractIdentifier<?>) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public int compareTo(AbstractIdentifier o) {
        return hashCode() - o.hashCode();
    }

}
