import java.io.*;
import java.util.*;

public class P10H {
    static Lector sc;
    static StringBuilder output;

    public static void main(String[] args) throws IOException {
        // Inicializamos entrada y salida:
        inicializar(10, 'H');

        // Resolvemos el problema:
        solve();

        // Mostramos la solución:
        System.out.print(output);
    }

    public static void solve() throws IOException {
        final int MOD = 1_000_000_007;

        // Leemos la entrada y guardamos la matriz de pueblos:
        int n = sc.nextInt(), m = sc.nextInt();
        int[][] pueblos = new int[n][m], caminos = new int[n][m];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                int valor = sc.nextInt();
                pueblos[i][j] = valor;
            }

        if (pueblos[0][0] == 0) {
            output.append(0);
            return;
        }

        /*
         * En esta ocasión no vamos a poder usar ventana deslizante, pues vamos a poder
         * saltar varios pueblos en un solo movimiento. La idea será usar una matriz de
         * enteros donde la entrada (i, j) indique el número de caminos mediante los
         * cuales se puede llegar al pueblo de coordenadas (i, j):
         */
        caminos[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int dist = pueblos[i][j];

                if (dist == 0)
                    continue;

                if (i + dist < n) {
                    caminos[i + dist][j] += caminos[i][j];
                    caminos[i + dist][j] %= MOD;
                }
                if (j + dist < m) {
                    caminos[i][j + dist] += caminos[i][j];
                    caminos[i][j + dist] %= MOD;
                }
            }
        }

        output.append(caminos[n - 1][m - 1]);
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