
// Este es mi problema wiii

import java.io.*;
import java.util.*;

public class P10J {
    static Lector sc;
    static StringBuilder output;

    public static void main(String[] args) throws IOException {
        // Inicializamos entrada y salida:
        inicializar(10, 'J');

        // Resolvemos el problema:
        solve();

        // Mostramos la solución:
        System.out.print(output);
    }

    public static void solve() throws IOException {
        // Leemos la entrada:
        int n = sc.nextInt();

        /*
         * La lógica a seguir es similar a la de problemas anteriores: creo un vector de
         * tamaño 'n + 1' (para que tenga índices para los valores 1..'n') y voy
         * recorriendo sus entradas. En cada una, calculo el resultado de sumar 1, y
         * multiplicar por 2 y 3, y en las posiciones asociadas a dichos resultados me
         * guardo el mínimo entre el valor que ya tenían y el valor de mi entrada actual
         * + 1 (pues estoy haciendo 1 movimiento desde ella para llegar a las otras):
         */
        int[] valor = new int[n + 1];
        Arrays.fill(valor, Integer.MAX_VALUE);
        valor[1] = 0;

        for (int i = 1; i < n; i++) {
            int suma1 = i + 1, mult2 = i * 2, mult3 = i * 3, movs = valor[i] + 1;

            valor[suma1] = Math.min(movs, valor[suma1]);

            if (mult2 <= n)
                valor[mult2] = Math.min(movs, valor[mult2]);

            if (mult3 <= n)
                valor[mult3] = Math.min(movs, valor[mult3]);
        }

        output.append(valor[n]);
    }

    // -------- CLASES Y MÉTODOS AUXILIARES --------
    public static void inicializar(int cap, char prob) throws IOException {
        File f = new File(String.format("inputs/%02d/%C.txt", cap, prob));
        sc = new Lector();
        sc.leerArchivo(f);
        output = new StringBuilder();
    }

    public static void inicializar() {
        sc = new Lector();
        sc.leerStd();
        output = new StringBuilder();
    }

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
                    resto.append(st.nextToken()).append(" ");
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
