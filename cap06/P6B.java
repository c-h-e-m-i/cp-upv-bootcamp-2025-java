import java.util.*;
import java.io.*;

public class P6B {
    public static void main(String[] args) throws IOException {
        File f = new File("inputs/06/B.txt");
        Scanner sc = new Scanner(f);
        int n = sc.nextInt();

        System.out.println(fib(n));
        sc.close();
    }

    public static int fib(int n) {
        if (n == 1 || n == 2)
            return 1;
        else
            return fib(n - 1) + fib(n - 2);
    }
}
