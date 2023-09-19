package com.aston;


import java.util.ArrayList;
import java.util.Arrays;

public class ArrayRun
{
    static <T> T[] concatWithArrayCopy(T[] array1, T[] array2) {
        T[] result = Arrays.copyOf(array1, array1.length + array2.length);
        System.arraycopy(array2, 0, result, array1.length, array2.length);
        return result;
    }
    public static void main( String[] args )
    {


        Integer[] a = {0, 0, 0, 0};
        Integer[] b = {1, 2, 3};
        Integer[] c = concatWithArrayCopy(a, b);
        Integer[] d = Arrays.copyOfRange(a, 0, 1);
        System.out.println(d.length);
        for (Integer i:d) {
            System.out.print(i + " ");
        }

//        ArrayList<Integer> arrayList = new ArrayList<>();
//        System.out.println(arrayList.size());
//        arrayList.add(1);
//        System.out.println(arrayList.size());
//        arrayList.add(1,2);
//        System.out.println(arrayList.size());


    }
}
