import java.util.*;
import java.io.*;

@SuppressWarnings("unchecked")
public class P7B {
    static File f;
    static Scanner sc;
    static Queue<Character>[] colas;

    public static void main(String[] args) throws IOException {
        f = new File("inputs/07/B.txt");
        sc = new Scanner(f);
        colas = (Queue<Character>[]) new LinkedList[3];
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < 3; i++) {
            colas[i] = new LinkedList<>();
            for (int j = 0; j < n; j++)
                colas[i].offer(sc.next().charAt(0));
        }

        solve();
    }

    public static void solve() {
        while (sc.hasNext()) {
            switch (sc.next()) {
                case "MOVER":
                    int desde = Integer.parseInt(sc.next()), hasta = Integer.parseInt(sc.next());
                    if (!colas[desde].isEmpty())
                        colas[hasta].offer(colas[desde].poll());
                    break;
                case "AGREGAR":
                    char persona = sc.next().charAt(0);
                    int destino = Integer.parseInt(sc.next());
                    colas[destino].offer(persona);
                    break;
                case "ATENDER":
                    int atendida = Integer.parseInt(sc.next());
                    if (!colas[atendida].isEmpty())
                        colas[atendida].poll();
                    break;
            }
        }

        for (LinkedList<Character> c : (LinkedList<Character>[]) colas) {
            if (c.isEmpty())
                System.out.println("NO HAY NADIE");
            else {
                for (char persona : c)
                    System.out.print(persona + " ");
                System.out.println();
            }
        }
    }
}
