
import java.util.ArrayList;
import java.util.Random;

public class SortingAlgorithms {

    public static void selectionSort(Comparable[] arr) { // O(N^2)

        long startTime = System.nanoTime();

        for(int i = 0; i < arr.length; i++) {
            int smallestIndex = i;
            for(int j = i; j < arr.length; j++) {
                if(arr[j].compareTo(arr[smallestIndex]) < 0)
                    smallestIndex = j;
            }
            Comparable temp = arr[i];
            arr[i] = arr[smallestIndex];
            arr[smallestIndex] = temp;
        }
        System.out.println((System.nanoTime() - startTime) / (double)1000000 + " ms");
    }

    public static void insertionSort(Comparable[] arr) { // O(N^2), nearly sorted = O(N)

        long startTime = System.nanoTime();

        for(int i = 1; i < arr.length; i++) {
            Comparable temp = arr[i];
            int j = i;
            while(j > 0 && temp.compareTo(arr[j - 1]) < 0) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
        System.out.println((System.nanoTime() - startTime) / (double)1000000 + " ms");
    }

    public static void shellSort(Comparable[] arr) {

        long startTime = System.nanoTime();

        ArrayList<Integer> gapVals = new ArrayList<>();
        for(int i = 1; (int)Math.pow(2, i) - 1 < arr.length; i++) {
            gapVals.add((int)Math.pow(2, i) - 1);
        }

        for(int val = gapVals.size() - 1; val >= 0; val--) {
            shellSortHelper(arr, gapVals.get(val));
        }
        System.out.println((System.nanoTime() - startTime) / (double)1000000 + " ms");
    }

    private static void shellSortHelper(Comparable[] arr, int gap) {

        for(int i = gap; i < arr.length; i += gap) {
            Comparable temp = arr[i];
            int j = i;
            while(j > 0 && temp.compareTo(arr[j - gap]) < 0) {
                arr[j] = arr[j - gap];
                j -= gap;
            }
            arr[j] = temp;
        }
    }

    public static void quicksort(Comparable[] arr) {
        quickSortHelper(arr, 0, arr.length - 1);
    }

    private static void quickSortHelper(Comparable[] arr, int low, int high) {

        if(low >= high) {
            return;
        }

        int midpoint = low + (high - low) / 2;
        Comparable pivot = arr[midpoint];
        swap(arr, midpoint, high);

        int leftPointer = partition(arr, low, high, pivot);

        quickSortHelper(arr, low, leftPointer - 1);
        quickSortHelper(arr, leftPointer + 1, high);
    }

    private static int partition(Comparable[] arr, int low, int high, Comparable pivot) {
        int leftPointer = low;
        int rightPointer = high;

        while(leftPointer < rightPointer) {
            while(arr[leftPointer].compareTo(pivot) <= 0 && leftPointer < rightPointer) {
                leftPointer++;
            }
            while(arr[rightPointer].compareTo(pivot) >= 0 && leftPointer < rightPointer) {
                rightPointer--;
            }
            swap(arr, leftPointer, rightPointer);
        }
        swap(arr, leftPointer, high);
        return leftPointer;
    }

    private static void swap(Comparable[] arr, int i, int k) {
        Comparable temp = arr[i];
        arr[i] = arr[k];
        arr[k] = temp;
    }

    public static void mergeSort(Comparable[] arr) {
        if(arr.length < 2) {
            return;
        }

        int midpoint = arr.length / 2;
        Comparable[] leftHalf = new Comparable[midpoint];
        Comparable[] rightHalf = new Comparable[arr.length - midpoint];

        for(int i = 0; i < midpoint; i++) {
            leftHalf[i] = arr[i];
        }
        for(int i = midpoint; i < arr.length; i++) {
            rightHalf[i - midpoint] = arr[i];
        }

        mergeSort(leftHalf);
        mergeSort(rightHalf);

        merge(arr, leftHalf, rightHalf);
    }

    private static void merge(Comparable[] arr, Comparable[] leftHalf, Comparable[] rightHalf) {
        int leftPointer = 0, rightPointer = 0, mergedArrPointer = 0;

        while(leftPointer < leftHalf.length && rightPointer < rightHalf.length) {
            if(leftHalf[leftPointer].compareTo(rightHalf[rightPointer]) <= 0) {
                arr[mergedArrPointer] = leftHalf[leftPointer++];
            } else {
                arr[mergedArrPointer] = rightHalf[rightPointer++];
            }
            mergedArrPointer++;
        }
        while(leftPointer < leftHalf.length) {
            arr[mergedArrPointer++] = leftHalf[leftPointer++];
        }
        while(rightPointer < rightHalf.length) {
            arr[mergedArrPointer++] = rightHalf[rightPointer++];
        }
    }

    public static void main(String[] args) {
        Random rnd = new Random();
        Comparable[] arr = new Comparable[100000000];

        for(int i = 0; i < arr.length; i++) {
            arr[i] = rnd.nextInt(100);
            System.out.print("| " + arr[i]);
        }
        System.out.printf("|\n");

        //selectionSort(arr);
        //insertionSort(arr);
        //shellSort(arr);
        //quicksort(arr);
        mergeSort(arr);

        for(Comparable i : arr) {
            System.out.printf("| " + i);
        }
        System.out.printf("|\n");
    }
}
