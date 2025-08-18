import java.io.*;
import java.util.*;

public class P1G {
    public static void main(String[] args) throws IOException {
        // Para leer de archivo:
        File f = new File("inputs/01/G.txt");
        BufferedReader br = new BufferedReader(new FileReader(f));

        // Para leer de entrada estándar:
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Línea 1:
        StringTokenizer st = new StringTokenizer(br.readLine());
        double decimal = Double.parseDouble(st.nextToken());

        // Línea 2:
        String linea2 = br.readLine();

        // Línea 3:
        st = new StringTokenizer(br.readLine());
        String palabra1 = st.nextToken();

        // Imprimimos salida y cerramos flujo de entrada (ojo con el Locale: aunque el
        // juez permite no usarlo, en los ejemplos la salida venía con punto decimal):
        System.out.printf(Locale.US, "%s: %.3f%n%s", linea2, decimal, palabra1);
        br.close();
    }
}
