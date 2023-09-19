package com.aston;


import java.util.Arrays;
import java.util.Iterator;

public class ArrayList<E> implements ArrayListInterface<E> {
    private E[] values;
    private int size;
    //private final int initialCapacity = 10;

    private static final Object[] emptyData = {};

    public ArrayList() {
        values = (E[]) emptyData;
        size = 0;
    }

    public ArrayList(int s) {
        values = (E[]) new Object[s];
        size = s;
    }

    private void newArray(int l) {
        values = (E[]) new Object[l];
    }
    private void checkReSize(int s) {
        if (values.length > size / 2) {
            size += s / 2;
        }

    }
    private void grow(int l) throws ClassCastException {
        E[] oldArray = values;
        newArray(l);

        System.arraycopy(oldArray, 0, values, 0, oldArray.length);
    }
    @Override
    public boolean add(E e) {
        try{
            if (size == values.length){
                //E[] temp = values;
                //values = (E[]) new Object[temp.length + 1];
                //newSize();
                //size += size / 2;
                //System.arraycopy(temp, 0, values, 0, temp.length);
                grow(values.length + 1);
                checkReSize(size); // Подумать над этим!!!
            }
            values[values.length - 1] = e;
            return true;
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean addByIndex(E e, int index) {
        checkForAdd(index);
       // if (index > size || index < 0) {
       //     return false;
        //}
        if (index == values.length - 1) {
            add(e);
            return true;
        }

        //int tempSize = size;
        E[] leftTemp = Arrays.copyOfRange(values, 0, index);
        E[] rightTemp = Arrays.copyOfRange(values, index, values.length);
        values = leftTemp;
        //size = values.length;;
        add(e);
        addAll(rightTemp);
        return true;
    }
    public boolean addAll(E[] array) {
        if (array.length >= size - values.length) {
            grow(values.length + array.length);
            checkReSize(size + array.length);
            //concatArrays(array);

        }
        concatArrays(array);
        return true;
    }

    @Override
    public void delete(int index) {

    }

    @Override
    public E get(int index) {
        return values[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void update(int index, E e) {
        values[index] = e;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator<E>(values);
    }
    private void checkForAdd(int index) {
        if (index > values.length - 1 || index < 0)
            throw new IndexOutOfBoundsException(indexOutOfBounds(index));
    }
    private String indexOutOfBounds(int index) {
        return "Index: " + index + ", Size: " + size;
    }
    private void concatArrays(E[] array) {
        System.arraycopy(array, 0, values, values.length, array.length);
    }
}
