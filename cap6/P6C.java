import java.util.*;
import java.io.*;

public class P6C {
    public static void main(String[] args) throws IOException {
        File f = new File("inputs/6/C.txt");
        Scanner sc = new Scanner(f);
        int n = sc.nextInt();

        System.out.println(triang(n));
        sc.close();
    }

    public static int triang(int n) {
        if (n == 1)
            return 1;
        else
            return n + triang(n - 1);
    }
}
