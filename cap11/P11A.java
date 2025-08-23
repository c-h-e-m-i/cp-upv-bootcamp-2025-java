import java.io.*;
import java.util.*;

public class P11A {
    static Lector sc;
    static StringBuilder output;

    public static void main(String[] args) throws IOException {
        // Inicializamos entrada y salida:
        inicializar(11, 'A');

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
        Punto c = new Punto(sc.nextDouble(), sc.nextDouble()), p = new Punto(sc.nextDouble(), sc.nextDouble());

        // Calculamos la distancia entre el centro y el punto. Si es mayor que el radio,
        // estará fuera del círculo:
        double dist = (p.menos(c)).mod() - 0.001;
        output.append((dist > r) ? "NO\n" : "SI\n");
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
        double x, y;

        Punto(double x, double y) {
            this.x = x;
            this.y = y;
        }

        Punto menos(Punto p) {
            return new Punto(this.x - p.x, this.y - p.y);
        }

        double mod() {
            return Math.sqrt(this.x * this.x + this.y * this.y);
        }

        double pesc(Punto p) {
            return this.x * p.x + this.y * p.y;
        }

        double pvec(Punto p) {
            return this.x * p.y - this.y * p.x;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof Punto))
                return false;
            Punto p = (Punto) o;
            return p.x == this.x && p.y == this.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}