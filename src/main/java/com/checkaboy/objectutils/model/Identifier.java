package com.checkaboy.objectutils.model;

/**
 * Интерфейс для реализации идентификации сущностей
 *
 * @param <T> тип идентификатора
 *            определяет тип идентификатора в БД и в java классах
 * @author Shaptala Taras
 */
public interface Identifier<T> {

    T getId();

    void setId(T id);

}
