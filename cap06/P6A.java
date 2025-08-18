import java.util.*;
import java.io.*;

public class P6A {
    public static void main(String[] args) throws IOException {
        File f = new File("inputs/06/A.txt");
        Scanner sc = new Scanner(f);
        int n = sc.nextInt();

        solve(n);
        sc.close();
    }

    public static void solve(int n) {
        if (n == 1) {
            System.out.println(1);
        } else if (n > 1) {
            System.out.println(n);
            solve(n - 1);
        }
    }
}
