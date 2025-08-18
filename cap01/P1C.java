import java.io.*;

public class P1C {
    public static void main(String[] args) throws IOException {
        // Para leer de archivo:
        File f = new File("inputs/01/C.txt");
        BufferedReader br = new BufferedReader(new FileReader(f));

        // Para leer de entrada estándar:
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String dni = br.readLine();
        System.out.print(dni.charAt(dni.length() - 1));
        br.close();
    }
}
