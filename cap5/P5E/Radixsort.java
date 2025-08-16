package P5E;

import java.util.*;
import java.io.*;

public class Radixsort {
    public static void main(String[] args) throws IOException {
        File f = new File("inputs/5/E.txt");
        Scanner sc = new Scanner(f);
        solve(sc);
    }

    public static void solve(Scanner sc) {
        int N = sc.nextInt(), A[] = new int[N], max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
            if (A[i] > max)
                max = A[i];
            if (A[i] < min)
                min = A[i];
        }

        // Restando el mínimo nos podemos ahorrar varios dígitos e incluir números
        // negativos en el ordenamiento:
        for (int i = 0; i < N; i++) {
            A[i] -= min;
        }
        max -= min;

        int exp = 0;
        while (max > 0) {
            A = radixsort(A, exp, min);
            exp++;
            max /= 10;
        }

        // Sumamos el mínimo de vuelta después del ordenamiento:
        for (int i = 0; i < N; i++) {
            A[i] += min;
        }

        for (int i = 0; i < N; i++) {
            System.out.print(A[i] + " ");
        }
    }

    public static int[] radixsort(int[] arr, int exp, int min) {
        int divisor = (int) Math.pow(10, exp), nums[] = new int[11], output[] = new int[arr.length];

        for (int k : arr) {
            int uds = (k / divisor) % 10;
            nums[uds + 1]++;
        }

        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }

        for (int j : arr) {
            int uds = (j / divisor) % 10;
            output[nums[uds]] = j;
            nums[uds]++;
        }

        return output;
    }

    public static void intercambio(int[] arr, int a, int b) {
        int aux = arr[a];
        arr[a] = arr[b];
        arr[b] = aux;
    }
}
