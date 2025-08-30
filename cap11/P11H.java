import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class P11H {
    static Lector sc;
    static StringBuilder output;
    static final int SIN = (int) Math.pow(10, 8);

    public static void main(String[] args) throws IOException {
        // Inicializamos entrada y salida:
        inicializar();

        // Resolvemos el problema:
        solve();
    }

    public static void solve() throws IOException {
        // Leemos las coordenadas de Arenópolis:
        Punto aren = new Punto(sc.nextInt(), sc.nextInt());

        /*
         * La idea de este problema es similar a la del anterior: los puntos donde la
         * dirección hacia la singularidad valga IND estarán en una frontera ("rayo")
         * entre 2 direcciones. El punto de corte entre 2 rayos será la singularidad,
         * así que realizaremos búsqueda binaria sobre uno de los ejes cardinales para
         * dar con ellos.
         */
        buscar(aren);
    }

    static void preguntar(Punto p) {
        System.out.printf("? %d %d%n", p.x, p.y);
        System.out.flush();
    }

    static void confirmar(Punto p) {
        System.out.printf("! %d %d%n", p.x, p.y);
        System.out.flush();
    }

    static void buscar(Punto aren) throws IOException {
        preguntar(aren);

        Punto rayo_A = null, rayo_B = null;
        boolean norte_u_oeste = false;

        /*
         * Si la dirección es norte o sur, exploro el eje X; si es este u oeste, el eje
         * Y, y si estoy encima de un rayo, me muevo una posición a la derecha y vuelvo
         * a preguntar:
         */
        switch (sc.next().charAt(0)) {
            case 'N':
                norte_u_oeste = true;
            case 'S':
                // ini = izquierda, fin = derecha
                rayo_A = busquedaA(new Punto(-SIN * 3, aren.y), new Punto(aren.x - 1, aren.y), true); // Izquierdo
                rayo_B = busquedaB(new Punto(aren.x + 1, aren.y), new Punto(SIN * 3, aren.y), true); // Derecho
                break;
            case 'O':
                norte_u_oeste = true;
            case 'E':
                // ini = abajo, fin = arriba
                rayo_A = busquedaA(new Punto(aren.x, aren.y + 1), new Punto(aren.x, SIN * 3), false); // Arriba
                rayo_B = busquedaB(new Punto(aren.x, -SIN * 3), new Punto(aren.x, aren.y - 1), false); // Abajo
                break;
            case 'I':
                // Si ya estoy colocado sobre un rayo, me muevo hacia un lado y vuelvo a buscar;
                aren.x += 1;
                buscar(aren);
                return;
        }

        // Hallo el punto de corte entre los 2 rayos, que será la singularidad:
        Punto corte = corte(rayo_A, rayo_B, norte_u_oeste);
        confirmar(corte);
    }

    static Punto busquedaA(Punto ini, Punto fin, boolean eje) throws IOException {
        Punto medio = ini.mas(fin).entre(2);
        preguntar(medio);
        char dir = sc.next().charAt(0);

        if (dir == 'I') {
            return medio;
        }

        // Hago búsqueda binaria según la dirección donde se encuentre la singularidad:
        if (eje) { // Si eje == true --> eje X, si eje == false --> eje Y
            if (dir == 'E')
                return busquedaA(new Punto(medio.x + 1, medio.y), fin, eje);
            else
                return busquedaA(ini, new Punto(medio.x - 1, medio.y), eje);
        } else {
            if (dir == 'S')
                return busquedaA(ini, new Punto(medio.x, medio.y - 1), eje);
            else
                return busquedaA(new Punto(medio.x, medio.y + 1), fin, eje);
        }
    }

    static Punto busquedaB(Punto ini, Punto fin, boolean eje) throws IOException {
        Punto medio = ini.mas(fin).entre(2);
        preguntar(medio);
        char dir = sc.next().charAt(0);

        if (dir == 'I') {
            return medio;
        }

        // Hago búsqueda binaria según la dirección donde se encuentre la singularidad:
        if (eje) { // Si eje == true --> eje X, si eje == false --> eje Y
            if (dir == 'O')
                return busquedaB(ini, new Punto(medio.x - 1, medio.y), eje);
            else
                return busquedaB(new Punto(medio.x + 1, medio.y), fin, eje);
        } else {
            if (dir == 'N')
                return busquedaB(new Punto(medio.x, medio.y + 1), fin, eje);
            else
                return busquedaB(ini, new Punto(medio.x, medio.y - 1), eje);
        }
    }

    static Punto corte(Punto rayo_A, Punto rayo_B, boolean norte_u_oeste) {
        /*
         * Nuestros rayos son rectas definidas por un punto y un vector director. Si
         * hemos explorado el eje Y estando al este de la singularidad, el rayo de
         * arriba tendrá de pendiente -1 y el otro 1, y viceversa estando al oeste. Lo
         * mismo con el eje X: si estábamos al norte de al singularidad, el rayo
         * izquierdo tendrá de pendiente -1 y el otro 1, y viceversa si estábamos al
         * sur. Sabiendo esto, para encontrar el punto de corte entre 2 rayos tendremos
         * que igualar sus ecuaciones:
         * 
         * rayo_A + (1, -1) * a = rayo_B + (1, 1) * b {
         * 
         * rayo_A.x + a = rayo_B.x + b
         * rayo_A.y - a = rayo_B.y + b
         * 
         * }
         * 
         * Sumamos ambas igualdades...
         * 
         * rayo_A.x + rayo_A.y = rayo_B.x + rayo_B.y + 2b
         * 
         * Despejamos la b...
         * 
         * b = [(rayo_A.x + rayo_A.y) - (rayo_B.x + rayo_B.y)] / 2
         * 
         * Para encontrar el punto, sustituimos la b en:
         * 
         * rayo_B + (1, 1) * b = rayo_B + (b, b)
         */
        if (norte_u_oeste) {
            Punto aux = rayo_A;
            rayo_A = rayo_B;
            rayo_B = aux;
        }

        int b = ((rayo_A.x + rayo_A.y) - (rayo_B.x + rayo_B.y)) / 2;
        return new Punto(rayo_B.x + b, rayo_B.y + b);
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
        int x, y;

        Punto(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Punto menos(Punto p) {
            return new Punto(this.x - p.x, this.y - p.y);
        }

        Punto mas(Punto p) {
            return new Punto(this.x + p.x, this.y + p.y);
        }

        Punto entre(int d) {
            return new Punto(this.x / d, this.y / d);
        }

        double mod() {
            return Math.sqrt(this.x * this.x + this.y * this.y);
        }
    }

}
