import java.io.*;
import java.util.*;

public class P10E {
    static Lector sc;
    static StringBuilder output;

    public static void main(String[] args) throws IOException {
        // Inicializamos entrada y salida:
        inicializar(10, 'E');

        solve();

        System.out.print(output);
    }

    public static void solve() throws IOException {
        // Leemos el valor de entrada y creamos el array 'comb' para realizar la DP:
        final int CARAS = 6, MODULO = 1_000_000_007;
        int n = sc.nextInt(), comb[] = new int[CARAS];

        /*
         * Seguiremos esta lógica: Imagina que estamos analizando el valor 'n' = 76
         * para el dado 5. Si 'comb[76]' = 2, significa que ya hemos encontrado
         * previamente 2 maneras de llegar a él. Del mismo modo, si 'comb[71]' = 4,
         * significa que hemos hallado 4 maneras de llegar al 71. ¿Qué significa
         * esto? Que si a esas 4 maneras de llegar a 'n' = 71 les sumo el dado 5,
         * estaré obteniendo 4 formas adicionales de llegar al 76, por lo que ahora
         * tendré 6 formas de llegar al 76. Por tanto, 'comb[76]' será igual a su
         * valor actual + 'comb[71]': 2 + 4 = 6.
         * 
         * Para reducir memoria, podemos implementar una "ventana rotativa" de tamaño 6,
         * pues lo más atrás que me voy a remontar en mi array es a la posición 6 uds.
         * anterior. Por ejemplo, el rango comb[6..11] ahora se guardará en comb[0..5].
         * El truco está en que, p. ej., al momento de actualizar comb[7], solo se habrá
         * sobrescrito el valor de comb[0], por lo que podremos seguir consultando las
         * entradas correspondientes a 'i' = [1..5], y también la entrada para 'i' = 6,
         * que estará guardada en comb[0]:
         */
        comb[0] = 1;
        for (int i = 1; i <= n; i++) {
            int aux = 0;
            for (int num = 1; num <= CARAS && num <= i; num++) {
                aux += comb[(i - num) % CARAS] % MODULO;
                aux %= MODULO;
            }
            comb[i % CARAS] = aux;
        }

        output.append(comb[n % CARAS]);
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