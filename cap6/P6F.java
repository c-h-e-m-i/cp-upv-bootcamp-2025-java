import java.util.*;
import java.io.*;

public class P6F {
    public static boolean[] grafo;
    public static int w, h, ini, fin;

    public static void main(String[] args) throws IOException {
        File f = new File("inputs/6/F.txt");
        Scanner sc = new Scanner(f);
        w = sc.nextInt();
        h = sc.nextInt();
        sc.nextLine(); // Porque el sc.nextInt() no consume el salto de línea.

        // Guardar grafo:
        grafo = new boolean[w * h];
        int pos = 0;
        for (int i = 0; i < h; i++) {
            String aux = sc.nextLine();

            for (int j = 0; j < w; j++) {
                grafo[pos] = aux.charAt(j) != '#';
                if (aux.charAt(j) == 'E')
                    ini = pos;
                else if (aux.charAt(j) == 'S')
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
                    return pasos + 2;
                }
                int resultado = dfs(vecino, pasos + 1);
                if (resultado > -1)
                    return resultado;
            }
        }

        return -1;
    }

    public static int[] vecinos(int nodo) {
        int tamanyo = w * h, alrededores[] = { 1, -1, w, -w }, aux[] = new int[4];
        for (int i = 0; i < alrededores.length; i++) {
            // Vemos si los vecinos están dentro del grafo y guardamos su índice:
            int vecino = nodo + alrededores[i];
            // ---- Comprobamos que restar o sumar 1 al vecino no hace que saltemos de
            // línea:
            if (Math.abs(alrededores[i]) == 1 && vecino / w != nodo / w)
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
