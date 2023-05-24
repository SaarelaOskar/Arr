package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int [] numberArr = {5,1,5,1,6,0,2,4};
        menu(numberArr);
    }
    public static void menu(int[] arr){
        boolean gameOver = true;
        Scanner scanner = new Scanner(System.in);
        int x = 0;
        int pos = 0;
        int value = 0;

        while (gameOver) {
            System.out.println("Your array's length: " + arr.length);
            System.out.print("Your array's elements: ");
            arrayLooper(arr);
            System.out.println("1 - Add element to the array's tail length");
            System.out.println("2 - Shrink the array's tail length(deleting the last element)");
            System.out.println("3 - Search the array for a number");
            System.out.println("4 - Sortera array");
            System.out.println("5 - Change element at pos");
            System.out.println("6 - exit");
            x = scanner.nextInt();
            switch (x) {
                case 1:
                    System.out.println("What number do you want the element to have?");
                    value = scanner.nextInt();
                    System.out.println();
                    arr =  arrAddLength(arr, value);
                    break;
                case 2:
                    arr = arrayShrinkLength(arr);
                    System.out.println();
                    break;
                case 3:
                    System.out.println("What number do you want to search for?");
                    value = scanner.nextInt();
                    System.out.println();
                    searchArray(arr,value);
                    break;
                case 4:
                    System.out.println("Sorterad array");
                    arr = timSort(arr);
                    break;
                case 5:
                    System.out.println("Vilket pos vill du ändra?");
                    pos = scanner.nextInt();
                    System.out.println("Vilket värde vill du lägga till i "+pos+" pos?");
                    value = scanner.nextInt();
                    arr = arrayChangeAtIndex(arr,pos,value);
                    break;
                default: {
                    System.out.println("Good bye");
                    gameOver = false;
                    break;
                }
            }
        }
    }
    public static void arrayLooper(int[] arr){
        for(int i = 0; i < arr.length; i++){
            if(i == arr.length -1){
                System.out.print(arr[i]);
            }
            else {
                System.out.print(arr[i] + ",");
            }
        }
        System.out.println();
        System.out.println();
    }
    public static void arrayLooperString(String[] arr){
        for(int i = 0; i < arr.length; i++){
            if(arr[i] != null) {
                System.out.print(arr[i] + " ");
            }
        }
        System.out.println();
    }

    public static int[] arrAddLength(int [] arr, int value){
        int[] array = new int[arr.length +1];
        int x = 0;

        if(arr.length == 0){
            array[0] = value;
            return array;
        }
        for (int i = 0; i < arr.length;i++){
            array[i] = arr[i];
            x = i;
        }
        array[x+1] = value;
        return array;
    }
    public static int[] arrayShrinkLength(int [] arr) throws NegativeArraySizeException{
        try {
            int[] array = new int[arr.length -1];
            for (int i = 0; i < arr.length -1;i++){
                array[i] = arr[i];
            }
            return array;
        }
        catch (NegativeArraySizeException e){
            System.out.println("Your array cant be less the zero in size");
            return arr;
        }
    }

    public static void searchArray(int [] arr, int value){
        String[] array = new String[arr.length];
        boolean check = false;

        for (int i = 0; i < arr.length;i++){
            if(arr[i] == value){
                array[i]= "pos: " + (i+1);
                check = true;
            }
        }
        if (check) {
            System.out.print("Your number " + value + " is found at following, ");
            arrayLooperString(array);
        }
        else {
            System.out.println("The number is not present");
            System.out.println();
        }
    }

    public static int[] arrayChangeAtIndex(int[]arr, int pos, int value){
        int [] array = new int[arr.length];

        for(int i = 0;i < arr.length ;i++){
            array[i] = arr[i];
        }
        if(pos > arr.length || pos < 0){
            System.out.println("Den pos finns inte");
            return arr;
        }
        array[pos-1] = value;
        return array;
    }

    static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    static void merge(int[]arr, int left, int mid, int right) {
        int length1 = mid - left + 1;
        int length2 = right - mid;

        int[]leftArr = new int[length1];
        int[]rightArr = new int[length2];

        for (int i = 0; i < length1; i++){
            leftArr[i] = arr[left + i];
        }
        for (int i = 0; i < length2; i++){
            rightArr[i] = arr[mid + 1 + i];
        }

        int i = 0, j = 0, k = left;
        while (i < length1 && j < length2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }
        while (i < length1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }
        while (j < length2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    public static int[] timSort(int[] arr) {
        int n = arr.length;
        int BlockSize = 32;

        for (int i = 0; i < n; i += BlockSize) {
            insertionSort(arr, i, Math.min((i + BlockSize - 1), (n - 1)));
        }
        for (int size = BlockSize; size < n; size *= 2) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (n - 1));
                merge(arr, left, mid, right);
            }
        }
        return arr;
    }
}