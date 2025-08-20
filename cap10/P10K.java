import java.io.*;
import java.util.*;

public class P10K {
    static Lector sc;
    static StringBuilder output;

    public static void main(String[] args) throws IOException {
        // Inicializamos entrada y salida:
        inicializar(10, 'K');

        // Resolvemos el problema:
        solve();

        // Mostramos la solución:
        System.out.print(output);
    }

    public static void solve() throws IOException {
        // Leemos la entrada:
        int n = sc.nextInt(), menores[] = new int[n + 1], max = 0;
        Arrays.fill(menores, Integer.MAX_VALUE);
        menores[0] = Integer.MIN_VALUE;

        /*
         * La lógica en este caso consiste en, para cada longitud 'l' posible, guardarse
         * el valor más pequeño que cierra una secuencia de longitud 'l'. Al mismo
         * tiempo vamos haciendo búsqueda binaria con 'lim_sup()' para ver cuál es la
         * primera longitud cuyo último valor es mayor o igual que el actual, cuya
         * entrada en 'menores' sobrescribiremos con dicho valor actual:
         */
        for (int i = 0; i < n; i++) {
            int val = sc.nextInt(), subsec = lim_sup(menores, 0, menores.length, val);
            menores[subsec] = val;

            if (subsec > max)
                max = subsec;
        }

        output.append(max);
    }

    /*
     * A diferencia de std::upper_bound de C++, voy a devolver el primer elemento
     * que sea mayor O IGUAL, para que no haya repetidos en menores[].
     * 
     * Para que devuelva el primero estrictamente mayor simplemente hay que alternar
     * entre >/>= y </<= en los ifs de las líneas 43 y 44.
     */
    public static int lim_sup(int[] a, int izq, int der, int val) {
        if (izq >= der)
            return der;

        int medio = (izq + der) / 2;

        if (a[medio] >= val) {
            if (medio == izq || a[medio - 1] < val)
                return medio;
            else
                return lim_sup(a, izq, medio, val);
        } else {
            return lim_sup(a, medio + 1, der, val);
        }
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
