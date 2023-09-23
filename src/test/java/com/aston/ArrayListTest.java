package com.aston;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class ArrayListTest {
    private ArrayList<Integer> testArrayList = new ArrayList<Integer>();

    private void initialArray() {
        testArrayList.add(15);
        testArrayList.add(12);
        testArrayList.add(104);
        testArrayList.add(12);
    }
    private void initialBigArray() {
        for (int i = 0; i< 10000; i++) {
            testArrayList.add((int)(Math.random() * 10000));
        }
    }

    @Test
    public void add() {
        initialArray();
        testArrayList.clear();
        initialArray();
        System.out.println(testArrayList.size());
        testArrayList.delete(3);
        System.out.println("Size arraylist " + testArrayList.size());
        testArrayList.delete(0);
        System.out.println("Size arraylist " + testArrayList.size());
        testArrayList.delete(1);
        System.out.println("Size arraylist " + testArrayList.size());
    }

    @Test
    public void addByIndex() {
        initialArray();
        testArrayList.addByIndex(77, 2);
        testArrayList.addByIndex(7, 0);
        testArrayList.addByIndex(777, 6);
    }

    @Test
    public void delete() {
        initialArray();
        testArrayList.delete(3);
        System.out.println("Size arraylist " + testArrayList.size());
        testArrayList.delete(0);
        System.out.println("Size arraylist " + testArrayList.size());
        testArrayList.delete(1);
        System.out.println("Size arraylist " + testArrayList.size());
    }

    @Test
    public void get() {
        initialArray();
        System.out.println(testArrayList.toString());
        System.out.println(testArrayList.get(2));
        System.out.println(testArrayList.get(0));
        System.out.println(testArrayList.get(3));
        //System.out.println(testArrayList.get(10));
    }

    @Test
    public void size() {
        System.out.println(testArrayList.size());
        initialArray();
        System.out.println(testArrayList.size());
    }

    @Test
    public void clear() {
        initialArray();
        System.out.println(testArrayList.size());
        testArrayList.clear();
        System.out.println(testArrayList.size());
    }

    @Test
    public void sort() {
        System.out.println(testArrayList.toString());
        initialBigArray();
        testArrayList.sort();
        System.out.println(testArrayList.toString());
    }

    @Test
    public void testSort() {
        initialBigArray();
        //System.out.println(testArrayList.toString());

        ArrayList.SortArray.sort(testArrayList);
        System.out.println(testArrayList.toString());
    }

    @Test
    public void replacement() {
        initialArray();
        System.out.println(testArrayList.toString());
        testArrayList.replacement(2, 5);
        System.out.println(testArrayList.toString());
    }

    @Test
    public void iterator() {

    }
}