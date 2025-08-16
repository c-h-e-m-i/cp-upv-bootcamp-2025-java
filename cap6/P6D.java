import java.util.*;
import java.io.*;

public class P6D {
    public static void main(String[] args) throws IOException {
        File f = new File("inputs/6/D.txt");
        Scanner sc = new Scanner(f);
        int n = sc.nextInt();

        imprimirAsc(n);
        imprimirDesc(n - 1);
        sc.close();
    }

    public static void imprimirAsc(int n) {
        if (n == 1) {
            linea(1);
            System.out.println();
        } else {
            imprimirAsc(n - 1);
            linea(n);
            System.out.println();
        }
    }

    public static void imprimirDesc(int n) {
        if (n == 1) {
            linea(1);
        } else if (n > 1) {
            linea(n);
            System.out.println();
            imprimirDesc(n - 1);
        }
    }

    public static void linea(int n) {
        if (n == 1)
            System.out.print('#');
        else if (n > 1) {
            linea(n - 1);
            System.out.print('#');
        }
    }
}
