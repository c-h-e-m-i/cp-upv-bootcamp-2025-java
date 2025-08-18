import java.util.*;
import java.io.*;

public class P10B {
    static Lector sc;
    static StringBuilder output;

    public static void main(String[] args) throws IOException {
        // Inicializamos entrada y salida:
        inicializar(10, 'B');

        // Creamos la secuencia de valores:
        int[] secuencia = tabulacion();

        // Leemos las consultas y devolvemos su entrada en la secuencia:
        int q = sc.nextInt();
        while (q-- > 0)
            output.append(secuencia[sc.nextInt()] + "\n");

        System.out.print(output);
    }

    public static int[] tabulacion() throws IOException {
        // Inicializamos la secuencia y el valor sobre el que aplicaremos el módulo:
        final int modulo = 1_000_000_007;
        int[] secuencia = new int[300_001];
        secuencia[0] = 0;
        secuencia[1] = 1;

        // Aplicamos tabulación para crear la secuencia:
        for (int i = 2; i < secuencia.length; i++) {
            secuencia[i] = (secuencia[i - 1] % modulo + secuencia[i - 2] % modulo) % modulo;
        }

        return secuencia;
    }

    // -------- CLASES Y MÉTODOS AUXILIARES --------
    public static void inicializar(int cap, char prob) throws IOException {
        File f = new File(String.format("inputs/%02d/%C.txt", cap, prob));
        sc = new Lector();
        sc.leerArchivo(f);
        output = new StringBuilder();
    }

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