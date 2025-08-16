package P5E;

import java.util.*;
import java.io.*;

public class Selection_sort {
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

        selection(A);

        for (int i = 0; i < N; i++) {
            System.out.print(A[i] + " ");
        }
    }

    public static void selection(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min])
                    min = j;
            }
            intercambio(arr, i, min);
        }
    }

    public static void intercambio(int[] arr, int a, int b) {
        int aux = arr[a];
        arr[a] = arr[b];
        arr[b] = aux;
    }
}
