import java.io.*;
import java.util.*;

public class P10G {
    static Lector sc;
    static StringBuilder output;

    public static void main(String[] args) throws IOException {
        // Inicializamos entrada y salida:
        inicializar(10, 'G');

        // Resolvemos el problema:
        solve();

        // Mostramos la solución:
        System.out.print(output);
    }

    public static void solve() throws IOException {
        // Leemos la entrada y guardamos la matriz de pueblos:
        int n = sc.nextInt(), m = sc.nextInt();
        int[][] pueblos = new int[n][m], ventana = new int[2][m];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                int valor = sc.nextInt();
                pueblos[i][j] = valor;

                if (i == 0)
                    ventana[0][j] = (j > 0 ? ventana[0][j - 1] : 0) + valor;
            }

        /*
         * Este problema también es similar a la distancia de Levenshtein. Usaremos una
         * ventana deslizante de 2 filas en la que la celda que estemos editando será
         * igual al máximo de sus celdas vecinas de arriba y la izquierda más el valor
         * actual de la celda (las ganancias de su pueblo asociado):
         */
        for (int i = 1; i < n; i++) {
            int edit = i & 1, prev = 1 - edit;
            ventana[edit][0] = ventana[prev][0] + pueblos[i][0];

            for (int j = 1; j < m; j++) {
                int max = Math.max(ventana[prev][j], ventana[edit][j - 1]);
                ventana[edit][j] = max + pueblos[i][j];
            }
        }

        output.append(ventana[(n - 1) % 2][m - 1]);
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