package P5E;

import java.util.*;
import java.io.*;

public class Insertion_sort {
    public static void main(String[] args) throws IOException {
        File f = new File("inputs/05/E.txt");
        Scanner sc = new Scanner(f);
        solve(sc);
    }

    public static void solve(Scanner sc) {
        int N = sc.nextInt(), A[] = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }

        insertion(A);

        for (int i = 0; i < N; i++) {
            System.out.print(A[i] + " ");
        }
    }

    public static void insertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int pivote = arr[i], j = i - 1;

            while (j >= 0 && arr[j] > pivote) {
                intercambio(arr, j, j + 1);
                j--;
            }
        }
    }

    public static void intercambio(int[] arr, int a, int b) {
        int aux = arr[a];
        arr[a] = arr[b];
        arr[b] = aux;
    }
}
