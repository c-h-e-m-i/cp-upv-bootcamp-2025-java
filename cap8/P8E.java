import java.util.*;
import java.io.*;

public class P8E {
    static Lector lector;
    static StringBuilder output;
    static boolean[][] creados;
    static int n;

    public static void main(String[] args) throws IOException {
        File f = new File("inputs/8/E.txt");
        lector = new Lector();
        lector.leerArchivo(f);
        output = new StringBuilder();
        n = lector.nextInt() + 1;

        solve();

        System.out.print(output);
    }

    public static void solve() throws IOException {
        int m = lector.nextInt(), conteo = 0;
        crearGrafo(m);

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < i; j++) {
                if (!creados[i][j] && i != j) {
                    output.append(j + " " + i + "\n");
                    conteo++;
                }
            }
        }

        System.out.println(conteo);
    }

    public static void crearGrafo(int m) throws IOException {
        // Inicializamos las listas de adyacencia:
        creados = new boolean[n][n];

        // Leemos las aristas:
        while (m-- > 0) {
            int a = lector.nextInt(), b = lector.nextInt();
            creados[a][b] = true;
            creados[b][a] = true;
        }
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