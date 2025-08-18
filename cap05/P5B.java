import java.util.*;
import java.io.*;

public class P5B {
    public static void main(String[] args) throws IOException {
        File f = new File("inputs/05/B.txt");
        Scanner sc = new Scanner(f);

        solve(sc);
    }

    public static void solve(Scanner sc) {
        int N = sc.nextInt(), A[] = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }
        quicksort(A, 0, A.length - 1);

        int Q = sc.nextInt();
        for (int i = 0; i < Q; i++)
            System.out.println(busqueda(A, 0, A.length - 1, sc.nextInt()) ? "YES" : "NO");
    }

    public static void quicksort(int[] arr, int left, int right) {
        int medio = (left + right) / 2, a = arr[left], b = arr[medio], c = arr[right],
                pivote = (a > b ^ a > c) ? left : (b > a ^ b > c) ? medio : right; // Operador XOR

        // Intercambiamos el pivote escogido con el primer elemento, de modo que sea más
        // sencillo manipular el array:
        intercambio(arr, left, pivote);

        /*
         * Recorro el array desde ambos lados:
         * - Si ambos elementos son menores o iguales que el pivote, avanzo en el lado
         * izquierdo <-- ELEMENTO DERECHO MAL COLOCADO. AVANZO EN EL LADO IZQUIERDO PARA
         * BUSCARLE UN SUSTITUTO.
         * - Si ambos son mayores, avanzo en el lado derecho. <-- ELEMENTO IZQUIERDO MAL
         * COLOCADO. AVANZO EN EL LADO DERECHO PARA BUSCARLE UN SUSTITUTO.
         * - Si el izquierdo es menor-igual y el derecho es mayor, avanzo en ambos. <--
         * AMBOS BIEN COLOCADOS. AVANZO EN AMBOS PARA BUSCAR UNA NUEVA PAREJA.
         * - Si el izquierdo es mayor y el derecho es menor, los intercambio y avanzo
         * ambos punteros. <-- AMBOS MAL COLOCADOS. LOS INTERCAMBIO Y LUEGO AVANZO EN
         * AMBOS PARA BUSCAR OTRA PAREJA.
         * De este modo, todos los elementos mayores que el pivote se quedan a la
         * derecha y los menores a la izquierda.
         */
        int indIzq = left + 1, indDer = right;
        while (indIzq < indDer) {
            // Si el izquierdo está bien colocado...
            if (arr[indIzq] <= arr[left]) {
                // Si el derecho está mal colocado, avanzo en el izquierdo...
                if (arr[indDer] <= arr[left])
                    indIzq++;
                // Si el derecho está bien colocado, avanzo en ambos para buscar una nueva
                // pareja...
                else {
                    indIzq++;
                    indDer--;
                }
            }
            // Si el izquierdo está mal colocado...
            else {
                // Si el derecho está mal colocado, he encontrado una pareja. Los intercambio y
                // avanzo en ambos para buscar otra pareja...
                if (arr[indDer] <= arr[left]) {
                    intercambio(arr, indIzq, indDer);
                    indIzq++;
                }
                // Si el derecho está bien colocado, avanzo en el derecho (esta parte es común
                // con el if de antes, por eso solo he puesto indDer-- una vez)...
                indDer--;
            }
        }

        // Una vez separado el array en la componente menor y la mayor, compruebo si el
        // elemento del centro es mayor o menor que el pivote para saber dónde hacer el
        // corte:
        pivote = indIzq - (arr[indIzq] <= arr[left] ? 0 : 1);
        // Intercambio el punto de corte con el pivote para poner el pivote entre ambas
        // componentes:
        intercambio(arr, left, pivote);

        // Llamamos recursivamente a quicksort para los subarrays que queden a la
        // izquierda y a la derecha del pivote:
        if (pivote > left + 1)
            quicksort(arr, left, pivote - 1);
        if (pivote < right - 1)
            quicksort(arr, pivote + 1, right);
    }

    public static void intercambio(int[] arr, int a, int b) {
        int aux = arr[a];
        arr[a] = arr[b];
        arr[b] = aux;
    }

    public static boolean busqueda(int[] arr, int left, int right, int libro) {
        if (left > right)
            return false;

        int medio = (left + right) / 2;

        if (libro == arr[medio])
            return true;
        else if (libro < arr[medio])
            return busqueda(arr, left, medio - 1, libro);
        else
            return busqueda(arr, medio + 1, right, libro);
    }
}