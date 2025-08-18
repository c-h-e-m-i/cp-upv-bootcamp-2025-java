import java.util.*;
import java.io.*;

public class P7H {
    static File f;
    static BufferedReader br;
    static Nodo cursor;
    static StringBuilder output;

    private static class ListaEnlazada {
        private Nodo ini, fin;

        ListaEnlazada() {
            ini = new Nodo(null, null);
            fin = new Nodo(ini, null);
            ini.sig = fin;
        }

        // Inserta un carácter después del señalado por el cursor y se coloca en él.
        void insertar(char letra) {
            Nodo nuevo = new Nodo(cursor, cursor.sig, letra);
            cursor.sig = cursor.sig.ant = nuevo;
            cursor = nuevo;
        }

        void moverIzq() {
            if (cursor != ini) {
                cursor = cursor.ant;
            }
        }

        void moverDer() {
            if (cursor.sig != fin) {
                cursor = cursor.sig;
            }
        }

        // Borra el carácter que va después del que señala el cursor.
        void borrar() {
            if (cursor.sig != fin) {
                cursor.sig.sig.ant = cursor;
                cursor.sig = cursor.sig.sig;
            }
        }

        boolean borrar(Nodo desde, Nodo hasta, boolean dir) {
            if (desde == hasta)
                return false;
            if (dir) {
                desde.sig = hasta.sig;
                hasta.sig.ant = desde;
                cursor = desde;
            } else {
                hasta.sig = desde.sig;
                desde.sig.ant = hasta;
                cursor = hasta;
            }
            return true;
        }

        void printear() {
            Nodo aux = ini;
            if (cursor == ini) {
                output.append('|');
            }
            while (aux.sig != fin) {
                aux = aux.sig;
                output.append((char) aux.letra);
                if (aux == cursor) {
                    output.append('|');
                }
            }
            output.append('\n');
        }

        Nodo getIni() {
            return ini;
        }

        Nodo getFin() {
            return fin;
        }
    }

    private static class Nodo {
        char letra;
        Nodo ant, sig;

        Nodo(Nodo ant, Nodo sig) {
            this.ant = ant;
            this.sig = sig;
        }

        Nodo(Nodo ant, Nodo sig, char letra) {
            this.ant = ant;
            this.sig = sig;
            this.letra = letra;
        }
    }

    public static void main(String[] args) throws IOException {
        f = new File("inputs/07/H.txt");
        output = new StringBuilder();
        br = new BufferedReader(new FileReader(f));
        int q = Integer.parseInt(br.readLine());
        // sc.nextLine();

        solve(q);
        System.out.print(output);
    }

    public static void solve(int q) throws IOException {
        StringTokenizer st;
        ListaEnlazada doc = new ListaEnlazada();

        cursor = doc.getIni();
        Nodo desde = cursor, hasta = cursor;
        boolean select = false, dir = true;

        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            // doc.printear();
            // output.append(" " + (572 - q) + '\n');
            switch (cmd.charAt(0)) {
                case 'I':
                    if (select) {
                        doc.borrar(desde, hasta, dir);
                        select = false;
                    }
                    doc.insertar(st.nextToken().charAt(0));
                    break;
                case 'M':
                    if (cmd.length() == 9) {
                        if (cursor == desde) {
                            dir = false;
                        }

                        doc.moverIzq();

                        if (select) {
                            hasta = cursor;
                        }
                    } else {
                        if (cursor == desde) {
                            dir = true;
                        }

                        doc.moverDer();

                        if (select) {
                            hasta = cursor;
                        }
                    }
                    break;
                case 'D':
                    boolean borrado = false;
                    if (select) {
                        borrado = doc.borrar(desde, hasta, dir);
                        select = false;
                    }
                    if (!borrado) {
                        doc.borrar();
                    }
                    break;
                case 'P':
                    doc.printear();
                    break;
                case 'S':
                    select = !select;

                    if (select) {
                        desde = cursor;
                        hasta = cursor;
                    }
                    break;
                case 'B':
                    cursor = doc.getIni();

                    if (select) {
                        hasta = cursor;
                        dir = false;
                    }
                    break;
                case 'E':
                    cursor = doc.getFin().ant;

                    if (select) {
                        hasta = cursor;
                        dir = true;
                    }
                    break;
            }
        }
    }
}