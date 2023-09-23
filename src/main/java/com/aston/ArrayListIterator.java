package com.aston;

import java.util.Iterator;

public class ArrayListIterator<E> implements Iterator<E> {
    private int index = 0;
    E[] list;

    public ArrayListIterator(E[] values) {
        this.list = values;
    }

    @Override
    public boolean hasNext() {
        return index < list.length;
    }

    @Override
    public E next() {
        return list[index++];
    }

    @Override
    public void remove() {
    }

}
