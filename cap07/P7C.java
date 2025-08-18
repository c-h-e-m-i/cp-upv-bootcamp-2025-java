import java.util.*;
import java.io.*;

public class P7C {
    static File f;
    static Scanner sc;

    public static void main(String[] args) throws IOException {
        f = new File("inputs/07/C.txt");
        sc = new Scanner(f);
        int n = sc.nextInt();
        // sc.nextLine();

        solve(n);
    }

    public static void solve(int n) {
        Map<Integer, Integer> bebidas = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            int bebida = sc.nextInt();
            bebidas.put(bebida, bebidas.getOrDefault(bebida, 0) + 1);
        }

        int max = -1, maxKey = -1;
        for (Map.Entry<Integer, Integer> bebida : bebidas.entrySet()) {
            int ventas = bebida.getValue();
            if (ventas > max) {
                max = ventas;
                maxKey = bebida.getKey();
            }
        }

        System.out.println(maxKey);
    }
}
