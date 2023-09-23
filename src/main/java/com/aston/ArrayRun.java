package com.aston;


import java.util.ArrayList;
import java.util.Arrays;

public class ArrayRun
{
    public static void main( String[] args )
    {

        java.util.ArrayList<Integer> arrayList = new ArrayList<>();
        Integer[] b = {1, 2, 3, 7, 1, 3, 5, 0, 2, 4};
        arrayList.add(14);
        arrayList.add(10);
        arrayList.add(12);
        System.out.println(arrayList.size());
        arrayList.clear();
        System.out.println(arrayList.size());

        //SortArray.sort();
        for (Integer i:b) {
            System.out.println(i + " ");
        }

//        list.sort;
//        ArrayList<Integer>list2 = list;
//        System.out.println(list2.e);
//        System.out.println(list.);
//        list.clear();
//        System.out.println(list);
    }
}
