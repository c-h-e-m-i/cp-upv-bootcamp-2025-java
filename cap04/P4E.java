import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class P4E {
    static Lector sc;
    static StringBuilder output;
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        // Inicializamos entrada y salida:
        inicializar(4, 'E');

        // Resolvemos el problema:
        int t = sc.nextInt();
        while (t-- > 0)
            solve();

        // Imprimimos la salida y cerramos el flujo de entrada:
        bw.flush();
        sc.cerrar();
    }

    public static void solve() throws IOException {
        // Leemos la entrada:
        char[] txt = sc.nextLine().toCharArray();

        // Analizamos cada carácter y añadimos los asteriscos correspondientes:
        StringBuilder inter = new StringBuilder();
        for (char c : txt) {
            if (c == ' ')
                inter.append("**");
            else
                inter.append("* ").append(c).append(' ');
        }
        inter.append("*");

        // Creamos las filas de asteriscos de arriba y abajo, y guardamos la salida:
        String fila = filaDe('*', inter.length()) + '\n';
        bw.write(fila + inter.toString() + '\n' + fila);
    }

    public static String filaDe(char c, int reps) {
        char[] aux = new char[reps];
        Arrays.fill(aux, c);
        return new String(aux);
    }

    // -------- CLASES Y MÉTODOS AUXILIARES --------
    public static void inicializar(int cap, char prob) throws IOException {
        File f = new File(String.format("inputs/%02d/%C.txt", cap, prob));
        sc = new Lector();
        sc.leerArchivo(f);
        output = new StringBuilder();
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    public static void inicializar() {
        sc = new Lector();
        sc.leerStd();
        output = new StringBuilder();
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
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