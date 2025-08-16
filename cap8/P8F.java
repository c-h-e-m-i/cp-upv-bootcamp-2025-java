import java.util.*;
import java.io.*;

public class P8F {
    static Lector sc;
    static StringBuilder output;
    static ArrayList<Calle>[] grafo;
    static boolean[] visitado;
    static int n, caminos, min;

    public static void main(String[] args) throws IOException {
        File f = new File("inputs/8/F.txt");
        sc = new Lector();
        sc.leerArchivo(f);
        output = new StringBuilder();
        int t = sc.nextInt();

        while (t-- > 0)
            solve();

        System.out.print(output);
    }

    public static void solve() throws IOException {
        crearGrafo();
        dfs(1, 0);
        output.append(caminos + "\n");
    }

    @SuppressWarnings("unchecked")
    public static void crearGrafo() throws IOException {
        n = sc.nextInt() + 1;
        grafo = new ArrayList[n];
        visitado = new boolean[n];
        visitado[1] = true;
        caminos = 0;
        min = Integer.MAX_VALUE;
        int m = sc.nextInt();
        for (int i = 1; i < n; i++) {
            grafo[i] = new ArrayList<>();
        }

        while (m-- > 0) {
            int u = sc.nextInt(), v = sc.nextInt(), dist = sc.nextInt();
            grafo[u].add(new Calle(dist, v));
            grafo[v].add(new Calle(dist, u));
        }
    }

    public static void dfs(int ini, int total) {
        if (ini == n - 1) {
            if (total == min) {
                caminos++;
            } else if (total < min) {
                min = total;
                caminos = 1;
            }

            visitado[ini] = false;
            return;
        }

        for (Calle calle : grafo[ini]) {
            if (!visitado[calle.dest] && total + calle.dist <= min) {
                visitado[calle.dest] = true;
                dfs(calle.dest, total + calle.dist);
            }
        }

        visitado[ini] = false;
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
    private static class Calle {
        int dist, dest;

        Calle(int dist, int dest) {
            this.dist = dist;
            this.dest = dest;
        }
    }
}