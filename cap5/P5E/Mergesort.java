package P5E;

import java.util.*;
import java.io.*;

public class Mergesort {
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

        mergesort(A, 0, A.length - 1);

        for (int i = 0; i < N; i++) {
            System.out.print(A[i] + " ");
        }
    }

    public static void mergesort(int[] arr, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;

            mergesort(arr, left, middle);
            mergesort(arr, middle + 1, right);

            merge(arr, left, middle, right);
        }
    }

    public static void merge(int[] arr, int left, int middle, int right) {
        int[] aux = new int[right - left + 1];
        int i1 = left, i2 = middle + 1, iAux = 0;

        while (i1 <= middle && i2 <= right) {
            if (arr[i1] < arr[i2]) {
                aux[iAux++] = arr[i1++];
            } else
                aux[iAux++] = arr[i2++];
        }

        while (i1 <= middle) {
            aux[iAux++] = arr[i1++];
        }

        while (i2 <= right) {
            aux[iAux++] = arr[i2++];
        }

        System.arraycopy(aux, 0, arr, left, iAux);
    }
}
