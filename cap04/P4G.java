import java.io.*;
import java.util.*;

public class P4G {
    static Lector sc;
    static StringBuilder output;

    public static void main(String[] args) throws IOException {
        // Inicializamos entrada y salida:
        inicializar(4, 'G');

        // Resolvemos el problema:
        int t = sc.nextInt();
        while (t-- > 0)
            solve();

        // Imprimimos la salida y cerramos el flujo de entrada:
        System.out.print(output);
        sc.cerrar();
    }

    public static void solve() throws IOException {
        // Creamos un tablero inicial correcto:
        char[][] tablero = {
                { 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B', '\n' },
                { 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W', '\n' },
                { 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B', '\n' },
                { 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W', '\n' },
                { 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B', '\n' },
                { 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W', '\n' },
                { 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B', '\n' },
                { 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W', '\n' }
        };

        // Lo editamos comparándolo con la entrada:
        for (int i = 0; i < 8; i++) {
            String fila = sc.nextLine();
            for (int j = 0; j < 8; j++) {
                if (fila.charAt(j) != tablero[i][j])
                    tablero[i][j] = 'X';
            }
        }

        // Guardamos el tablero editado en la salida:
        for (char[] fila : tablero)
            output.append(fila);
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