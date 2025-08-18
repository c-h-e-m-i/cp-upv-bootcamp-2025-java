import java.util.*;
import java.io.*;

public class P5A {
    public static void main(String[] args) throws IOException {
        File f = new File("inputs/5/A.txt");
        Scanner sc = new Scanner(f);

        while (sc.hasNextLine()) {
            solve(sc);
        }
    }

    public static void solve(Scanner sc) {
        int N = sc.nextInt(), A[] = new int[N], min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }

        if (sc.hasNextLine())
            sc.nextLine(); // Para asegurarnos de consumir el salto de línea

        mergesort(A, 0, A.length - 1);

        for (int i = 0; i < N - 1; i++) {
            int diff = Math.abs(A[i] - A[i + 1]);
            if (diff < min) {
                min = diff;
            }
        }

        System.out.println(min);
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
