import java.io.*;
import java.util.*;

public class P4K {
    static Lector sc;
    static StringBuilder output;

    public static void main(String[] args) throws IOException {
        // Inicializamos entrada y salida:
        inicializar(4, 'K');

        // Resolvemos el problema:
        solve();

        // Imprimimos la salida y cerramos el flujo de entrada:
        System.out.print(output);
        sc.cerrar();
    }

    public static void solve() throws IOException {
        /*
         * Usaremos una matriz de enteros inicializada a ceros, uno por cada baldosa del
         * tablero. Cada vez que leamos una mina, sumaremos 1 a sus baldosas vecinas
         * (menos las minas), y marcaremos la mina con un -1 para saber dónde está al
         * momento de imprimir el tablero por pantalla:
         */
        int n = sc.nextInt(), m = sc.nextInt(), casilla[][] = new int[n][m];
        for (int i = 0; i < n; i++) {
            String linea = sc.nextLine();
            for (int j = 0; j < m; j++) {
                if (linea.charAt(j) == '*') {
                    casilla[i][j] = -1;
                    rellenarVecinos(casilla, i, j);
                }
            }
        }

        // Guardamos en la salida el tablero, cambiando previamente los '-1' por 'F' y
        // los ceros por espacios en blanco:
        for (int[] fila : casilla) {
            for (int i : fila) {
                switch (i) {
                    case -1 -> output.append('F');
                    case 0 -> output.append(' ');
                    default -> output.append(i);
                }
            }
            output.append('\n');
        }
    }

    public static void rellenarVecinos(int[][] casilla, int i, int j) {
        int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
        int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };

        for (int k = 0; k < 8; k++) {
            int ni = i + dx[k];
            int nj = j + dy[k];

            if (ni >= 0 && ni < casilla.length && nj >= 0 && nj < casilla[ni].length) {
                if (casilla[ni][nj] != -1)
                    casilla[ni][nj]++;
            }
        }
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