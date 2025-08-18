import java.util.*;
import java.io.*;

public class P8G {
    static Lector sc;
    static StringBuilder output;
    static ArrayList<Calle>[] grafo;
    static int n, t;
    static boolean[] visitado;

    public static void main(String[] args) throws IOException {
        File f = new File("inputs/08/G.txt");
        sc = new Lector();
        sc.leerArchivo(f);
        output = new StringBuilder();
        n = sc.nextInt();

        solve();

        System.out.print(output);
    }

    public static void solve() throws IOException {
        t = sc.nextInt();
        int vA = sc.nextInt(), vB = sc.nextInt(), iniA = sc.nextInt(), iniB = sc.nextInt();

        crearGrafo();

        int[] distA = new int[n + 1];
        Arrays.fill(distA, Integer.MAX_VALUE);
        dfs(iniA, vA, 0, distA);

        int[] distB = new int[n + 1];
        Arrays.fill(distB, Integer.MAX_VALUE);
        dfs(iniB, vB, 0, distB);

        System.out.println(comparar(distA, distB, vA, vB));
    }

    @SuppressWarnings("unchecked")
    public static void crearGrafo() throws IOException {
        grafo = new ArrayList[n + 1];
        visitado = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            grafo[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt(), v = sc.nextInt(), d = sc.nextInt();
            grafo[u].add(new Calle(d, v));
            grafo[v].add(new Calle(d, u));
        }
    }

    public static void dfs(int ini, int v, int total, int[] dist) {
        visitado[ini] = true;
        dist[ini] = total;

        for (Calle calle : grafo[ini]) {
            if (!visitado[calle.dest] && total + calle.dist <= t * v) {
                dfs(calle.dest, v, total + calle.dist, dist);
            }
        }

        visitado[ini] = false;
    }

    public static int comparar(int[] distA, int[] distB, int vA, int vB) {
        int contador = 0;

        for (int i = 1; i <= n; i++) {
            if (distA[i] <= t * vA && distB[i] <= t * vB) {
                contador++;
                output.append(i + " ");
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

        double nextFloat() throws IOException {
            return Float.parseFloat(next());
        }

        double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }

        boolean nextBoolean() throws IOException {
            return Boolean.parseBoolean(next());
        }
    }

    private static class Calle {
        int dist, dest;

        Calle(int dist, int dest) {
            this.dist = dist;
            this.dest = dest;
        }
    }
}