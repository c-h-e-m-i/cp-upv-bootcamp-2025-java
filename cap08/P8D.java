import java.util.*;
import java.io.*;

public class P8D {
    static Lector lector;
    static StringBuilder output;
    static ArrayList<Pair>[] grafo;
    static int[] padreDe;

    public static void main(String[] args) throws IOException {
        File f = new File("inputs/08/D.txt");
        lector = new Lector();
        lector.leerArchivo(f);
        output = new StringBuilder();
        int n = lector.nextInt();

        while (n-- > 0) {
            solve();
        }

        System.out.print(output);
    }

    public static void solve() throws IOException {
        int esquinas = lector.nextInt(), ini = lector.nextInt(), dist[];

        crearGrafo(esquinas);

        dist = dijkstra(ini, esquinas);

        for (int i = 0; i < esquinas; i++) {
            if (i != ini) {
                output.append(i + ":\n" + "riesgo " + dist[i] + "\n");
                int aux = padreDe[i];
                while (padreDe[aux] >= 0) {
                    output.append(aux + " <- ");
                    aux = padreDe[aux];
                }
                output.append(ini + "\n");
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static void crearGrafo(int nodos) throws IOException {
        grafo = new ArrayList[nodos];
        padreDe = new int[nodos];
        for (int i = 0; i < nodos; i++) {
            grafo[i] = new ArrayList<>();
        }

        for (int i = 0; i < nodos; i++) {
            int vecinos = lector.nextInt();
            while (vecinos-- > 0) {
                int vecino = lector.nextInt(), riesgo = lector.nextInt();
                grafo[i].add(new Pair(vecino, riesgo));
            }
        }
    }

    public static int[] dijkstra(int ini, int nodos) {
        Queue<Pair> pendientes = new PriorityQueue<>();
        int[] dist = new int[nodos];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[ini] = 0;
        padreDe[ini] = -1;
        pendientes.offer(new Pair(ini, 0));

        while (!pendientes.isEmpty()) {
            Pair actual = pendientes.poll();
            ini = actual.a;

            if (actual.b > dist[actual.a]) {
                continue;
            }

            for (Pair calle : grafo[ini]) {
                int destino = calle.a, riesgo = calle.b;

                if (dist[ini] + riesgo < dist[destino]) {
                    dist[destino] = dist[ini] + riesgo;
                    padreDe[destino] = ini;
                    pendientes.offer(new Pair(destino, dist[destino]));
                }
            }
        }

        return dist;
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
    private static class Pair implements Comparable<Pair> {
        int a, b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Pair p) {
            return this.b - p.b;
        }
    }
}