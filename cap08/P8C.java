import java.util.*;
import java.io.*;

public class P8C {
    static Lector lector;
    static StringBuilder output;
    static ArrayList<Integer>[] grafo;
    static boolean[] visitados;
    static int n;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        File f = new File("inputs/08/C.txt");
        lector = new Lector();
        lector.leerArchivo(f);
        output = new StringBuilder();
        n = lector.nextInt();
        grafo = new ArrayList[n + 2];

        boolean yaConexo = false;
        for (int i = 0; i < n && !yaConexo; i++) {
            yaConexo = solve();
            System.out.println(yaConexo ? "NO" : "SI");
        }

        System.out.print(output);
    }

    public static boolean solve() throws IOException {
        lector.next();
        int origen = lector.nextInt(), destino = lector.nextInt();
        if (grafo[origen] == null)
            grafo[origen] = new ArrayList<>();
        if (grafo[destino] == null)
            grafo[destino] = new ArrayList<>();

        visitados = new boolean[n + 2];
        boolean res = dfs(origen, destino);
        rellenarGrafo(origen, destino);
        return res;
    }

    public static boolean dfs(int ini, int fin) {
        if (ini == fin)
            return true;

        visitados[ini] = true;

        for (int vecino : grafo[ini]) {
            if (!visitados[vecino]) {
                if (dfs(vecino, fin))
                    return true;
            }
        }

        return false;
    }

    public static void rellenarGrafo(int a, int b) {
        grafo[a].add(b);
        grafo[b].add(a);
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
}