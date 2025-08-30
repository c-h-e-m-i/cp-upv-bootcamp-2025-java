import java.io.*;
import java.util.*;

public class P4I {
    static Lector sc;
    static StringBuilder output;

    public static void main(String[] args) throws IOException {
        // Inicializamos entrada y salida:
        inicializar(4, 'I');

        // Resolvemos el problema:
        solve();

        // Imprimimos la salida y cerramos el flujo de entrada:
        System.out.print(output);
        sc.cerrar();
    }

    public static void solve() throws IOException {
        /*
         * Leemos la entrada, nos guardamos el cuadro en una matriz de booleanos
         * ('.' = false, '#' = true), y comprobamos, para cada área 1x1 del cuadro, si
         * sus áreas circundantes son iguales que ella, en cuyo caso sabremos que el
         * cuadro no es original:
         */
        int n = sc.nextInt(), m = sc.nextInt();
        boolean[][] cuadro = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            String fila = sc.nextLine();
            for (int j = 0; j < m; j++) {
                boolean p = fila.charAt(j) == '#';
                cuadro[i][j] = p;

                if (i > 0 && j > 0)
                    if (p == cuadro[i][j - 1] && p == cuadro[i - 1][j] && p == cuadro[i - 1][j - 1]) {
                        output.append("NO ORIGINAL");
                        return;
                    }
            }
        }

        // Si hemos llegado al final del bucle sin encontrar ningún área 2x2 igual, el
        // cuadro es original:
        output.append("ORIGINAL");
    }

    // -------- CLASES Y MÉTODOS AUXILIARES --------
    public static void inicializar(int cap, char prob) throws IOException {
        File f = new File(String.format("inputs/%02d/%C.txt", cap, prob));
        sc = new Lector();
        sc.leerArchivo(f);
        output = new StringBuilder();
    }

    public static void inicializar() {
        sc = new Lector();
        sc.leerStd();
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

        void cerrar() throws IOException {
            br.close();
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
                    resto.append(st.nextToken()).append(" ");
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