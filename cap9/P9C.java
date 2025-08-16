import java.util.*;
import java.io.*;

public class P9C {
    static Lector sc;
    static StringBuilder output;
    static Queue<Conexion> grafo;
    static int n, m, padreDe[], tamSets[];
    static boolean[] invalido;

    public static void main(String[] args) throws IOException {
        File f = new File("inputs/9/C.txt");
        sc = new Lector();
        sc.leerArchivo(f);
        output = new StringBuilder();

        solve();

        System.out.print(output);
    }

    public static void solve() throws IOException {
        crearGrafo();
        kruskal();

        ArrayList<Integer> setsValidos = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (!invalido[i] && tamSets[i] > 0) {
                setsValidos.add(tamSets[i] + 1);
            }
        }

        Collections.sort(setsValidos);
        for (int i : setsValidos)
            output.append(i + "\n");
    }

    public static void crearGrafo() throws IOException {
        n = sc.nextInt();
        m = sc.nextInt();
        grafo = new LinkedList<>();
        padreDe = new int[n + 1];
        tamSets = new int[n + 1];

        for (int i = 1; i <= m; i++) {
            int u = sc.nextInt(), v = sc.nextInt();
            grafo.offer(new Conexion(u, v));
        }
    }

    public static void kruskal() {
        invalido = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            crearSet(i);
        }

        while (!grafo.isEmpty()) {
            Conexion actual = grafo.poll();
            int setOrigen = verSet(actual.origen), setDestino = verSet(actual.destino);

            if (invalido[setOrigen] || invalido[setDestino] || setOrigen == setDestino) {
                invalido[setOrigen] = true;
                invalido[setDestino] = true;
                continue;
            }

            padreDe[setOrigen] = setDestino;
            tamSets[setDestino] += tamSets[setOrigen] + 1;
            tamSets[setOrigen] = 0;
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

    private static class Conexion {
        int origen, destino;

        Conexion(int a, int b) {
            this.origen = a;
            this.destino = b;
        }
    }
}