import java.io.*;
import java.util.*;

public class P10C {
    static Lector sc;
    static StringBuilder output;

    public static void main(String[] args) throws IOException {
        // Inicializamos entrada y salida:
        inicializar(10, 'C');

        // Leemos el n.º de casos de prueba y los ejecutamos:
        int t = sc.nextInt();
        while (t-- > 0)
            levenshtein();

        System.out.print(output);
    }

    public static void levenshtein() throws IOException {
        // Leemos ambas palabras y sus longitudes:
        String a = sc.next(), b = sc.next();
        int lA = a.length(), lB = b.length();

        // Como en un mismo momento solo necesitamos 2 filas de la matriz, iremos
        // "desplazando la ventana" de la tabla de Levenshtein sobre esas 2 filas:
        int[][] matriz = new int[2][lB + 1];

        // Rellenamos la primera fila asumiendo que hacemos solo borrados:
        for (int j = 1; j <= lB; j++)
            matriz[0][j] = j;

        /*
         * En cada iteración, para no tener que hacer copias de arrays, iremos
         * alternando la "fila activa" -es decir, la fila que se está editando-,
         * mediante el uso de 2 variables: 'edit' -la fila a editar-, que irá
         * alternando entre 0 y 1; y 'cons' -la fila a consultar-, que será su
         * valor opuesto:
         */
        for (int i = 1; i <= lA; i++) {
            int edit = i % 2, cons = 1 - edit;
            matriz[edit][0] = i;
            for (int j = 1; j <= lB; j++)
                matriz[edit][j] = a.charAt(i - 1) == b.charAt(j - 1) ? matriz[cons][j - 1]
                        : Math.min(Math.min(matriz[edit][j - 1], matriz[cons][j]), matriz[cons][j - 1]) + 1;
        }

        output.append(matriz[lA % 2][lB]).append("\n");
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