import java.util.*;
import java.io.*;

public class P6E {
    public static boolean[] grafo;
    public static int n, ini, fin;

    public static void main(String[] args) throws IOException {
        File f = new File("inputs/6/E.txt");
        Scanner sc = new Scanner(f);
        n = sc.nextInt();
        sc.nextLine(); // Porque el sc.nextInt() no consume el salto de línea.

        // Guardar grafo:
        grafo = new boolean[n * n];
        int pos = 0;
        for (int i = 0; i < n; i++) {
            String aux = sc.nextLine();

            for (int j = 0; j < aux.length(); j++) {
                grafo[pos] = aux.charAt(j) != '.';
                if (aux.charAt(j) == 'R')
                    ini = pos;
                else if (aux.charAt(j) == 'C')
                    fin = pos;

                pos++;
            }
        }

        // Ejecutamos DFS:
        System.out.println(dfs(ini, 0));
        sc.close();
    }

    public static int dfs(int nodo, int pasos) {
        grafo[nodo] = false;
        int[] vecinos = vecinos(nodo);

        for (int vecino : vecinos) {
            if (vecino >= 0 && grafo[vecino]) {
                if (vecino == fin) {
                    return pasos + 1;
                }
                int resultado = dfs(vecino, pasos + 1);
                if (resultado > -1)
                    return resultado;
            }
        }

        return -1;
    }

    public static int[] vecinos(int nodo) {
        int tamanyo = n * n, alrededores[] = { -1, 1, n, -n }, aux[] = new int[4];
        for (int i = 0; i < alrededores.length; i++) {
            // Vemos si los vecinos están dentro del grafo y guardamos su índice:
            int vecino = nodo + alrededores[i];
            // ---- Comprobamos que restar o sumar 1 al vecino no hace que saltemos de
            // línea:
            if (Math.abs(alrededores[i]) == 1 && vecino / n != nodo / n)
                vecino = -1;
            // ---- Si el vecino es válido, nos guardamos su índice:
            if (vecino >= 0 && vecino < tamanyo) {
                aux[i] = grafo[vecino] ? vecino : -1;
            } else
                aux[i] = -1;
        }

        return aux;
    }
}
