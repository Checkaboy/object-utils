package com.checkaboy.objectutils.util;

public class SimpleStringConverter<I> {

    public String toString(I item) {
        if (item == null)
            return returnIfNull();
        return parseObject(item);
    }

    protected String returnIfNull() {
        return "";
    }

    protected String parseObject(I item) {
        return item.toString();
    }

}
