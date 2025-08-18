import java.util.*;
import java.io.*;

public class P10A {
    static Lector sc;
    static StringBuilder output;
    static int n;

    public static void main(String[] args) throws IOException {
        File f = new File("inputs/10/A.txt");
        sc = new Lector();
        sc.leerArchivo(f);
        output = new StringBuilder();
        n = sc.nextInt();

        solve();

        System.out.print(output);
    }

    public static void solve() throws IOException {
        // Aplicamos tabulación:
        long[] secuencia = new long[n];

        for (int i = 0; i < 3; i++) {
            secuencia[i] = sc.nextLong(); // Rellenamos los 3 primeros elementos
            output.append(secuencia[i] + " ");
        }

        for (int i = 3; i < n; i++) {
            secuencia[i] = secuencia[i - 3] + secuencia[i - 2] + secuencia[i - 1];
            output.append(secuencia[i] + " ");
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