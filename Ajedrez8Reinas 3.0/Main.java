
public class Main{

    private static int n_sol = 0;

    public static void print(int[] tablero) {
        System.out.println("\nsol " + n_sol + ":\n\t+---+---+---+---+---+---+---+---+");
        for (int i=0; i<8; i++) {
            System.out.print("\t|");
            for (int j=0; j<8; j++)
                if (j == tablero[i]) System.out.print(" # |");
                else System.out.print("   |");
            System.out.println("\n\t+---+---+---+---+---+---+---+---+");
        }
    }

    // Probar si se puede colocar la reina en la columna n, fila j
    public static boolean check(int[] tablero, int n, int j){
        for (int i=0; i<n; i++)         // probar cada una de las otras reinas
            if (tablero[i] != -1 && (   // celdas con ficha
                tablero[i] == j ||      // misma fila
                tablero[i]-j == n-i ||  // misma diagonal ascendente
                j-tablero[i] == n-i     // misma diagonal descendente
                )) return false;
        return true;
    }

    // Intentar colocar reinas en las columnas >= n
    public static void solve(int[] tablero, int n) {
        if (n == 8) { // se ha encontrado una solucion
            n_sol++;
            print(tablero);
        }
        else {
            for (int i=0; i<8; i++) {   // probar a colocar cada reina
                if (check(tablero, n, i)) {
                    tablero[n] = i;
                    solve(tablero, n+1);
                }
            }
        }
    }

    public static void main(String[] args) {
        /*
            tablero[i] -> reina en la fila i, columna tablero[i]
            reina en casilla (i, j) <==> tablero[i] == j
            tablero[i] == -1 <==> no hay reina
        */
        int[] tablero = new int[] {-1, -1, -1, -1, -1, -1, -1, -1};
        long t0 = System.currentTimeMillis();
        solve(tablero, 0);
        System.out.println("\nFinalizado en " + (System.currentTimeMillis() - t0) + "ms");
    }
}
