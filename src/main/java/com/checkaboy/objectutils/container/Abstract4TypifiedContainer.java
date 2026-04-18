package com.checkaboy.objectutils.container;

/**
 * @author Taras Shaptala
 */
public abstract class Abstract4TypifiedContainer<V1, V2, V3, V4>
        implements I4Typified<V1, V2, V3, V4> {

    private final Class<V1> firstValueType;
    private final Class<V2> secondValueType;
    private final Class<V3> thirdValueType;
    private final Class<V4> fourthValueType;

    protected Abstract4TypifiedContainer(Class<V1> firstValueType, Class<V2> secondValueType, Class<V3> thirdValueType, Class<V4> fourthValueType) {
        this.firstValueType = firstValueType;
        this.secondValueType = secondValueType;
        this.thirdValueType = thirdValueType;
        this.fourthValueType = fourthValueType;
    }

    @Override
    public Class<V1> getFirst() {
        return firstValueType;
    }

    @Override
    public Class<V2> getSecond() {
        return secondValueType;
    }

    @Override
    public Class<V3> getThird() {
        return thirdValueType;
    }

    @Override
    public Class<V4> getFourth() {
        return fourthValueType;
    }

}
