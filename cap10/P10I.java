import java.io.*;
import java.util.*;

public class P10I {

    static Lector sc;
    static StringBuilder output;

    public static void main(String[] args) throws IOException {
        // Inicializamos entrada y salida:
        inicializar(10, 'I');

        // Resolvemos el problema:
        solve();

        // Mostramos la solución:
        System.out.print(output);
    }

    public static void solve() throws IOException {
        final int MOD = 1_000_000_007;

        // Leemos la entrada:
        int n = sc.nextInt(), m = sc.nextInt();

        /*
         * Como el caballo solo se puede mover a lo sumo 2 posiciones hacia abajo,
         * podemos emplear nuevamente una ventana deslizante (de 3 filas en este caso)
         * para almacenar el problema.
         * 
         * La lógica en este caso es similar a la del problema anterior. Para cada
         * casilla recogemos el número de caminos posibles que llegan a ella, sumando
         * dicho número a las casillas que podemos alcanzar desde la actual:
         */
        int casillas[][] = new int[3][m];
        casillas[0][0] = 1;

        for (int i = 0; i < n; i++) {
            int f1 = i % 3, f2 = (i + 1) % 3, f3 = (i + 2) % 3;
            casillas[f3][0] = 0;

            for (int j = 0; j < m - 1; j++) {
                casillas[f3][j + 1] = 0; // Vamos limpiando la fila 3 para reutilizarla (en la primera posición siempre
                                         // habrá un 0)

                int caminos = casillas[f1][j];
                if (caminos == 0) // Si no hay caminos que lleguen a mi casilla actual, avanzo a la siguiente
                    continue;

                // Modifico las casillas correspondientes de las filas 2 y 3
                casillas[f3][j + 1] += caminos % MOD;
                casillas[f3][j + 1] %= MOD;
                if (j + 2 < m) {
                    casillas[f2][j + 2] += caminos % MOD;
                    casillas[f2][j + 2] %= MOD;
                }

            }
        }

        output.append(casillas[(n - 1) % 3][m - 1]);
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
