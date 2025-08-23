import java.io.*;
import java.util.*;

public class P11D {
    static Lector sc;
    static StringBuilder output;

    public static void main(String[] args) throws IOException {
        // Inicializamos entrada y salida:
        inicializar(11, 'D');

        // Resolvemos el problema:
        int t = sc.nextInt();
        while (t-- > 0)
            solve();

        // Imprimimos la salida y cerramos el flujo de entrada:
        System.out.print(output);
        sc.cerrar();
    }

    public static void solve() throws IOException {
        // Leemos la entrada:
        int grupos = sc.nextInt(), radio[] = new int[grupos];
        Punto[] grupo = new Punto[grupos];

        for (int i = 0; i < grupos; i++) {
            grupo[i] = new Punto(sc.nextInt(), sc.nextInt());
            radio[i] = sc.nextInt();
        }

        /*
         * La idea aquí es bastante intuitiva: para saber si 2 grupos se solapan,
         * trazamos un segmento entre sus centros y vemos si la suma de sus radios es
         * mayor que la longitud de dicho segmento. Si lo es, se solapan:
         */
        int contador = 0;
        for (int i = 0; i < grupos - 1; i++)
            for (int j = i + 1; j < grupos; j++) {
                double dist = grupo[j].menos(grupo[i]).mod();
                if (radio[i] + radio[j] > dist)
                    contador++;
            }

        // Escribimos la salida:
        output.append(contador).append('\n');
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

    private static class Punto {
        int x, y;

        Punto(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Punto menos(Punto p) {
            return new Punto(this.x - p.x, this.y - p.y);
        }

        double mod() {
            return Math.sqrt(this.x * this.x + this.y * this.y);
        }
    }
}