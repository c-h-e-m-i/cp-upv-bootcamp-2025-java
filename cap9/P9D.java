import java.util.*;
import java.io.*;

public class P9D {
    static Lector sc;
    static StringBuilder output;

    public static void main(String[] args) throws IOException {
        File f = new File("inputs/9/D.txt");
        sc = new Lector();
        sc.leerArchivo(f);
        output = new StringBuilder();
        int t = sc.nextInt();

        while (t-- > 0)
            solve();

        System.out.print(output);
    }

    public static void solve() throws IOException {
        int n = sc.nextInt(), calif[] = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            calif[i] = sc.nextInt();
        }

        SegmentTree st = new SegmentTree(calif);
        st.construir(1, n);

        int q = sc.nextInt();

        while (q-- > 0) {
            output.append(st.rangoMin(sc.nextInt(), sc.nextInt()) + "\n");
        }
    }

    // -------- CLASES AUXILIARES --------
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
    private static class SegmentTree {
        int datoIni, datoFin;
        int[] datos, arbol;

        SegmentTree(int[] datos) {
            this.datos = datos;
        }

        void construir() {
            construir(0, datos.length);
        }

        void construir(int ini, int fin) {
            this.datoIni = ini;
            this.datoFin = fin;
            this.arbol = new int[4 * (this.datoFin - this.datoIni + 1)];
            construir(1, ini, fin);
        }

        private void construir(int actual, int ini, int fin) {
            if (ini == fin) {
                arbol[actual] = datos[ini];
            } else {
                int medio = (ini + fin) / 2, izq = actual * 2, der = actual * 2 + 1;
                construir(izq, ini, medio);
                construir(der, medio + 1, fin);
                arbol[actual] = combinar(arbol[izq], arbol[der]);
            }
        }

        int combinar(int a, int b) {
            return Math.min(a, b);
        }

        int rangoMin(int ini, int fin) {
            return rangoMin(1, ini, fin, this.datoIni, this.datoFin);
        }

        private int rangoMin(int actual, int ini, int fin, int tI, int tF) {
            if (ini > fin)
                return Integer.MAX_VALUE;
            if (ini == tI && fin == tF)
                return arbol[actual];

            int tM = (tI + tF) / 2;
            return combinar(rangoMin(actual * 2, ini, Math.min(fin, tM), tI, tM),
                    rangoMin(actual * 2 + 1, Math.max(ini, tM + 1), fin, tM + 1, tF));
        }

        void actualizar(int elem, int nuevoValor) {
            datos[elem] = nuevoValor;
            actualizar(1, this.datoIni, this.datoFin, elem);
        }

        private void actualizar(int actual, int tI, int tF, int elem) {
            if (tI == tF) {
                arbol[actual] = datos[elem];
            } else {
                int tM = (tI + tF) / 2, izq = actual * 2, der = actual * 2 + 1;

                if (elem <= tM) {
                    actualizar(izq, tI, tM, elem);
                } else {
                    actualizar(der, tM + 1, tF, elem);
                }

                arbol[actual] = combinar(arbol[izq], arbol[der]);
            }
        }
    }
}