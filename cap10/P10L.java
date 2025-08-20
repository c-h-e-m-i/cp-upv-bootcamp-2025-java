import java.io.*;
import java.util.*;

public class P10L {
    static Lector sc;
    static StringBuilder output;

    public static void main(String[] args) throws IOException {
        // Inicializamos entrada y salida:
        inicializar(10, 'L');

        // Resolvemos el problema:
        solve();

        // Mostramos la solución:
        System.out.print(output);
    }

    public static void solve() throws IOException {
        final int MOD = (int) Math.pow(10, 9) + 7;

        // Leemos la entrada:
        int n = sc.nextInt(), k = sc.nextInt(), piedra[] = new int[k + 1];

        /*
         * La idea aquí es bastante interesante. A simple vista parece que tenemos que
         * ir recorriendo la ventana entera de saltos posibles por cada piedra, pero
         * realmente se puede simplificar mucho más:
         * 
         * Imagina que el saltamontes puede saltar hasta 3 piedras más alante. Eso
         * significa que, por ejemplo, podrá alcanzar la piedra 18 desde las piedras 15,
         * 16 y 17. Ello implica a su vez que el número de formas llegar a la piedra 18
         * es igual a la suma del número de formas de llegar a las 3 anteriores.
         * 
         * Aquí es donde sacamos cierta lógica recursiva: esos 3 valores anteriores
         * serán a su vez la suma de sus 3 valores anteriores. Es decir:
         * 'piedra[18]' = 'piedra[17]' + 'piedra[16]' + 'piedra[15]'
         * = ('piedra[16]' + 'piedra[15]' + 'piedra[14]') + 'piedra[16]' + 'piedra[15]'
         * = 2 * ('piedra[16]' + 'piedra[15]') + 'piedra[14]'
         * 
         * Podemos reescribir esa última expresión como:
         * 2 * ('piedra[16]' + 'piedra[15]') + 'piedra[14]'
         * = 2 * ('piedra[16]' + 'piedra[15]' + 'piedra[14]') - 'piedra[14]'
         * = 2 * 'piedra[17]' - 'piedra[14]'
         * 
         * Es decir, el número de maneras de llegar a una piedra es igual al doble de
         * maneras de llegar a la piedra anterior - las maneras de llegar a la última
         * piedra desde la cual no se puede saltar directamente hacia la actual. O, lo
         * que es lo mismo:
         * 'piedra[actual]' = 2 * 'piedra[anterior]' - 'piedra[anterior - salto máximo]'
         * 
         * Cabe destacar que el restando solo se aplica para piedras de índice mayor al
         * salto máximo (+ 1 porque estoy en 1-based), pues todas las anteriores se van
         * a poder acceder desde la piedra 1. Por ejemplo, si mi salto máximo es de 3,
         * hasta la piedra 5 no tengo que empezar a restar, pues todas las maneras
         * posibles de llegar a las piedras 1, 2 y 3 también son maneras de llegar a la
         * piedra 4, mientras que para la piedra 5 tendré que excluir las maneras de
         * llegar a la piedra 1 en el conteo.
         * 
         * Si te das cuenta, no deja de ser una generalización de Fibonacci donde
         * sumamos los k términos anteriores en vez de solo 2 (lo que se conoce como
         * k-Fibonacci).
         * 
         * Para optimizar espacio en memoria, emplearemos una ventana deslizante de
         * 'v' = 'k' + 1 elementos en vez de guardar los valores de todas las piedras.
         */
        if (n == 1) {
            output.append(1);
            return;
        }

        piedra[1] = 1;
        piedra[2] = 1;
        int v = k + 1;

        for (int i = 2; i < n; i++)
            piedra[(i + 1) % v] = mod(piedra[i % v] * 2 - (i - k > 0 ? piedra[(i - k) % v] : 0), MOD);

        output.append(piedra[n % v]);
    }

    // Este es el módulo para cuando 'a' puede ser negativo (siendo 'b' positivo. Si
    // no lo fuera, habría que añadir la línea comentada):
    public static int mod(int a, int b) {
        // b = Math.abs(b);
        int res = a % b;
        if (res < 0)
            res += b;
        return res;
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
