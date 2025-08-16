import java.util.*;
import java.io.*;

public class P9G {
    static Lector sc;
    static StringBuilder output;

    public static void main(String[] args) throws IOException {
        File f = new File("inputs/9/G.txt");
        sc = new Lector();
        sc.leerArchivo(f);
        output = new StringBuilder();

        solve();

        System.out.print(output);
    }

    public static void solve() throws IOException {
        String secuencia = sc.nextLine();
        int tam = secuencia.length(), peticiones = sc.nextInt();
        boolean[] parentesis = new boolean[tam + 1];

        for (int i = 0; i < tam; i++) {
            parentesis[i + 1] = secuencia.charAt(i) < ')';
        }

        SegmentTree st = new SegmentTree(parentesis);
        st.construir(1, tam);

        while (peticiones-- > 0) {
            output.append(st.maxSub(sc.nextInt(), sc.nextInt()) + "\n");
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
                int izq = datos[ini] ? 1 : 0, der = 1 - izq;
                arbol[actual] = new Nodo(false, izq, der, 1);
            } else {
                int medio = (ini + fin) / 2, izq = actual * 2, der = actual * 2 + 1;
                construir(izq, ini, medio);
                construir(der, medio + 1, fin);
                arbol[actual] = combinar(arbol[izq], arbol[der]);
            }
        }

        Nodo combinar(Nodo a, Nodo b) {
            if (a.valor && b.valor)
                return new Nodo(true, 0, 0, a.tam + b.tam);

            int izq = Math.max(a.izq - b.der, 0) + b.izq, der = Math.max(b.der - a.izq, 0) + a.der;
            return new Nodo(izq + der == 0, izq, der, a.tam + b.tam);
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
                return new Nodo(true, 0, 0, 0);

            if (ini == tI && fin == tF) {
                int eliminados = arbol[actual].tam - (arbol[actual].izq + arbol[actual].der);
                if (eliminados > this.max)
                    this.max = eliminados;
                return arbol[actual];
            }

            int tM = (tI + tF) / 2;
            Nodo izq = maxSub(actual * 2, ini, Math.min(fin, tM), tI, tM),
                    der = maxSub(actual * 2 + 1, Math.max(ini, tM + 1), fin, tM + 1, tF);

            Nodo padre = combinar(izq, der);
            int eliminados = padre.tam - (padre.izq + padre.der);
            if (eliminados > this.max)
                this.max = eliminados;

            return padre;
        }

        void actualizar(int elem, boolean nuevoValor) {
            datos[elem] = nuevoValor;
            actualizar(1, this.datoIni, this.datoFin, elem);
        }

        private void actualizar(int actual, int tI, int tF, int elem) {
            if (tI == tF) {
                int izq = datos[elem] ? 1 : 0, der = 1 - izq;
                arbol[actual] = new Nodo(false, izq, der, 1);
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
        boolean valor;
        int izq, der, tam;

        Nodo(boolean valor, int izq, int der, int tam) {
            this.valor = valor;
            this.izq = izq;
            this.der = der;
            this.tam = tam;
        }
    }
}