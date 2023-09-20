package com.aston;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class ArrayList<E extends Comparable<E>> implements ArrayListInterface<E> {
    private E[] values;
    private int size;

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
    private void checkReSize() {
        if (values.length > size / 2) {
            size += size / 2;
        }
    }
    private void grow(int l) throws ClassCastException {
        E[] oldArray = values;
        newArray(l);
        System.arraycopy(oldArray, 0, values, 0, oldArray.length);
        checkReSize();
    }
    @Override
    public boolean add(E e) {
        try{
            if (size == values.length){
                grow(values.length + 1);
                //checkReSize(); // Подумать над этим!!!
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
        if (index == values.length) {
            add(e);
            return true;
        }
        grow(values.length + 1);
        if (values.length - 1 - index >= 0)
            System.arraycopy(values, index, values, index + 1, values.length - index);
        values[index] = e;
        return true;
    }

    @Override
    public void delete(int index) {
        checkForRemove(index);
        grow(values.length + 1);
        if (values.length - 1 - index >= 0)
            System.arraycopy(values, index + 1, values, index, values.length - 1 - index);
    }

    @Override
    public E get(int index) {
        checkForAdd(index);
        return values[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(values, null);
    }

    public void sort(Comparator<E> c) {
        Arrays.sort(values, c);
    }

    @Override
    public void sort() {
        Arrays.sort(values);
    }

    @Override
    public void replacement(int index, E e) {
        checkForRemove(index);
        if (e != null)
            values[index] = e;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator<E>(values);
    }
    private void checkForAdd(int index) {
        if (index > values.length || index < 0)
            throw new IndexOutOfBoundsException(indexOutOfBounds(index));
    }
    private void checkForRemove(int index) {
        if (index >= values.length || index < 0)
            throw new IndexOutOfBoundsException(indexOutOfBounds(index));
    }
    private String indexOutOfBounds(int index) {
        return "Index: " + index + ", Size: " + size;
    }
    public E[] toArray() {
        return values;
    }
    public void quickSort() {
        if (values == null) {
            throw new NullPointerException();
        }
        //E[] array = values;
        qSort(values, 0, values.length - 1);
    }
    private void qSort(E[] array, int left, int right) {
        int index = 0;
        if (array.length > 1) {
            index = partition(array, left, right);
            if (left < index - 1) {
                qSort(array, left, index - 1);
            }
            if (index < right) {
                qSort(array, index, right);
            }
        }
    }
    private void swap(E[] array, int firstIndex, int secondIndex) {
        E temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }
    private int partition(E[] array, int left, int right) {
        E pivot = array[(right + left) / 2];
        while (left <= right) {
            while (array[left].compareTo(pivot) < 0) {
                left++;
            }
            while (array[right].compareTo(pivot) > 0) {
                right--;
            }
            if (left <= right) {
                swap(array, left, right);
                left++;
                right--;
            }
        }
        return left;
    }
}
