import java.util.*;
import java.io.*;

public class P7D {
    static Scanner sc;
    static List<int[]> laberinto = new ArrayList<>();
    static Map<Pair, Integer> mapa = new HashMap<>();
    static int n;
    static char[] dir = { 'F', 'B', 'L', 'R' };
    static int[][] padres;
    static int salida;

    public static void main(String[] args) throws IOException {
        File f = new File("inputs/07/D.txt");
        sc = new Scanner(f);
        n = sc.nextInt();
        // sc.nextLine();

        solve();
    }

    public static void solve() {
        laberinto();
        bfs();
    }

    public static void laberinto() {
        int ini = 0, aModificar[] = { 1, 0, 3, 2 };
        Pair pos = new Pair(0, 0), suma[] = { new Pair(0, 1), new Pair(0, -1), new Pair(-1, 0), new Pair(1, 0) };
        laberinto.add(new int[] { -1, -1, -1, -1 });

        for (int i = 0; i < n; i++) {
            mapa.put(pos, ini);
            char dir = sc.next().charAt(0);
            int casilla = 4, nueva[] = { -1, -1, -1, -1 }, nodo[] = laberinto.get(ini);
            switch (dir) {
                case 'F':
                    casilla--;
                case 'B':
                    casilla--;
                case 'L':
                    casilla--;
                case 'R':
                    casilla--;
            }

            pos = pos.sumPair(suma[casilla]);
            int sig = mapa.getOrDefault(pos, -1);
            if (sig < 0) {
                int hasta = laberinto.size();
                nodo[casilla] = hasta;
                nueva[aModificar[casilla]] = ini;
                laberinto.add(nueva);
                ini = hasta;
            } else {
                nodo[casilla] = sig;
                laberinto.get(sig)[aModificar[casilla]] = ini;
                ini = sig;
            }
        }
        salida = ini;
    }

    public static void bfs() {
        int tamanyo = laberinto.size();
        boolean[] visitados = new boolean[tamanyo];
        padres = new int[2][tamanyo]; // El 1.er array guarda el padre y el 2.º el movimiento para alcanzarlo.
        Queue<Integer> pendientes = new LinkedList<Integer>();
        int ini = 0, nodo = 0;
        padres[0][0] = -1;
        pendientes.offer(ini);

        while (!pendientes.isEmpty() && nodo != salida) {
            nodo = pendientes.poll();
            visitados[nodo] = true;
            int[] vecinos = laberinto.get(nodo);
            for (int i = 0; i < vecinos.length; i++) {
                if (vecinos[i] != -1 && !visitados[vecinos[i]]) {
                    pendientes.offer(vecinos[i]);
                    padres[0][vecinos[i]] = nodo;
                    padres[1][vecinos[i]] = i;
                }
            }
        }

        recorrido(nodo);
    }

    public static void recorrido(int nodo) {
        if (padres[0][nodo] != -1) {
            recorrido(padres[0][nodo]);
            System.out.print(dir[padres[1][nodo]] + " ");
        }
    }
}

class Pair {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pair sumPair(Pair p) {
        return new Pair(this.x + p.x, this.y + p.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Pair))
            return false;
        Pair p = (Pair) o;
        return p.x == this.x && p.y == this.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}