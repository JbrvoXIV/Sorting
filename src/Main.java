import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random rnd = new Random();
        Comparable[] arr = new Comparable[10];


        for(int i = 0; i < arr.length; i++) {
            arr[i] = rnd.nextInt(100);
            System.out.print("| " + arr[i]);
        }
        System.out.println("|");

        //SortingAlgorithms.selectionSort(arr);
        //SortingAlgorithms.insertionSort(arr);
        //SortingAlgorithms.shellSort(arr);
        //SortingAlgorithms.quicksort(arr);
        SortingAlgorithms.mergeSort(arr);

        for(Comparable i : arr) {
            System.out.printf("| " + i);
        }
        System.out.println("|");
    }

}
