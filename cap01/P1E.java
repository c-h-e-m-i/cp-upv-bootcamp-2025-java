import java.io.*;

public class P1E {
    public static void main(String[] args) throws IOException {
        // Para leer de archivo:
        File f = new File("inputs/01/E.txt");
        BufferedReader br = new BufferedReader(new FileReader(f));

        // Para leer de entrada estándar:
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String pergamino = br.readLine();
        System.out.print(pergamino.toLowerCase() + "\n" + pergamino.toUpperCase());
        br.close();
    }
}
