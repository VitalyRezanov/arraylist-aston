package com.aston;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

/**
 * "Класс ArrayList.com.aston - это коллекция для произвольного типа данных. Она имеет приватное поле values,
 *  которое хранит в себе массив произвольного типа, приватное поле size в котором хранится размер коллекции.
 *  Поле emptyData для хранения пустого массива.
 *  Коллекция имеее следующие публичные методы: add(E e), addByIndex(E e, int index), delete(int index), get(int index),
 *  size(), clear(), sort(), sort(Comparator c), toString(). Так же есть статический метод быстрой сортировки sort(ArrayList a),
 *  который находится внутри вложенного статического класса SortArray.
 *  "
 */
public final class ArrayList<E> implements ArrayListInterface<E> {
    private E[] values;
    private int size;
    /**
     * The public name of a hero that is common knowledge
     */
    private static final Object[] emptyData = new Comparable[]{};
    /**
     * Конструктор
     */
    public ArrayList() {
        //values = (E[]) new Comparable[]{};;
        values = (E[]) emptyData;;
        size = 0;
    }
    /**
     * Конструктор который принимает размер для коллекции
     */
    public ArrayList(int s) {
        //values = (E[]) new Object[s];
        values = (E[]) new Comparable[s];
        size = s;
    }
    /**
     * Приватный метод который создает новый массив определенной длины
     */
    private void newArray(int l) {
        values = (E[]) new Comparable[l];
    }
    /**
     * Метод для проверки и изменения размера коллекции
     */
    private void checkReSize() {
        if (values.length > size || size == 0) {
            size++;
        }
        if (values.length < size) {
            size--;
        }
    }
    /**
     * Метод для расширения коллекции, на входе длина для создания нового массива
     */
    private void grow(int l) throws ClassCastException {
        E[] oldArray = values;
        newArray(l);
        System.arraycopy(oldArray, 0, values, 0, oldArray.length);
        checkReSize();
    }
    /**
     * Метод для уменьшения коллекции, на входе длина для создания нового массива
     */
    private void decrease(int l) throws ClassCastException {
        E[] oldArray = values;
        newArray(l);
        System.arraycopy(oldArray, 0, values, 0, oldArray.length - 1);
        checkReSize();
    }
    /**
     * Метод для добавления элемента коллекции, на входе элемент, который нужно добавить
     */
    @Override
    public boolean add(E e) {
        try{
            if (size == values.length){
                grow(values.length + 1);
            }
            values[values.length - 1] = e;
            return true;
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    /**
     * Метод для добавления элемента коллекции по индексу, принимает на вход элемент, который нужно добавить и его индекс
     * возвращает true, если элемент успешно добавлен или false если нет
     */
    @Override
    public boolean addByIndex(E e, int index) {
        checkForAdd(index);
        if (index == values.length) {
            add(e);
            return true;
        }
        grow(values.length + 1);

        if (values.length > index) {
            System.arraycopy(values, index, values, index + 1, values.length - 1 - index);
        }
        values[index] = e;
        return true;
    }
    /**
     * Метод для удаления жлемента коллекции по индексу, принимает на вход индекс удаляемого элемента
     */
    @Override
    public void delete(int index) {
        checkForRemove(index);
        if (index == 0) {
            System.arraycopy(values, index + 1, values, index, values.length - 1 - index);
        }
        if (index < values.length - 1)
            System.arraycopy(values, index + 1, values, index, values.length - 1 - index);
        values[values.length - 1] = null;
        decrease(values.length - 1);
    }
    /**
     * Метод, который возвращает элемент коллекции по индексу, принимает на вход индекс
     */
    @Override
    public E get(int index) {
        checkForAdd(index);
        return values[index];
    }
    /**
     * Метод, который возвращает размер коллекции
     */
    @Override
    public int size() {
        return size;
    }
    /**
     * Метод для очищения коллекции от элементов
     */
    @Override
    public void clear() {
        values = (E[])emptyData;
        size = 0;
    }
    /**
     * Метод для сортировки коллекции по компаратору, на вход принимает компаратор
     */
    public void sort(Comparator<? super E> c) {
        Arrays.sort(values, c);
    }
    /**
     * Метод для натуральной сортировки
     */
    @Override
    public void sort() {
        Arrays.sort(values);
    }
    /**
     * Метод для замены элемента коллекции по индексу, на вход принимает индекс и элемент, на который нужно заменить старый
     */
    @Override
    public void replacement(int index, E e) {
        checkForRemove(index);
        if (e != null)
            values[index] = e;
    }
    /**
     * Метод итератор, возвращает итератор для коллекции
     */
    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator<E>(values);
    }
    /**
     * Метод проверки индекса для добавления в коллекцию, принимает на вход индекс
     */
    private void checkForAdd(int index) {
        if (index > values.length || index < 0)
            throw new IndexOutOfBoundsException(indexOutOfBounds(index));
    }
    /**
     * Метод проверки индекса для удаления из коллекции, принимает на вход индекс
     */
    private void checkForRemove(int index) {
        if (index >= values.length || index < 0)
            throw new IndexOutOfBoundsException(indexOutOfBounds(index));
    }
    /**
     * Метод, который вызывается при создании исключения, выход за пределы размеров коллекции, принимает на вход индекс.
     * Возвращает строку с описанием исключения
     */
    private String indexOutOfBounds(int index) {
        return "Index: " + index + ", Size: " + size;
    }
    /**
     * Метод для строкового представления коллекции, возвращает строку
     */

    @Override
    public String toString() {

        String s = "";
        for (E e:values) {
            s += "["+ e + "]";
        }
        return s;
    }
    /**
     * Внутренний класс для алгоритма быстрой сортировки. Имеет перегруженные публичные методы sort(ArrayList a, Comparator c),
     * sort(ArrayList a), предназначенные для сортировки коллекции.
     * Так же приватные перегруженные методы quickSort() корневой метод алгоритма сортировки,
     * partition() методы для деления коллекции в определенном промежутке,
     * swap() метод, который меняет местами элементы коллекции,
     * compareValues() методы для сравнения элементов коллекции
     */
    public static final class SortArray {
        /**
         * Публичный метод для вызова сортировки коллекции, на входе ArrayList
         */
        public static <E extends Comparable<E>> void sort(ArrayList<E> a) {
            if (a == null) {
                throw new NullPointerException();
            }
            E[] array = a.values;
            quickSort(array, 0, array.length - 1);
        }
        public static <E extends Comparable<E>, T extends Comparator<E>> void sort(ArrayList<E> a, T c) {
            if (a == null) {
                throw new NullPointerException();
            }
            E[] array = a.values;
            quickSort(array, 0, array.length - 1, c);
        }
        /**
         * Крневой приватный статический метод алгоритма быстрой соритровки, перегруженный для возможности
         * сортировки по компаратору
         */
        private static <E extends Comparable<E>> void quickSort(E[] array, int left, int right) {
            int index = 0;
            if (array.length > 1) {
                index = partition(array, left, right);
                if (left < index - 1) {
                    quickSort(array, left, index - 1);
                }
                if (index < right) {
                    quickSort(array, index, right);
                }
            }
        }
        private static <E extends Comparable<E>, T extends Comparator<E>> void quickSort(E[] array, int left, int right, T c) {
            int index = 0;
            if (array.length > 1) {
                index = partition(array, left, right, c);
                if (left < index - 1) {
                    quickSort(array, left, index - 1, c);
                }
                if (index < right) {
                    quickSort(array, index, right, c);
                }
            }
        }
        /**
         * Метод для разделения коллекции согласно алгоритму быстрой сортировки, за опорный разделитель
         * берется середина коллекции
         */
        private static <E extends Comparable<E>> int partition(E[] array, int left, int right) {
            E pivot = array[(right + left) / 2];
            while (left <= right) {
                while (compareValues(array[left], pivot) < 0) {
                    left++;
                }
                while (compareValues(array[right], pivot) > 0) {
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
        private static <E extends Comparable<E>, T extends Comparator<E>>  int partition(E[] array, int left, int right, T c) {
            E pivot = array[(right + left) / 2];
            while (left <= right) {
                //while (array[left].compareTo(pivot) < 0) {
                while (compareValues(array[left], pivot, c) < 0) {
                    left++;
                }
                //while (array[right].compareTo(pivot) > 0) {
                while (compareValues(array[right], pivot, c) > 0) {
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
        /**
         * Метод, который меняет местами два элемента коллекции
         */
        private static <E extends Comparable<E>> void swap(E[] array, int firstIndex, int secondIndex) {
            E temp = array[firstIndex];
            array[firstIndex] = array[secondIndex];
            array[secondIndex] = temp;
        }
        /**
         * Метод обертка для сравнения элементов коллекции с компаратором или без
         */
        private static <E extends Comparable<E>> int compareValues(E firstElement, E secondElement) {
            return firstElement.compareTo(secondElement);

        }
        private static  <E extends Comparable<E>, T extends Comparator<E>> int compareValues(E firstElement, E secondElement, T c) {
            if (c == null) {
                return compareValues(firstElement, secondElement);
            }
            return c.compare(firstElement, secondElement);
        }
    }
}

