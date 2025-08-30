import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class P11I {
    static Lector sc;
    static StringBuilder output;

    public static void main(String[] args) throws IOException {
        // Inicializamos entrada y salida:
        inicializar(11, 'I');

        // Resolvemos el problema:
        solve();
    }

    public static void solve() throws IOException {
        /*
         * Leemos los puntos de entrada y calculamos el de mínima Y (si hay 2 con la
         * misma Y, de esos 2 cogemos el que tenga menor X). Los guardaré en una
         * LinkedList para poder borrar fácilmente dicho punto después de encontrarlo:
         */
        int p = sc.nextInt(), min_ind = -1;

        if (p <= 2) {
            System.out.printf("0.0000");
            return;
        }

        Punto min_punto = new Punto(Double.MAX_VALUE, Double.MAX_VALUE);
        LinkedList<Punto> puntos = new LinkedList<>();
        for (int i = 0; i < p; i++) {
            Punto leido = new Punto(sc.nextDouble(), sc.nextDouble());
            puntos.add(leido);

            if ((leido.y < min_punto.y) || (Math.abs(min_punto.y - leido.y) < 1e-9 && leido.x < min_punto.x)) {
                min_punto = leido;
                min_ind = i;
            }
        }
        puntos.remove(min_ind);

        /*
         * Ordenamos la lista resultante en función del ángulo polar (ángulo antihorario
         * respecto del eje X entre 0° y 360°) que forma cada punto con 'min_punto'. Los
         * 2 de ángulo menor serán los siguientes en nuestra envolvente convexa.
         * 
         * Para hallar dichos ángulos podemos emplear un truco muy ingenioso: la
         * tangente. La tangente no es más la pendiente de la hipotenusa del triángulo
         * formado por el seno y el coseno de un ángulo. Pasa que dicha tangente va
         * tomando valores positivos y negativos de manera periódica, así que usaremos
         * la función 'Math.atan2()', que devuelve la arcotangente de un ángulo polar:
         */
        Punto aux = min_punto; // Variable efectivamente final para que no nos dé problemas meterla en la
                               // expresión lambda
        Collections.sort(puntos, (a, b) -> {
            Punto dif_a = a.menos(aux), dif_b = b.menos(aux);
            double angulo_A = Math.atan2(dif_a.y, dif_a.x), angulo_B = Math.atan2(dif_b.y, dif_b.x);
            if (Math.abs(angulo_A - angulo_B) > 1e-9)
                return angulo_A < angulo_B ? -1 : 1;
            else
                return dif_a.mod2() < dif_b.mod2() ? -1 : 1;
        });

        // Una vez ordenada la lista, guardamos los 2 primeros puntos en la pila donde
        // almacenaremos la envolvente:
        Stack<Punto> hull = new Stack<>();
        hull.push(min_punto);
        hull.push(puntos.poll());

        /*
         * Mientras queden puntos por analizar, los vamos guardando en 'sig' y
         * comprobamos que el vector que forman con el último punto de la envolvente
         * actual ('act') represente un giro antihorio respecto del vector formado por
         * 'ant' y 'act':
         */
        while (!puntos.isEmpty()) {
            Punto sig = puntos.poll();

            while (hull.size() >= 2) {
                Punto act = hull.pop(), ant = hull.peek();

                if (convexo(act.menos(ant), sig.menos(act))) {
                    hull.push(act);
                    break;
                }
            }

            hull.push(sig);
        }

        // Finalmente, empleamos el teorema del cordel para calcular el área:
        if (hull.size() < 3)
            System.out.printf("0.0000");
        else
            System.out.printf(Locale.US, "%.4f", cordel(hull));
    }

    static boolean convexo(Punto vector_A, Punto vector_B) {
        return vector_A.pvec(vector_B) > 0;
    }

    static double cordel(List<Punto> hull) {
        double suma = 0;
        for (int i = 0; i < hull.size() - 1; i++) {
            suma += (hull.get(i).x * hull.get(i + 1).y - hull.get(i).y * hull.get(i + 1).x);
        }
        suma += hull.get(hull.size() - 1).x * hull.get(0).y - hull.get(hull.size() - 1).y * hull.get(0).x;
        return 0.5 * Math.abs(suma);
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

    @SuppressWarnings("unused")
    private static class Punto {
        double x, y;

        Punto(double x, double y) {
            this.x = x;
            this.y = y;
        }

        Punto menos(Punto p) {
            return new Punto(this.x - p.x, this.y - p.y);
        }

        double pvec(Punto p) {
            return this.x * p.y - this.y * p.x;
        }

        double mod2() {
            return this.x * this.x + this.y * this.y;
        }
    }
}

