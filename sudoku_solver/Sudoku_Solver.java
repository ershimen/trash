import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
class Sudoku_Solver {

	static void solve(int[][] e){
		boolean aux = true;
		for (int i=0; i<9 && aux; i++)
			for (int j=0; j<9 && aux; j++)
				if (e[i][j]==0) {
					for (int k=1; k<10; k++)
						if (check(e, i, j, k)) {
							e[i][j] = k;
							solve(e);
							e[i][j] = 0;
						};
					aux = false;
				};
		if (aux) print(e);
	}

	static void print(int[][] e) {
		for (int i=0; i<9; i++) {
			if (i%3==0)	System.out.println("+---------+---------+---------+");
			for (int j=0; j<9; j++) {
				if (j%3==0) System.out.print("|");
				System.out.print(" "+e[i][j]+" ");
			}
			System.out.println("|");
		}
		System.out.println("+---------+---------+---------+");
	}

	static boolean check(int[][] e, int i, int j, int r) {
		boolean res = true;
		for (int c=0; c<9 && res; c++)
			if (e[c][j]==r)
				res = false;
		if (!res) return false;

		for (int c=0; c<9 && res; c++)
			if (e[i][c]==r)
				res = false;
		if (!res) return false;

		int subI, subJ;
		subI = (i/3)*3;
		subJ = (j/3)*3;
		for (int a=0; a<3 && res; a++)
			for (int b=0; b<3 && res; b++)
				if (e[subI+a][subJ+b]==r) res = false;

		return res;
	}

	public static void main(String[] args) {
		if (args.length==0) System.out.println("Se necesita un fichero.");
		else {
			try {
				Scanner sc = new Scanner(new File(args[0]));
				int[][] sudoku = new int[9][9];
				boolean aux = true;
				System.out.println("Cargando datos...");
				int i=0;
				while (i<81 && aux) {
					if (sc.hasNextInt()) {
						sudoku[i/9][i%9] = sc.nextInt();
						i++;
					}
					else aux = false;
				}
				sc.close();
				if (!aux) System.out.println("Formato incorrecto. Falla el caracter numero "+(i+1)+".");
				else {
					System.out.println("Resolviendo...");
					long start = System.currentTimeMillis();
					solve(sudoku);
					long total = System.currentTimeMillis()-start;
					System.out.println("Resuelto en "+total+" ms");
				}
			}
			catch (FileNotFoundException e) {
				System.out.println("Fichero incorrecto.");
			}
		}
		/*
		int[][] prueba1 = {{7, 0, 0, 0, 0, 0, 0, 0, 4},
						   {5, 0, 0, 8, 0, 0, 6, 9, 0},
						   {1, 0, 9, 0, 0, 0, 8, 0, 7},
						   {0, 0, 0, 0, 0, 0, 0, 5, 8},
						   {3, 0, 0, 0, 9, 0, 0, 0, 0},
						   {0, 0, 7, 0, 0, 0, 0, 4, 0},
						   {8, 7, 0, 0, 0, 3, 9, 0, 0},
						   {0, 3, 0, 0, 0, 4, 0, 0, 0},
						   {0, 0, 0, 5, 0, 0, 0, 0, 2}};

		int[][] prueba2 = {{0, 0, 0, 2, 6, 0, 7, 0, 1},
						   {6, 8, 0, 0, 7, 0, 0, 9, 0},
						   {1, 9, 0, 0, 0, 4, 5, 0, 0},
						   {8, 2, 0, 1, 0, 0, 0, 4, 0},
						   {0, 0, 4, 6, 0, 2, 9, 0, 0},
						   {0, 5, 0, 0, 0, 3, 0, 2, 8},
						   {0, 0, 9, 3, 0, 0, 0, 7, 4},
						   {0, 4, 0, 0, 5, 0, 0, 3, 6},
						   {7, 0, 3, 0, 1, 8, 0, 0, 0}};

		int[][] prueba3 = {{5, 3, 0, 0, 7, 0, 0, 0, 0},
						   {6, 0, 0, 1, 9, 5, 0, 0, 0},
						   {0, 9, 8, 0, 0, 0, 0, 6, 0},
						   {8, 0, 0, 0, 6, 0, 0, 0, 3},
						   {4, 0, 0, 8, 0, 3, 0, 0, 1},
						   {7, 0, 0, 0, 2, 0, 0, 0, 6},
						   {0, 6, 0, 0, 0, 0, 2, 8, 0},
						   {0, 0, 0, 4, 1, 9, 0, 0, 5},
						   {0, 0, 0, 0, 8, 0, 0, 7, 9}};

		int[][] prueba4 = {{0, 0, 5, 8, 0, 7, 0, 9, 0},
						   {0, 0, 0, 0, 0, 0, 0, 0, 4},
						   {0, 0, 0, 0, 0, 3, 0, 0, 8},
						   {0, 0, 3, 0, 2, 1, 0, 0, 0},
						   {5, 2, 0, 0, 0, 0, 0, 0, 6},
						   {0, 0, 8, 7, 3, 0, 4, 0, 0},
						   {0, 0, 0, 0, 0, 0, 0, 1, 0},
						   {6, 0, 0, 0, 4, 0, 0, 0, 0},
						   {2, 0, 0, 3, 0, 0, 5, 0, 0}};
		*/
		//solve(prueba4);
	}
}

