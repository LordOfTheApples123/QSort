package com.csf.halilov;


import java.util.Arrays;
import java.util.Random;

public class Main {

    public static long count = 0;

    public static void main(String[] args) {
        int[] arr = getRandomArray();
        test(arr);

    }

    public static void printArray(int[] arr){
        for(int num: arr){
            System.out.println(num);
        }
    }

    private static int[] getRandomArray(){
        Random rnd = new Random();
        int[] arr = new int[100];
        for(int i = 0; i < arr.length; i++){
            arr[i] = rnd.nextInt(1000);
        }
        return arr;
    }



    public static void test(int[] a) {
        System.out.println("Опорный элемент: первый");
        int[] b = a.clone();
        int[] c = a.clone();
        Arrays.sort(c);
        sort(b, 0, b.length-1, 0);
        System.out.println("Массив отсортирован: " + Arrays.equals(b, c));
        System.out.println("Количество сравнений: " + count);
        count = 0;
        System.out.println("Опорный элемент: последний");
        b = a.clone();
        sort(b, 0, b.length-1, 1);
        System.out.println("Массив отсортирован: " + Arrays.equals(b, c));
        System.out.println("Количество сравнений: " + count);
        count = 0;
        System.out.println("Опорный элемент: случайный");
        b = a.clone();
        sort(b, 0, b.length-1, 2);
        System.out.println("Массив отсортирован: " + Arrays.equals(b, c));
        System.out.println("Количество сравнений: " + count);
        count = 0;
        System.out.println("Опорный элемент: медиана");
        b = a.clone();
        sort(b, 0, b.length-1, 3);
        System.out.println("Массив отсортирован: " + Arrays.equals(b, c));
        System.out.println("Количество сравнений: " + count);
        count = 0;
    }


    public static int partition(int[] a, int left, int right, int pivot) {
        int pivotIndex = choosePivot(a, left, right, pivot);
        int pivotValue = a[pivotIndex];
        swap(a, pivotIndex, right);  // Move pivot to end
        int pivotPlace = left;
        for (int i = left; i <= right; i++) {
            count++;
            if (a[i] < pivotValue) {
                swap(a, pivotPlace, i);
                pivotPlace++;
            }
        }
        swap(a, right, pivotPlace);
        return pivotPlace;
    }

    public static void sort(int[] a, int left, int right, int pivot) {
        if (left >= right) return;
        int index = partition(a, left, right, pivot);
        sort(a, left, index - 1, pivot);
        sort(a, index + 1, right, pivot);


    }

    public static void swap(int[] a, int i1, int i2) {
        int tmp = a[i1];
        a[i1] = a[i2];
        a[i2] = tmp;
    }

    private static int choosePivot(int[] arr, int left, int right, int pivot) {
        Random rnd = new Random();
        switch (pivot) {
            case 0:
                return left;
            case 1:
                return right;
            case 2:
                return rnd.nextInt(right - left + 1) + left;
            case 3:
                int a = arr[left];
                int b = arr[(left + right) / 2];
                int c = arr[right];
                if ((a >= b && b >= c) || (a <= b && b <= c))
                    return (left + right) / 2;
                else if ((a >= c && c >= b) || (a <= c && c <= b))
                    return right;
                else return left;
        }
        return left;
    }
}
