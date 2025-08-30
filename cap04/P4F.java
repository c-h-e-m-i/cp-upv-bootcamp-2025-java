import java.io.*;
import java.util.*;

public class P4F {
    static Lector sc;
    static StringBuilder output;

    public static void main(String[] args) throws IOException {
        // Inicializamos entrada y salida:
        inicializar(4, 'F');

        // Resolvemos el problema:
        solve();

        // Imprimimos la salida y cerramos el flujo de entrada:
        System.out.print(output);
        sc.cerrar();
    }

    public static void solve() throws IOException {
        // Leemos la entrada:
        int lado = sc.nextInt();
        sc.nextLine();
        boolean vacia = true, tostada[][] = new boolean[lado][lado];
        for (int i = 0; i < lado; i++) {
            String linea = sc.nextLine();
            for (int j = 0; j < lado; j++) {
                boolean mermelada = linea.charAt(j) == '#';
                tostada[i][j] = mermelada;
                if (mermelada) {
                    vacia = false;
                }
            }
        }

        // Si la tostada no lleva mermelada, lo mostramos por salida y acabamos:
        if (vacia) {
            output.append("NO LLEVABA MERMELADA");
            return;
        }

        // Comparamos con la tostada nueva:
        sc.nextLine();
        for (int i = 0; i < lado; i++) {
            String linea = sc.nextLine();
            for (int j = 0; j < lado; j++) {
                boolean mermelada = linea.charAt(j) == '#';
                if (mermelada != tostada[i][j]) {
                    output.append("TRAGEDIA");
                    return;
                }
            }
        }

        // Si salimos del bucle, significa que la tostada está igual:
        output.append("HA HABIDO SUERTE");
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