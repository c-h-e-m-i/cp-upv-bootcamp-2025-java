import java.util.*;
import java.io.*;

public class P5D {
    public static void main(String[] args) throws IOException {
        File f = new File("inputs/05/D.txt");
        Scanner sc = new Scanner(f);

        solve(sc);
    }

    public static void solve(Scanner sc) {
        int N = sc.nextInt();
        Puesto[] q = new Puesto[N];
        for (int i = 0; i < N; i++) {
            q[i] = new Puesto(sc.nextInt(), sc.nextInt());
        }

        Comparator<Puesto> comparador = new PuestoSort().reversed();
        Arrays.sort(q, comparador);
        for (int i = 0; i < N; i++) {
            System.out.println(q[i]);
        }
    }
}

class Puesto {
    public int probs, penal;

    public Puesto(int probs, int penal) {
        this.probs = probs;
        this.penal = penal;
    }

    @Override
    public String toString() {
        return probs + " " + penal;
    }
}

class PuestoSort implements Comparator<Puesto> {
    public int compare(Puesto p1, Puesto p2) {
        if (p1.probs < p2.probs)
            return -1;
        else if (p1.probs > p2.probs)
            return 1;
        else
            return p2.penal - p1.penal; // Si p1 es menor, devolverá un número positivo;
    }
}
