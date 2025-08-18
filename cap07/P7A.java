import java.util.*;
import java.io.*;

public class P7A {
    static File f;
    static Scanner sc;

    public static void main(String[] args) throws IOException {
        f = new File("inputs/07/A.txt");
        sc = new Scanner(f);
        int n = sc.nextInt();
        sc.nextLine();
        solve(n);
        sc.close();
    }

    public static void solve(int n) {
        Stack<String> pila = new Stack<>();
        for (int i = 0; i < n; i++) {
            String accion = sc.nextLine();
            if (accion.length() <= 7)
                pila.pop();
            else
                pila.push(accion.substring(10));
        }

        int tamanyo = pila.size();
        if (tamanyo == 0)
            System.out.println("No quedan libros");
        else
            for (int i = 0; i < tamanyo; i++) {
                System.out.println(pila.pop());
            }
    }
}
