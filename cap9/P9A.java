import java.util.*;
import java.io.*;

public class P9A {
    static Lector sc;
    static StringBuilder output;
    static ArrayList<Integer>[] grafo;
    static int n, m;

    public static void main(String[] args) throws IOException {
        File f = new File("inputs/9/A.txt");
        sc = new Lector();
        sc.leerArchivo(f);
        output = new StringBuilder();
        int t = sc.nextInt();

        while (t-- > 0)
            solve();

        System.out.print(output);
    }

    public static void solve() throws IOException {
        crearGrafo();
        analizarGrafo();
    }

    @SuppressWarnings("unchecked")
    public static void crearGrafo() throws IOException {
        n = sc.nextInt() + 1;
        m = sc.nextInt();
        grafo = new ArrayList[n];

        for (int i = 1; i < n; i++) {
            grafo[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt(), v = sc.nextInt();
            grafo[u].add(v);
            grafo[v].add(u);
        }
    }

    public static void analizarGrafo() {
        HashMap<Integer, Integer> freqs = new HashMap<>();
        int x = 1, y = 1, aux = 1;
        for (int i = 1; i < n; i++) {
            int tam = grafo[i].size();

            if (tam != 1) {
                int freq = freqs.getOrDefault(tam, 0) + 1;
                if (freq > 1) {
                    y = tam;
                    break;
                }

                freqs.put(tam, freq);
                aux = tam;
            }
        }

        if (y == 1) { // Si entro aquí significa que no he encontrado más de 1 nodo con el mismo
                      // número != 1 de vecinos.
            if (aux == m)
                y = aux - 1; // Si ese nodo no es el central, significa que x = 1, así que tendré aux aristas
                             // (pues aux = y + 1).
            else
                x = aux;
        } else { // Si entro aquí significa que he encontrado 2 o más nodos con el mismo número
                 // != 1 de vecinos, por lo que y = dicho número - 1.
            x = m / y; // El número de aristas es igual a x*y + x = x*(y + 1).
            y -= 1;
        }

        output.append(x + " " + y + "\n");
    }

    // -------- CLASES AUXILIARES --------
    @SuppressWarnings("unused")
    private static class Lector {
        BufferedReader br;
        StringTokenizer st;

        Lector() {
            br = null;
            st = new StringTokenizer("");
        }

        void leerArchivo(File f) throws IOException {
            br = new BufferedReader(new FileReader(f));
        }

        void leerStd() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            if (!st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
            }
            return st.nextToken();
        }

        String nextLine() throws IOException {
            if (!st.hasMoreTokens()) {
                return br.readLine();
            } else {
                StringBuilder resto = new StringBuilder();
                while (st.hasMoreTokens()) {
                    resto.append(st.nextToken() + " ");
                }
                return resto.toString().trim();
            }
        }

        byte nextByte() throws IOException {
            return Byte.parseByte(next());
        }

        short nextShort() throws IOException {
            return Short.parseShort(next());
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        float nextFloat() throws IOException {
            return Float.parseFloat(next());
        }

        double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }

        boolean nextBoolean() throws IOException {
            return Boolean.parseBoolean(next());
        }
    }
}