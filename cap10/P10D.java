import java.util.*;
import java.io.*;

public class P10D {
    static Lector sc;
    static StringBuilder output;

    public static void main(String[] args) throws IOException {
        // Inicializamos entrada y salida:
        inicializar(10, 'D');

        solve();

        System.out.print(output);
    }

    public static void solve() throws IOException {
        // Leemos los valores de entrada y creamos el array 'seg' para realizar la DP:
        int n = sc.nextInt(), v[] = new int[3], seg[] = new int[n + 1];
        for (int i = 0; i < v.length; i++) {
            int val = sc.nextInt();
            v[i] = val;
            if (val <= n)
                seg[val] = 1;
        }

        /*
         * La lógica de este problema es similar a la del problema de cambio de monedas,
         * pero buscando un máximo en vez de un mínimo.
         * 
         * La idea es la siguiente: vamos a recorrer 'seg' 3 veces, una por cada medida
         * 'm' de corte. Por cada índice 'j' con un valor mayor que 0, pondremos el
         * valor 'seg[j + m]' a 'Math.max(seg[j + m], seg[j] + 1)', de tal forma que, al
         * salir del bucle, el valor 'seg[x]' será el número de segmentos máximos que
         * podemos cortar con una tela que mida 'x':
         */
        for (int i = 0; i < v.length; i++) {
            int m = v[i];

            for (int j = 1; j <= n - m; j++) {
                if (seg[j] > 0)
                    seg[j + m] = Math.max(seg[j + m], seg[j] + 1);
            }
        }

        output.append(seg[n]);
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
}