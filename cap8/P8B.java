import java.util.*;
import java.io.*;

public class P8B {
    static Lector lector;
    static StringBuilder output;
    static ArrayList<Integer>[] grafo;
    static int n, padre[];

    public static void main(String[] args) throws IOException {
        File f = new File("inputs/8/B.txt");
        lector = new Lector();
        lector.leerArchivo(f);
        output = new StringBuilder();

        solve();

        System.out.print(output.toString().trim());
    }

    public static void solve() throws IOException {
        n = lector.nextInt();
        int m = lector.nextInt();
        int X = lector.nextInt(), Y = lector.nextInt();

        // Creamos el grafo:
        crearGrafo(m);

        // Calculamos el camino más corto entre X e Y usando BFS:
        bfs(X, Y);

        // Vemos por qué puntos ha pasado ese camino:
        recorrido(Y, 0);
    }

    @SuppressWarnings("unchecked")
    public static void crearGrafo(int m) throws IOException {
        grafo = new ArrayList[n + 1];

        // Rellenamos dicha lista iterando sobre las conexiones:
        while (m-- > 0) {
            int a = lector.nextInt(), b = lector.nextInt();
            if (grafo[a] == null) {
                grafo[a] = new ArrayList<>();
            }
            if (grafo[b] == null) {
                grafo[b] = new ArrayList<>();
            }
            grafo[a].add(b);
            grafo[b].add(a);
        }
    }

    public static void bfs(int ini, int fin) {
        boolean[] visitado = new boolean[n + 1];
        padre = new int[n + 1];
        Queue<Integer> pendientes = new LinkedList<>();

        visitado[ini] = true;
        padre[ini] = -1;
        pendientes.offer(ini);

        while (!pendientes.isEmpty()) {
            int actual = pendientes.poll();

            // Si el nodo actual es el destino, terminamos la búsqueda.
            if (actual == fin)
                break;

            // Si no, vemos quiénes son los vecinos del nodo actual
            // y añadimos a la lista de pendientes los que no hayamos visitado aún:
            for (int vecino : grafo[actual]) {
                if (!visitado[vecino]) {
                    visitado[vecino] = true;
                    padre[vecino] = actual;
                    pendientes.offer(vecino);
                }
            }
        }
    }

    public static void recorrido(int actual, int pasos) {
        // Mientras no haya llegado al nodo inicial (de padre -1), llamo recursivamente
        // a la función añadiendo 1 paso. Cuando llegue, muestro el total de pasos:
        if (padre[actual] != -1) {
            recorrido(padre[actual], pasos + 1);
        } else {
            System.out.println(pasos);
        }

        // Voy añadiendo los nodos que voy recorriendo al string de salida:
        output.append(actual + " ");
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

        int nextShort() throws IOException {
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