import java.util.*;
import java.io.*;

public class P8J {
    static Lector sc;
    static StringBuilder output;
    static ArrayList<Calle>[] grafo;
    static String[] sitios;
    static int n, casaTomas, casaDani;
    static double d;

    public static void main(String[] args) throws IOException {
        File f = new File("inputs/08/J.txt");
        sc = new Lector();
        sc.leerArchivo(f);
        output = new StringBuilder();
        n = sc.nextInt();

        crearGrafo();
        int q = sc.nextInt();

        while (q-- > 0) {
            solve();
            System.out.print(output);
            output.setLength(0);
        }
    }

    public static void solve() throws IOException {
        String cT = sc.next(), cD = sc.next();
        casaTomas = indexOf(sitios, cT);
        casaDani = indexOf(sitios, cD);

        double[] distTomas = dijkstra(casaTomas), distDani = dijkstra(casaDani);
        System.out.println(comparar(distTomas, distDani));
    }

    @SuppressWarnings("unchecked")
    public static void crearGrafo() throws IOException {
        grafo = new ArrayList[n];
        sitios = new String[n];
        int m = sc.nextInt();
        d = sc.nextDouble();

        for (int i = 0; i < n; i++) {
            sitios[i] = sc.nextLine();
            grafo[i] = new ArrayList<>();
        }

        Arrays.sort(sitios); // Para que luego se mantenga el orden lexicográfico

        while (m-- > 0) {
            int a = indexOf(sitios, sc.next()), b = indexOf(sitios, sc.next());
            double dist = sc.nextDouble();
            grafo[a].add(new Calle(b, dist));
            grafo[b].add(new Calle(a, dist));
        }
    }

    public static int indexOf(String[] sitios, String sitio) {
        for (int i = 0; i < n; i++) {
            if (sitio.equals(sitios[i]))
                return i;
        }

        return -1;
    }

    public static double[] dijkstra(int ini) {
        PriorityQueue<Calle> pend = new PriorityQueue<>();
        double[] dist = new double[n];
        Arrays.fill(dist, Double.MAX_VALUE);
        dist[ini] = 0;
        pend.offer(new Calle(ini, 0));

        while (!pend.isEmpty()) {
            Calle actual = pend.poll();
            ini = actual.dest;

            if (actual.dist > dist[ini]) {
                continue;
            }

            for (Calle vecino : grafo[ini]) {
                if (dist[ini] + vecino.dist < dist[vecino.dest]) {
                    dist[vecino.dest] = dist[ini] + vecino.dist;
                    pend.offer(new Calle(vecino.dest, dist[vecino.dest]));
                }
            }
        }

        return dist;
    }

    public static int comparar(double[] tomas, double[] dani) {
        int contador = 0;

        for (int i = 0; i < n; i++) {
            if (Math.abs(tomas[i] - dani[i]) <= d + 0.01) {
                output.append(sitios[i] + "\n");
                contador++;
            }
        }

        return contador;
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
    private static class Calle implements Comparable<Calle> {
        int dest;
        double dist;

        Calle(int dest, double dist) {
            this.dest = dest;
            this.dist = dist;
        }

        @Override
        public int compareTo(Calle c) {
            if (this.dist < c.dist)
                return -1;
            if (this.dist > c.dist)
                return 1;
            return 0;
        }
    }
}