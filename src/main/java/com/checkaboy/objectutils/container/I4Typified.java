package com.checkaboy.objectutils.container;

/**
 * @author Taras Shaptala
 */
public interface I4Typified<V1, V2, V3, V4> {

    Class<V1> getFirst();

    Class<V2> getSecond();

    Class<V3> getThird();

    Class<V4> getFourth();

}
