import java.util.*;
import java.io.*;

public class P9H {
    static Lector sc;
    static StringBuilder output;

    public static void main(String[] args) throws IOException {
        File f = new File("inputs/9/H.txt");
        sc = new Lector();
        sc.leerArchivo(f);
        output = new StringBuilder();

        solve();

        System.out.print(output);
    }

    public static void solve() throws IOException {
        int n = sc.nextInt(), q = sc.nextInt();
        boolean[] capas = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            capas[i] = sc.next().charAt(0) > 'A';
        }

        SegmentTree st = new SegmentTree(capas);
        st.construir(1, n);

        while (q-- > 0) {
            switch (sc.next().charAt(0)) {
                case 's':
                    output.append(st.maxSub(sc.nextInt(), sc.nextInt()) + "\n");
                    break;
                case 'c':
                    int i = sc.nextInt();
                    st.actualizar(i, !capas[i]);
                    break;
            }
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
        int datoIni, datoFin, max;
        boolean[] datos;
        Nodo[] arbol;

        SegmentTree(boolean[] datos) {
            this.datos = datos;
            this.max = 0;
        }

        void construir() {
            construir(0, datos.length);
        }

        void construir(int ini, int fin) {
            this.datoIni = ini;
            this.datoFin = fin;
            this.arbol = new Nodo[4 * (this.datoFin - this.datoIni + 1)];
            construir(1, ini, fin);
        }

        private void construir(int actual, int ini, int fin) {
            if (ini == fin) {
                int izq, der;
                izq = der = datos[ini] ? 1 : 0;
                arbol[actual] = new Nodo(izq, der, 1, izq);
            } else {
                int medio = (ini + fin) / 2, izq = actual * 2, der = actual * 2 + 1;
                construir(izq, ini, medio);
                construir(der, medio + 1, fin);
                arbol[actual] = combinar(arbol[izq], arbol[der]);
            }
        }

        Nodo combinar(Nodo a, Nodo b) {
            int contiguos = a.der + b.izq,
                    max = Math.max(Math.max(contiguos, a.max), b.max),
                    izq = a.izq, der = b.der;

            if (a.izq == a.tam)
                izq += b.izq;

            if (b.der == b.tam)
                der += a.der;

            return new Nodo(izq, der, a.tam + b.tam, max);
        }

        int maxSub() {
            return maxSub(this.datoIni, this.datoFin);
        }

        int maxSub(int ini, int fin) {
            max = 0;
            maxSub(1, ini, fin, this.datoIni, this.datoFin);
            return max;
        }

        private Nodo maxSub(int actual, int ini, int fin, int tI, int tF) {
            if (ini > fin)
                return new Nodo(0, 0, 0, 0);

            if (ini == tI && fin == tF) {
                Nodo act = arbol[actual];
                if (act.max > this.max)
                    this.max = act.max;
                return act;
            }

            int tM = (tI + tF) / 2;
            Nodo izq = maxSub(actual * 2, ini, Math.min(fin, tM), tI, tM),
                    der = maxSub(actual * 2 + 1, Math.max(ini, tM + 1), fin, tM + 1, tF);

            Nodo padre = combinar(izq, der);
            if (padre.max > this.max)
                this.max = padre.max;

            return padre;
        }

        void actualizar(int elem, boolean nuevoValor) {
            datos[elem] = nuevoValor;
            actualizar(1, this.datoIni, this.datoFin, elem);
        }

        private void actualizar(int actual, int tI, int tF, int elem) {
            if (tI == tF) {
                int izq, der;
                izq = der = datos[elem] ? 1 : 0;
                arbol[actual] = new Nodo(izq, der, 1, izq);
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

    // @SuppressWarnings("unused")
    private static class Nodo {
        int izq, der, tam, max; // izq y der hacen referencia al número de frutas contiguas a la izquierda o
                                // derecha del nodo

        Nodo(int izq, int der, int tam, int max) {
            this.izq = izq;
            this.der = der;
            this.tam = tam;
            this.max = max;
        }
    }
}