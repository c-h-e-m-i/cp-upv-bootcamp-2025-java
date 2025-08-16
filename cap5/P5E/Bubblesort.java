package P5E;

import java.util.*;
import java.io.*;

public class Bubblesort {
    public static void main(String[] args) throws IOException {
        File f = new File("inputs/5/E.txt");
        Scanner sc = new Scanner(f);
        solve(sc);
    }

    public static void solve(Scanner sc) {
        int N = sc.nextInt(), A[] = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }

        bubblesort(A);

        for (int i = 0; i < N; i++) {
            System.out.print(A[i] + " ");
        }
    }

    public static void bubblesort(int[] arr) {
        for (int pasada = 1; pasada < arr.length; pasada++)
            for (int i = 0; i < arr.length - pasada; i++) {
                if (arr[i] > arr[i + 1])
                    intercambio(arr, i, i + 1);
            }
    }

    public static void intercambio(int[] arr, int a, int b) {
        int aux = arr[a];
        arr[a] = arr[b];
        arr[b] = aux;
    }
}
