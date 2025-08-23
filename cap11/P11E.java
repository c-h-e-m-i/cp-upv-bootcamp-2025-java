import java.io.*;
import java.util.*;

public class P11E {
    static Lector sc;
    static StringBuilder output;
    static int x, y;

    public static void main(String[] args) throws IOException {
        // Inicializamos entrada y salida:
        inicializar(11, 'E');

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
        int r = sc.nextInt();
        x = sc.nextInt();
        y = sc.nextInt();

        // Escribimos la salida:
        output.append(dibujar(r, 0, 0)).append('\n');
    }

    static int dibujar(int r, int cx, int cy) {
        // Primero comprobamos si el punto está dentro del círculo actual:
        int sx = x - cx, sy = y - cy;
        long dist = 1L * sx * sx + 1L * sy * sy;
        int res = dist > 1L * r * r ? 0 : 1;

        /*
         * Si el radio del círculo es mayor que 1, aplicamos la siguiente lógica:
         *
         * Como se puede observar en la figura del enunciado, los únicos círculos que se
         * pueden solapar son un círculo "hijo" con su "padre". Esto quiere decir que no
         * tenemos que analizar los 4 hijos de cada círculo en cada llamada, sino que
         * solo uno de ellos (a lo sumo) podrá contener el punto. Siguiendo esta idea,
         * dividimos el círculo en 4 cuadrantes, y según el cuadrante donde se encuentre
         * el punto expandiremos un hijo u otro:
         */
        if (r > 1) {
            if (Math.abs(sx) > Math.abs(sy)) // Cuadrantes derecho e izquierdo
                cx += sx > 0 ? r : -r;
            else // Cuadrantes superior e inferior
                cy += sy > 0 ? r : -r;
            res += dibujar(r / 2, cx, cy);
        }

        return res;
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