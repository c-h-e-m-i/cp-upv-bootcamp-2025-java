import java.util.*;
import java.io.*;

public class P5C {
    public static void main(String[] args) throws IOException {
        File f = new File("inputs/05/C.txt");
        Scanner sc = new Scanner(f);

        solve(sc);
    }

    public static void solve(Scanner sc) {
        int n = sc.nextInt();
        Pair[] q = new Pair[n];
        for (int i = 0; i < n; i++) {
            q[i] = new Pair(sc.nextInt(), sc.next());
        }

        Comparator<Pair> comparador = new PairSort();
        Arrays.sort(q, comparador);
        for (int i = 0; i < n; i++) {
            System.out.println(q[i]);
        }
    }
}

class Pair {
    public int calidad;
    public String etiqueta;

    public Pair(int calidad, String etiqueta) {
        this.calidad = calidad;
        this.etiqueta = etiqueta;
    }

    @Override
    public String toString() {
        return calidad + " " + etiqueta;
    }
}

class PairSort implements Comparator<Pair> {
    public int compare(Pair p1, Pair p2) {
        if (p1.calidad < p2.calidad)
            return -1;
        else if (p1.calidad > p2.calidad)
            return 1;
        else
            return p1.etiqueta.compareTo(p2.etiqueta);
    }
}
