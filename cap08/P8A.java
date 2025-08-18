import java.util.*;
import java.io.*;

public class P8A {
    static Lector lector;
    static StringBuilder output;
    static int n, acusadoDe[];

    public static void main(String[] args) throws IOException {
        File f = new File("inputs/08/A.txt");
        lector = new Lector();
        lector.leerArchivo(f);
        output = new StringBuilder();
        n = lector.nextInt();

        // Creamos la lista de acusados y la rellenamos (el primer alumno es el 1, así
        // que añadimos un hueco adicional a la lista):
        acusadoDe = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            acusadoDe[i] = lector.nextInt();
        }

        // Para cada alumno i, calculamos el culpable en caso de que la profesora
        // empezara acusando a i:
        for (int i = 1; i <= n; i++) {
            solve(i);
        }
        System.out.print(output);
    }

    public static void solve(int alumno) {
        // Nos guardamos en una lista de booleanos qué alumnos ya han sido acusados:
        boolean[] acusados = new boolean[n + 1];

        // Mientras el alumno actual no haya sido acusado, lo marcamos como acusado y
        // pasamos al que acusa él:
        while (!acusados[alumno]) {
            acusados[alumno] = true;
            alumno = acusadoDe[alumno];
        }

        // Si salimos del while es porque el alumno actual ya ha sido acusado más de una
        // vez, así que le cargamos el muerto:
        output.append(alumno + " ");
    }

    // -------- CLASES AUXILIARES --------
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
                    resto.append(st.nextToken() + " ");
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