import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class P1F {
    public static void main(String[] args) throws IOException {
        // Para leer de archivo:
        File f = new File("inputs/01/F.txt");
        BufferedReader br = new BufferedReader(new FileReader(f));

        // Para leer de entrada estándar:
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        st.nextToken();
        BigInteger entero = new BigInteger(st.nextToken()); // Podríamos imprimir el String directamente, pero la idea
                                                            // del problema es manejar BigIntegers

        // Imprimimos la salida y cerramos el flujo de entrada:
        System.out.print(entero);
        br.close();
    }
}
