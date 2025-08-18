import java.util.*;
import java.io.*;

public class P7F {
    static File f;
    static Scanner sc;
    static int q;

    public static void main(String[] args) throws IOException {
        f = new File("inputs/07/F.txt");
        sc = new Scanner(f);
        q = sc.nextInt();
        // sc.nextLine();

        solve();
    }

    public static void solve() {
        Queue<Tarea> tareas = new PriorityQueue<>();
        List<Long> realizadas = new ArrayList<>();

        for (int i = 0; i < q; i++) {
            switch (sc.next()) {
                case "+":
                    tareas.offer(new Tarea(sc.nextLong(), sc.nextLong()));
                    sc.nextLine();
                    break;
                case "-":
                    long id = sc.nextLong();
                    realizadas.add(id);
                    sc.nextLine();
                    System.out.println(id);
                    break;
                case "!":
                    Tarea t = tareas.poll();
                    while (t != null && realizadas.contains(t.id)) {
                        t = tareas.poll();
                    }
                    if (t != null)
                        System.out.println(t.id);
                    break;
            }
        }
    }
}

class Tarea implements Comparable<Tarea> {
    long pr, id;

    public Tarea(long pr, long id) {
        this.pr = pr;
        this.id = id;
    }

    @Override
    public int compareTo(Tarea t) {
        long dif = t.pr - this.pr;
        return (int) Math.signum(dif);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Tarea))
            return false;
        Tarea t = (Tarea) o;
        return t.id == this.id;
    }
}