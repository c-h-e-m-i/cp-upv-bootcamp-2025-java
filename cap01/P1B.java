import java.io.*;

public class P1B {
    public static void main(String[] args) throws IOException {
        // Para leer de archivo:
        File f = new File("inputs/01/B.txt");
        BufferedReader br = new BufferedReader(new FileReader(f));

        // Para leer de entrada estándar:
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print(br.readLine());
        br.close();
    }
}
