import java.io.*;
import java.util.*;

public class P1D {
    public static void main(String[] args) throws IOException {
        // Para leer de archivo:
        File f = new File("inputs/01/D.txt");
        BufferedReader br = new BufferedReader(new FileReader(f));

        // Para leer de entrada estándar:
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String precios = br.readLine();
        StringBuilder output = new StringBuilder();
        StringTokenizer st = new StringTokenizer(precios);

        for (int i = 0; i < 3; i++) {
            double precio = Double.parseDouble(st.nextToken());

            switch (i) {
                case 0:
                    output.append((int) Math.ceil(precio) + "\n");
                    break;
                case 1:
                    output.append((int) Math.floor(precio) + "\n"); // Math.ceil y Math.floor siempre devuelven doubles
                    break;
                case 2:
                    output.append(Math.round(precio) + "\n"); // Math.round devuelve long para doubles e int para floats
                    break;
            }
        }

        output.append(st.nextToken() + "\n"); // Este debe mostrarle tal cual, incluso si sobran ceros al final

        // Mostramos la salida:
        System.out.print(output);
        br.close();
    }
}
