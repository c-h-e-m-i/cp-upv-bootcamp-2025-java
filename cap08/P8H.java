import java.util.*;
import java.io.*;

public class P8H {
    static Lector sc;
    static StringBuilder output;
    static Queue<Integer>[] grafo;
    static int n;

    public static void main(String[] args) throws IOException {
        File f = new File("inputs/08/H.txt");
        sc = new Lector();
        sc.leerArchivo(f);
        output = new StringBuilder();
        n = sc.nextInt();

        solve();

        System.out.print(output);
    }

    public static void solve() throws IOException {
        int ini = crearGrafo();

        caminoEuleriano(ini);
    }

    @SuppressWarnings("unchecked")
    public static int crearGrafo() throws IOException {
        int m = sc.nextInt(), ini = sc.nextInt();
        grafo = new Queue[n + 1];
        for (int i = 1; i <= n; i++) {
            grafo[i] = new LinkedList<>();
        }

        while (m-- > 0) {
            int u = sc.nextInt(), v = sc.nextInt();
            grafo[u].offer(v);
            grafo[v].offer(u);
        }

        return ini;
    }

    public static void caminoEuleriano(int ini) {
        int impares = 0;
        for (int i = 1; i < grafo.length; i++) {
            if (grafo[i].size() % 2 == 1) {
                impares++;

                if (impares > 2) {
                    output.append(-1);
                    return;
                }
            }
        }

        if (impares == 1 || (impares == 2 && grafo[ini].size() % 2 == 0)) {
            output.append(-1);
            return;
        }

        encontrarCamino(ini);
    }

    public static void encontrarCamino(int ini) {
        Stack<Integer> pendientes = new Stack<>(), res = new Stack<>();
        pendientes.push(ini);

        while (!pendientes.isEmpty()) {
            ini = pendientes.pop();
            if (grafo[ini].size() > 0) {
                int sig = grafo[ini].poll();
                grafo[sig].remove(ini);
                pendientes.push(ini);
                pendientes.push(sig);
                ini = sig;
            } else {
                res.push(ini);
            }
        }

        while (!res.isEmpty())
            output.append(res.pop() + "\n");
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
}