package P5E;

import java.util.*;
import java.io.*;

public class Pigeonhole_sort {
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

        pigeonhole(A);

        for (int i = 0; i < N; i++) {
            System.out.print(A[i] + " ");
        }
    }

    public static void pigeonhole(int[] arr) {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
            if (arr[i] < min)
                min = arr[i];
        }

        int[] aux = new int[max - min + 1];
        for (int i = 0; i < arr.length; i++) {
            aux[arr[i] - min]++;
        }

        int index = 0;
        for (int i = 0; i < aux.length; i++) {
            while (aux[i] > 0) {
                arr[index] = i + min;
                index++;
                aux[i]--;
            }
        }
    }
}
