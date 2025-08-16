import java.util.*;
import java.io.*;

public class P9B {
    static Lector sc;
    static StringBuilder output;
    static PriorityQueue<Carretera> grafo;
    static int n, m, padreDe[];

    public static void main(String[] args) throws IOException {
        File f = new File("inputs/9/B.txt");
        sc = new Lector();
        sc.leerArchivo(f);
        output = new StringBuilder();

        solve();

        System.out.print(output);
    }

    public static void solve() throws IOException {
        crearGrafo();
        output.append((n - 1) + "\n"); // Siempre se van a reformar (n - 1) carreteras.
        kruskal();
    }

    public static void crearGrafo() throws IOException {
        n = sc.nextInt();
        m = sc.nextInt();
        grafo = new PriorityQueue<>();
        padreDe = new int[n + 1];

        for (int i = 1; i <= m; i++) {
            int u = sc.nextInt(), v = sc.nextInt();
            long d = sc.nextLong();
            grafo.offer(new Carretera(u, v, d, i));
        }
    }

    public static void kruskal() {
        for (int i = 1; i <= n; i++) {
            crearSet(i);
        }

        int numCarreteras = 0;
        while (!grafo.isEmpty() && numCarreteras < n - 1) {
            Carretera actual = grafo.poll();

            if (combinarSets(actual.origen, actual.destino)) {
                output.append(actual.indice + "\n");
                numCarreteras++;
            }
        }
    }

    public static void crearSet(int nodo) {
        padreDe[nodo] = nodo;
    }

    public static int verSet(int nodo) {
        if (nodo == padreDe[nodo])
            return nodo;

        return padreDe[nodo] = verSet(padreDe[nodo]);
    }

    public static boolean combinarSets(int n1, int n2) {
        int set1 = verSet(n1), set2 = verSet(n2);
        padreDe[set1] = verSet(set2);
        return set1 != set2;
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

    private static class Carretera implements Comparable<Carretera> {
        int origen, destino, indice;
        long longitud;

        Carretera(int a, int b, long c, int d) {
            this.origen = a;
            this.destino = b;
            this.longitud = c;
            this.indice = d;
        }

        @Override
        public int compareTo(Carretera c) {
            long dif = this.longitud - c.longitud;
            if (dif == 0)
                return this.indice - c.indice;
            else
                return (int) Math.signum(dif);
        }
    }
}