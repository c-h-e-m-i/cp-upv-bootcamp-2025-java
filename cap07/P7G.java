import java.util.*;
import java.io.*;

public class P7G {
    static File f;
    static Scanner sc;
    static int n;

    public static void main(String[] args) throws IOException {
        f = new File("inputs/07/G.txt");
        sc = new Scanner(f);
        n = sc.nextInt();
        // sc.nextLine();

        solve();
    }

    @SuppressWarnings("unchecked")
    public static void solve() {
        Stack<String>[] inter = new Stack[1_000_001];
        boolean[] borradas = new boolean[1_000_001];

        for (int i = 0; i < n; i++) {
            switch (sc.next()) {
                case "+":
                    int numero = sc.nextInt();
                    if (inter[numero] == null)
                        inter[numero] = new Stack<>();
                    inter[numero].push(sc.next());
                    break;
                case "-":
                    numero = sc.nextInt();
                    inter[numero] = null;
                    borradas[numero] = true;
                    break;
            }
        }

        // int i = 0;
        for (int i = 1; i <= 1_000_000; i++) {
            if (!borradas[i]) {
                System.out.println(i);
                if (inter[i] != null) {
                    while (!inter[i].isEmpty()) {
                        System.out.println(inter[i].pop());
                    }
                }
            }
        }
    }
}