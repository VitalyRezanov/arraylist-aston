package com.aston;

public interface ArrayListInterface<E> extends Iterable<E> {
    boolean add(E e);
    boolean addByIndex(E e, int index);
    void delete(int index);
    E get(int index);
    int size();
    void update(int index, E e);

}
