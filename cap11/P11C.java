import java.io.*;
import java.util.*;

public class P11C {
    static Lector sc;
    static StringBuilder output;

    public static void main(String[] args) throws IOException {
        // Inicializamos entrada y salida:
        inicializar(11, 'C');

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
        Punto p1 = new Punto(sc.nextInt(), sc.nextInt()), p2 = new Punto(sc.nextInt(), sc.nextInt()),
                p3 = new Punto(sc.nextInt(), sc.nextInt());

        /*
         * Para este problema podríamos comprobar si las razones de los 3 puntos
         * (coordenada X/coordenada Y o viceversa) son iguales, en cuyo caso serían
         * colineales, el problema es que al poder haber divisiones por 0 no nos renta
         * estar contemplando esos casos aparte. Otro enfoque que podríamos usar si no
         * sería el mismo que el del problema anterior: ver si el determinante con
         * coordenadas homogéneas (Z = 1) para los 3 puntos da 0, pero podemos
         * simplificarlo incluso más:
         * 
         * - Piensa que si resto los vectores que tienen como punta 2 puntos, el
         * resultado es un vector que une esos 2 puntos.
         * 
         * - Así pues, puedo restar 2 pares de puntos y obtener los vectores que los
         * unen. Si estos son colineales (su determinante 2x2 es 0), entonces los puntos
         * también lo serán (esto es lo del producto cruzado AB x AC que viene en la web
         * del Bootcamp):
         */
        Punto v12 = p2.menos(p1), v13 = p3.menos(p1);

        // Escribimos la salida:
        output.append(v12.pvec(v13) == 0 ? "SI" : "NO").append('\n');
    }

    public static double max(double... args) {
        double max = 0;

        for (double v : args) {
            v = Math.abs(v);
            if (v > max) {
                max = v;
            }
        }

        return max;
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

    @SuppressWarnings("unused")
    private static class Punto {
        int x, y;

        Punto(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Punto menos(Punto p) {
            return new Punto(this.x - p.x, this.y - p.y);
        }

        int pvec(Punto p) {
            return this.x * p.y - this.y * p.x;
        }
    }
}