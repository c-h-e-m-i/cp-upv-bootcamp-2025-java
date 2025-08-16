import java.util.*;
import java.io.*;

public class P6G {
    static int[][] matriz;

    public static void main(String[] args) throws IOException {
        File f = new File("inputs/6/G.txt");
        Scanner sc = new Scanner(f);
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            solve(sc);
        }
    }

    public static void solve(Scanner sc) {
        // Rellenamos la matriz:
        int n = sc.nextInt(), m = sc.nextInt();
        matriz = new int[n][m + 1];
        for (int i = 0; i < n; i++) {
            int min = Integer.MAX_VALUE, minPos = -1;
            for (int j = 0; j < m; j++) {
                int aux = sc.nextInt();
                matriz[i][j] = aux;

                // Me guardo el índice del mínimo de cada fila en la última posición de la fila
                // para ahorrarme comprobaciones:
                if (aux < min) {
                    min = aux;
                    minPos = j;
                }
            }
            matriz[i][m] = minPos;
        }

        // Comparo el mínimo de cada fila con el resto de su columna para ver si es
        // también el máximo de la columna:
        int i = 0;
        while (i < n && !propagar(i, matriz[i][m]))
            i++;

        if (i < n)
            System.out.println(i + " " + matriz[i][m]);
        else
            System.out.println("-1 -1");
    }

    public static boolean propagar(int x, int y) {
        return propagar(x, y, 1, true) && propagar(x, y, 1, false);
    }

    public static boolean propagar(int x, int y, int salto, boolean dir) {
        // Si dir == true --> Dirección hacia arriba.
        // Si dir == false --> Dirección hacia abajo.
        if (dir) {
            if (x - salto < 0)
                return true;
            if (matriz[x - salto][y] <= matriz[x][y])
                return propagar(x, y, salto + 1, dir);
            return false;
        } else {
            if (x + salto >= matriz.length)
                return true;
            if (matriz[x + salto][y] <= matriz[x][y])
                return propagar(x, y, salto + 1, dir);
            return false;
        }
    }
}
