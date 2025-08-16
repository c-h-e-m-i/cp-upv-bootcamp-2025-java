import java.util.*;
import java.io.*;

public class P7E {
    static File f;
    static Scanner sc;

    public static void main(String[] args) throws IOException {
        f = new File("inputs/7/E.txt");
        sc = new Scanner(f);
        // int n = sc.nextInt();
        // sc.nextLine();

        solve();
    }

    public static void solve() {
        String s = sc.nextLine();
        int k = sc.nextInt();
        Set<String> subcadenas = new HashSet<>();

        for (int i = 0; i <= s.length() - k; i++) {
            subcadenas.add(s.substring(i, i + k));
        }

        System.out.println(subcadenas.size());
    }
}