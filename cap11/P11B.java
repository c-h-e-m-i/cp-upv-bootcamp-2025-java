import java.io.*;
import java.util.*;

public class P11B {
    static Lector sc;
    static StringBuilder output;

    public static void main(String[] args) throws IOException {
        // Inicializamos entrada y salida:
        inicializar(11, 'B');

        // Resolvemos el problema:
        int t = sc.nextInt();
        while (t-- > 0)
            solve();

        // Imprimimos la salida y cerramos el flujo de entrada:
        System.out.print(output);
        sc.cerrar();
    }

    public static void solve() throws IOException {
        // Leemos la entrada (y normalizamos los valores para evitar overflow al
        // multiplicarlos):
        double x1 = sc.nextDouble(), y1 = sc.nextDouble(), x2 = sc.nextDouble(), y2 = sc.nextDouble(),
                max = max(x1, y1, x2, y2);

        if (max > 0) {
            x1 /= max;
            y1 /= max;
            x2 /= max;
            y2 /= max;
        }

        /*
         * Si bien hay varias maneras de sacar la ecuación de la recta, la más eficiente
         * de programar toma como base el determinante de los vectores que tienen como
         * punta los puntos 1 y 2.
         * 
         * La idea es la siguiente:
         * 
         * - El determinante se interpreta como el factor por el que se escala un área
         * en un plano (o un volumen en un cubo) al aplicar al espacio la matriz de
         * transformación sobre la que se está realizando el determinante.
         * 
         * - Sacar la ecuación de la recta que contiene los puntos 1 y 2 es equivalente
         * a sacar todos los puntos (x, y) que son colineales con 1 y 2. Para ello,
         * podemos usar determinantes.
         * 
         * - Lo primero que haremos será añadir una coordenada Z adicional a cada punto
         * (puede tomar cualquier valor salvo el 0, así que pondremos un 1). Colocamos
         * los puntos 1 y 2 + un punto variable (x, y) como columnas de una matriz:
         * 
         * | x1 x2 x |
         * | y1 y2 y |
         * | 01 01 1 |
         * 
         * - Si el determinante de esa matriz es igual a 0 significa que reduce a 0 el
         * volumen de un cuerpo en el espacio 3D (o sea que lo aplasta a una figura 2D o
         * a un punto). Para ello, los vectores de la matriz deben ser, como mínimo,
         * COPLANARIOS (ello hará que la base del espacio 3D pase a ser 2D,
         * transformando
         * cualquier figura 3D en una 2D). Podrían ser colineales también, pero para eso
         * los 3 vectores tendrían que ser el mismo (teniendo en cuenta que los 3 tienen
         * su punta en el plano Z = 1).
         * 
         * - Aquí es donde empieza la magia: como hemos dicho, si el determinante da 0,
         * sabemos que los vectores son coplanarios (como mínimo), pero esto tiene una
         * implicación aún más importante: si son coplanarios, significa que SUS PUNTAS
         * SON COLINEALES, pues para poder trazar un plano que contenga a los 3 sabiendo
         * que todos tienen la punta en el plano Z = 1, esas puntas deben estar
         * alineadas. ¿Qué implica esto? Que todos los puntos (x, y) que hagan que ese
         * determinante valga 0 serán colineales con 1 y 2, por lo que igualando a 0 y
         * despejando podremos sacar la ecuación:
         * 
         * | x1 x2 x |
         * | y1 y2 y | = 0 --> x1*y2 + x2*y + x*y1 - x*y2 - x1*y - x2*y1 = 0
         * | 01 01 1 |
         * 
         * --> (y1 - y2)*x + (x2 - x1)*y + (x1*y2 - x2*y1) = a*x + b*y + c = 0 -->
         * 
         * a = y1 - y2
         * b = x2 - x1
         * c = x1*y2 - x2*y1
         */
        double a = y1 - y2, b = x2 - x1, c = x1 * y2 - x2 * y1;

        /*
         * Al haber normalizado los puntos, la ecuación se nos ha quedado como:
         * 
         * a = y1/max - y2/max = (y1 - y2)/max
         * b = x2/max - x1/max = (x2 - x1)/max
         * c = (x1/max * y2/max) - (x2/max * y1/max) = (x1*y2 - x2*y1)/max²
         * 
         * Para que los 3 denominadores sean iguales, y por tanto la ecuación
         * normalizada sea proporcional a la original, debemos multiplicar c por max:
         */
        c *= max;

        // Construimos la salida y la guardamos en el StringBuilder:
        String salida = String.format(Locale.US, "%.9f %.9f %.9f%n", a, b, c);
        output.append(salida);
    }

    public static double max(double... args) {
        double max = 0;

        for (double v : args) {
            v = Math.abs(v);
            if (v > max) {
                max = v;
            }
        }

        return max;
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