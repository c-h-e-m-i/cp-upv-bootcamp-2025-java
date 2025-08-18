import java.util.*;
import java.io.*;

public class P9F {
    static Lector sc;
    static StringBuilder output;
    static int n;

    public static void main(String[] args) throws IOException {
        File f = new File("inputs/09/F.txt");
        sc = new Lector();
        sc.leerArchivo(f);
        output = new StringBuilder();

        solve();

        System.out.print(output);
    }

    public static void solve() throws IOException {
        n = sc.nextInt();
        int m = sc.nextInt(), tam = (int) Math.pow(2, n), calif[] = new int[tam + 1];
        ArrayList<String> ppt = new ArrayList<>(Arrays.asList("piedra", "papel", "tijera"));

        for (int i = 1; i <= tam; i++) {
            calif[i] = ppt.indexOf(sc.next());
        }

        SegmentTree st = new SegmentTree(calif);
        st.construir(1, tam);

        while (m-- > 0) {
            st.actualizar(sc.nextInt(), ppt.indexOf(sc.next()));
            output.append(ppt.get(st.ganador()) + "\n");
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
        static final int[][] res = new int[][] { { 1, 0, 2 }, { 0, 2, 1 } };
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
            construir(1, ini, fin, 0);
        }

        private void construir(int actual, int ini, int fin, int nivel) {
            if (ini == fin) {
                arbol[actual] = datos[ini];
            } else {
                int medio = (ini + fin) / 2, izq = actual * 2, der = actual * 2 + 1;
                construir(izq, ini, medio, nivel + 1);
                construir(der, medio + 1, fin, nivel + 1);
                arbol[actual] = combinar(arbol[izq], arbol[der], nivel + 1);
            }
        }

        int combinar(int a, int b, int nivel) {
            if (a == b)
                return a;

            return res[nivel % 2 == n % 2 ? 0 : 1][a + b - 1];
        }

        int ganador() {
            return ganador(this.datoIni, this.datoFin);
        }

        int ganador(int ini, int fin) {
            return ganador(1, ini, fin, this.datoIni, this.datoFin, 0);
        }

        private int ganador(int actual, int ini, int fin, int tI, int tF, int nivel) {
            if (ini > fin)
                return Integer.MAX_VALUE;
            if (ini == tI && fin == tF)
                return arbol[actual];

            int tM = (tI + tF) / 2;
            return combinar(ganador(actual * 2, ini, Math.min(fin, tM), tI, tM, nivel + 1),
                    ganador(actual * 2 + 1, Math.max(ini, tM + 1), fin, tM + 1, tF, nivel + 1), nivel + 1);
        }

        void actualizar(int elem, int nuevoValor) {
            datos[elem] = nuevoValor;
            actualizar(1, this.datoIni, this.datoFin, elem, 0);
        }

        private void actualizar(int actual, int tI, int tF, int elem, int nivel) {
            if (tI == tF) {
                arbol[actual] = datos[elem];
            } else {
                int tM = (tI + tF) / 2, izq = actual * 2, der = actual * 2 + 1;

                if (elem <= tM) {
                    actualizar(izq, tI, tM, elem, nivel + 1);
                } else {
                    actualizar(der, tM + 1, tF, elem, nivel + 1);
                }

                arbol[actual] = combinar(arbol[izq], arbol[der], nivel + 1);
            }
        }
    }
}