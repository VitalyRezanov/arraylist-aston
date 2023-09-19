package com.aston;

import java.util.Iterator;

public class ArrayListIterator<E> implements Iterator<E> {
    private int index = 0;
    E[] values;

    public ArrayListIterator(E[] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return index < values.length;
    }

    @Override
    public E next() {
        return values[index++];
    }

    @Override
    public void remove() {
        //Iterator.super.remove();
    }
}
