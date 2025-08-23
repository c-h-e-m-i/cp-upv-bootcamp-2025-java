import java.io.*;
import java.util.*;

public class P11G {
    static Lector sc;
    static StringBuilder output;

    public static void main(String[] args) throws IOException {
        // Inicializamos entrada y salida:
        inicializar(11, 'G');

        // Resolvemos el problema:
        int t = sc.nextInt();
        while (t-- > 0)
            solve();

        // Imprimimos la salida y cerramos el flujo de entrada:
        System.out.print(output);
        sc.cerrar();
    }

    public static void solve() throws IOException {
        // Leemos la entrada:
        int p = sc.nextInt();

        /*
         * Este problema es muy interesante. Podríamos hacerlo a fuerza bruta comparando
         * todos los pares posibles de puntos, pero eso nos daría TLE, así que vamos a
         * tener que ingeniárnoslas un poco más:
         * 
         * - Si te fijas, a la hora de ver si un punto es alcanzable por la brújula, lo
         * que hacemos es trazar 4 rayos a través del punto donde está la brújula: uno
         * horizontal, otro vertical y dos diagonales perpendiculares.
         * 
         * - Para que 2 puntos sean alcanzables entre sí, deben estar contenidos en un
         * mismo rayo, y un mismo par de puntos nunca va a coincidir en más de 1 rayo,
         * porque de lo contrario estaríamos implicando que se pueden trazar 2 rectas de
         * distinta dirección entre ambos puntos.
         * 
         * - Sabiendo esto, podemos representar cada tipo de rayo como un punto de corte
         * con los ejes. Los rayos verticales vendrán dados por su corte con el eje X,
         * mientras que para los otros 3 usaremos el corte con el eje Y (aunque en el
         * caso de las diagonales ambos ejes valdrían). Calcular dichos cortes con los
         * ejes es bastante sencillo:
         * 
         * > Para los rayos horizontales y verticales nos quedamos con una de las
         * coordenadas.
         * 
         * > Para las diagonales, teniendo en cuenta que la pendiente es 1 para la
         * diagonal positiva y -1 para la negativa, y que tenemos que dar |x| pasos para
         * llegar al eje Y, el corte de la diagonal positiva valdrá (y + x) y el de la
         * negativa (y - x).
         * 
         * - Podemos crear un HashMap para cada tipo de rayo, y guardar la cantidad de
         * rayos que se cortan en cada punto de los ejes. Por ejemplo, si la entrada '3'
         * del HashMap de la diagonal positiva vale 5, significa que hay 5 puntos en el
         * rayo en forma de diagonal positiva que corta al eje Y en el valor 3.
         * 
         * - Una vez rellenados los HashMaps, podemos recorrerlos y calcular cuántos
         * pares de puntos hay en cada rayo. Volviendo al ejemplo de antes, si en un
         * mismo rayo tenemos 5 puntos, el número de pares será la cantidad de
         * PERMUTACIONES de 2 elementos que hay en un conjunto de 5. Es decir:
         * 
         * 5! / (5 - 2)! = 5 * 4 = 20.
         */
        HashMap<Integer, Integer> proyX = new HashMap<>(), proyY = new HashMap<>(), diagPos = new HashMap<>(),
                diagNeg = new HashMap<>();
        for (int i = 0; i < p; i++) {
            int x = sc.nextInt(), y = sc.nextInt();
            proyX.merge(x, 1, Integer::sum);
            proyY.merge(y, 1, Integer::sum);
            diagPos.merge(y - x, 1, Integer::sum);
            diagNeg.merge(y + x, 1, Integer::sum);
        }

        long total = sumaPares(proyX) + sumaPares(proyY) + sumaPares(diagPos) + sumaPares(diagNeg);
        output.append(total).append('\n');
    }

    public static long sumaPares(Map<Integer, Integer> rayo) {
        long total = 0;
        for (int v : rayo.values())
            total += 1L * v * (v - 1);
        return total;
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

        void cerrar() throws IOException {
            br.close();
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