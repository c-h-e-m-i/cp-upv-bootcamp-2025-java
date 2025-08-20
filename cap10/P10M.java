import java.io.*;
import java.util.*;

public class P10M {
    static Lector sc;
    static StringBuilder output;

    public static void main(String[] args) throws IOException {
        // Inicializamos entrada y salida:
        inicializar(10, 'M');

        // Resolvemos el problema:
        int t = sc.nextInt();
        while (t-- > 0)
            solve();

        // Mostramos la solución:
        System.out.print(output);
    }

    public static void solve() throws IOException {
        // Leemos la entrada:
        String s = sc.nextLine();
        StringBuilder sb = new StringBuilder("@#");
        int l = s.length();
        ArrayList<Integer> corresp = new ArrayList<>();

        /*
         * La manera más eficiente de resolver este problema es empleando el algoritmo
         * de Manacher, con una complejidad de O(n). Se basa en lo siguiente:
         * 
         * - Intercalamos un separador (por ejemplo, '#') entre cada símbolo, y ponemos
         * otros 2 separadores distintos (por ejemplo, '@' y '$') al principio y al
         * final (ahora verás el porqué de esto último).
         * 
         * - "Expandimos" cada símbolo que no sean los separadores de los extremos,
         * comprobando si los símbolos a sus lados coinciden. Si lo hacen, comparamos
         * los de los lados siguientes, y así sucesivamente hasta que algún par sea
         * distinto. Cada vez que hagamos una expansión añadimos 1 unidad al llamado
         * "radio" del símbolo, que, como su propio nombre indica, representa el radio
         * del palíndromo con centro en el símbolo actual. Se usan '@' y '$'
         * precisamente porque nos aseguran que el radio se cortará antes de salirse del
         * tamaño del array. Los radios los recogeremos en el array 'r'.
         * 
         * - A medida que calculamos estos radios, nos guardamos el extremo más derecho
         * ('der') al que haya llegado alguno de ellos, junto con su centro. De ese
         * modo, podemos saber si el símbolo sobre el que estamos iterando se encuentra
         * ya dentro de un palíndromo o no, lo cual nos ahorrará muchas operaciones por
         * lo que leerás ahora.
         * 
         * - Si efectivamente nuestro símbolo está dentro de algún palíndromo (es decir,
         * 'i <= der', aunque en la práctica usaremos 'i < der', pues que 'i' sea igual
         * a 'der' no nos aporta información adicional porque no sabemos qué hay
         * inmediatamente a su derecha), significa que dentro de ese palíndromo habrá 1
         * símbolo "espejo" cuyos lados coincidirán con el del símbolo actual. Esto
         * quiere decir que el símbolo actual tendrá como radio el de su símbolo espejo
         * (a no ser que este radio se salga del palíndromo, en cuyo caso tendrá de
         * radio lo que le queda para alcanzar el extremo del palíndromo, como mínimo.
         * Este radio podrá aumentar después si vemos que fuera del palíndromo los lados
         * siguen coincidiendo).
         */
        for (int i = 0; i < l; i++) {
            char c = s.charAt(i);

            /*
             * Compactaremos la cadena original quitando los espacios y pasándola a
             * minúsculas.
             * Para cada símbolo editado, tendremos una correspondencia en 'corresp' de su
             * posición en la cadena original:
             */
            if (c != ' ') {
                sb.append(Character.toLowerCase(c)).append('#');
                corresp.add(i);
            }
        }
        sb.append('$');

        char[] edit = sb.toString().toCharArray();
        l = edit.length;
        int max = 0, r[] = new int[l], cen = 0, der = 0;
        String pal = "";
        for (int i = 1; i < l - 1; i++) {
            int radio = 0;
            if (i < der) {
                int espejo = 2 * cen - i;
                // Por ejemplo, en el caso de #a#b#c#b#a# el espejo de la segunda 'b' es la
                // primera, que tiene un radio de solo 1 frente al de 'r - i', que es 3.
                radio = Math.min(r[espejo], der - i);
            }

            while (edit[i - radio] == edit[i + radio]) {
                radio++;
            }
            r[i] = radio - 1;

            if (r[i] == 0)
                continue;

            if (i + r[i] > der) {
                der = i + r[i];
                cen = i;
            }

            if (r[i] >= max) {
                String sec = secuencia(s, corresp, i, r[i]);

                if (r[i] > max || sec.compareTo(pal) < 0) {
                    max = r[i];
                    pal = sec;
                }
            }
        }

        /*
         * El trim() realmente no haría falta porque, al haber trabajado sobre la cadena
         * sin espacios, nos hemos asegurado de que el primer y último carácter nunca
         * van a ser espacios, pero lo ponemos por si acaso:
         */
        output.append(pal.trim()).append('\n');
    }

    public static String secuencia(String original, ArrayList<Integer> corresp, int centro, int radio) {
        /*
         * Los extremos del palíndromo en la cadena editada ('edit') siempre están 1
         * posición a la izquierda/derecha de la frontera del radio. Sus equivalentes
         * sin '#' se obtienen restándoles 2 (porque están desplazados 2
         * posiciones a causa del "@#" del principio)...
         * 
         * -entonces a la izquierda tendremos
         * "centro - radio + 1 - 2" = "centro - radio - 1"
         * y a la derecha tendremos
         * "centro - radio - 1 - 2" = "centro - radio - 3"-
         * 
         * ... y haciendo la división truncada entre 2:
         */
        int ini = corresp.get((centro - radio - 1) / 2), fin = corresp.get((centro + radio - 3) / 2);

        return original.substring(ini, fin + 1);
    }

    // -------- CLASES Y MÉTODOS AUXILIARES --------
    public static void inicializar(int cap, char prob) throws IOException {
        File f = new File(String.format("inputs/%02d/%C.txt", cap, prob));
        sc = new Lector();
        sc.leerArchivo(f);
        output = new StringBuilder();
    }

    public static void inicializar() {
        sc = new Lector();
        sc.leerStd();
        output = new StringBuilder();
    }

    @SuppressWarnings("unused")
    private static class Lector {

        BufferedReader br;
        StringTokenizer st;

        Lector() {
            br = null;
            st = new StringTokenizer("");
        }

        void leerArchivo(File f) throws IOException {
            br = new BufferedReader(new FileReader(f));
        }

        void leerStd() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            if (!st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
            }
            return st.nextToken();
        }

        String nextLine() throws IOException {
            if (!st.hasMoreTokens()) {
                return br.readLine();
            } else {
                StringBuilder resto = new StringBuilder();
                while (st.hasMoreTokens()) {
                    resto.append(st.nextToken()).append(" ");
                }
                return resto.toString().trim();
            }
        }

        byte nextByte() throws IOException {
            return Byte.parseByte(next());
        }

        short nextShort() throws IOException {
            return Short.parseShort(next());
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        float nextFloat() throws IOException {
            return Float.parseFloat(next());
        }

        double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }

        boolean nextBoolean() throws IOException {
            return Boolean.parseBoolean(next());
        }
    }
}
