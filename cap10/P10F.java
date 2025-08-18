import java.util.*;
import java.io.*;

public class P10F {
    static Lector sc;
    static StringBuilder output;

    public static void main(String[] args) throws IOException {
        // Inicializamos entrada y salida:
        inicializar(10, 'F');

        // Resolvemos el problema:
        solve();

        // Mostramos la solución:
        System.out.print(output);
    }

    public static void solve() throws IOException {
        final int MOD = 1_000_000_007;

        // Leemos la entrada y guardamos la plaza como una matriz de booleanos:
        int n = sc.nextInt(), m = sc.nextInt();
        boolean[][] plaza = new boolean[n][m];
        int[][] caminos = new int[2][m];

        for (int i = 0; i < n; i++) {
            String linea = sc.nextLine();
            for (int j = 0; j < m; j++) {
                boolean caminable = linea.charAt(j) == '.';
                plaza[i][j] = caminable;

                // Las baldosas de fila de arriba y la columna izquierda tendrán a lo sumo 1
                // camino:
                if (i == 0 && j == 0)
                    caminos[0][0] = caminable ? 1 : 0;
                else if (i == 0)
                    caminos[0][j] = caminable ? caminos[0][j - 1] : 0;
            }
        }

        /*
         * La lógica es parecida a la de los problemas anteriores: Para cada baldosa
         * caminable de la plaza, me guardaré el n.º de caminos que llegan a ella, y
         * sumaré dicho número a sus baldosas contiguas.
         * 
         * Ya que en una misma iteración solo necesitaremos referenciar a la baldosa
         * actual y sus 2 vecinas, podemos implementar el algoritmo con una
         * "ventana deslizante" de 2 filas (igual que con Levenshtein):
         */
        for (int i = 1; i < n; i++) {
            int edit = i % 2, prev = 1 - edit;
            caminos[edit][0] = plaza[i][0] ? caminos[prev][0] : 0;

            for (int j = 1; j < m; j++) {
                caminos[edit][j] = plaza[i][j] ? (caminos[edit][j - 1] + caminos[prev][j]) % MOD : 0; // Izq + sup
            }
        }

        output.append(caminos[(n - 1) % 2][m - 1]);
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

    @SuppressWarnings("unused")
    private static class Pair implements Comparable<Pair> {
        int a, b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Pair p) {
            return this.a - p.a;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof Pair))
                return false;
            Pair p = (Pair) o;
            return p.a == this.a;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }
    }
}