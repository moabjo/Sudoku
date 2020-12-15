package sudoku;

public class MBSudokuSolver implements SudokuSolver {

	/**
	 * Sudokulösare
	 * 
	 * @author Moa Björkman & Blenda Öhman
	 *
	 */

	private int[][] sudoMx;

	/**
	 * Skapar ett tomt sudokubräde där alla rutor har värdet 0.
	 */
	public MBSudokuSolver() {
		sudoMx = new int[9][9];
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				sudoMx[r][c] = 0;
			}
		}

	}

	/**
	 * Skapar ett sudokubräde med samma värden som  i matrisen numbers.
	 * @param numbers
	 */
	public MBSudokuSolver(int[][] numbers) {
		sudoMx = numbers;

	}
	
	/**
	 * Rensar hela brädet och sätter alla rutor till värdet 0.
	 */
	@Override
	public void clear() {
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				sudoMx[r][c] = 0;
			}
		}

	}

	/**
	 * setNumber sätter in numret number på platsen [row][col] i matrisen
	 * 
	 * @param row
	 * @param col
	 * @param number
	 */

	@Override
	public void setNumber(int row, int col, int number) {

		sudoMx[row][col] = number;
	}

	/**
	 * trySetNumber ser om numret number uppfyller reglerna för sudoku
	 * 
	 * @param row
	 * @param col
	 * @param number
	 * @return returnerar true om number uppfyller reglerna, false annars
	 */
	@Override
	public boolean trySetNumber(int row, int col, int number) {
		// Är siffran unik i rad, col, och ruta
		// är det en siffra mellan 1-9

		sudoMx[row][col] = 0;
		if (rowCheck(row, number) && colCheck(col, number) && sqrCheck(row, col, number)) {
			sudoMx[row][col] = number;
			return true;
		} else {
			return false;
		}

		//
	}

	private boolean rowCheck(int row, int number) {
		for (int i = 0; i < 9; i++) {
			if (sudoMx[row][i] == number) {
				return false;
			}
		}
		return true;
	}

	private boolean colCheck(int col, int number) {
		for (int i = 0; i < 9; i++) {
			if (sudoMx[i][col] == number) {
				return false;
			}
		}
		return true;
	}

	private boolean sqrCheck(int row, int col, int number) {
		int startSqrRow = (row / 3) * 3; // heltalsdivision * 3 ger platserna 0,3,6
		int startSqrCol = (col / 3) * 3;

		for (int r = startSqrRow; r < startSqrRow + 3; r++) {
			for (int c = startSqrCol; c < startSqrCol + 3; c++) {
				if (sudoMx[r][c] == number) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Hämtar värdet i matrisen med radnummer row och kolumnnumret col.
	 * 
	 * @param row
	 * @param col
	 * @return Värdet i matrisen
	 */
	@Override
	public int getNumber(int row, int col) {
		return sudoMx[row][col];
	}

	/**
	 * Tar bort siffran på radnummer row och kolumnnumret col och sätter till 0.
	 * 
	 * @param row
	 * @param col
	 */
	@Override
	public void removeNumber(int row, int col) {
		sudoMx[row][col] = 0;

	}

	/*
	 * Kollar så att alla inskrivna siffor inte bryter mot reglerna
	 */
	private boolean isAllowed() {

		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				if (sudoMx[r][c] != 0) {
					if (!trySetNumber(r, c, sudoMx[r][c])) {

						return false;
					}
				}

			}
		}

		return true;
	}

	/**
	 * Solve() är en rekursiv metod som löser ett sudokubräde
	 * 
	 * @return true om sudokut är lösbart, false annars
	 */
	@Override
	public boolean solve() {
		if (isAllowed()) {
			return solve(0, 0);
		} else {
			return false;
		}

	}

	/*
	 * Privat rekursiv metod för att lösa sudokut.
	 * */
	private boolean solve(int r, int c) { // Rekursiv hjälpmetod
		if (sudoMx[r][c] == 0) {// Om rutan är tom
			if (r == 8 && c == 8) { // Sista rutan i sudokut
				for (int i = 1; i < 10; i++) {
					if (trySetNumber(r, c, i)) { // Om i går att sätta in
						sudoMx[r][c] = i;
						return true; // Sudokut är löst
					}
				}
				return false;
			}

			for (int i = 1; i < 10; i++) {
				if (trySetNumber(r, c, i)) { // uppfyller i reglerna för sudoku
					sudoMx[r][c] = i; // I så fall sätt in i
					if (c < 8) {
						if (solve(r, c + 1)) {// Rekursivt anrop till nästa ruta (samma rad)
							return true;
						}
					} else if (solve(r + 1, 0)) {// Rekursivt anrop till nästa ruta (nästa rad)
						return true;
					}
				}
			}

			// Hit kommer vi om vi måste gå tillbaka. Inget värde på denna ruta gick :(
			sudoMx[r][c] = 0; // sätt tillbaka rutan till tom
			return false;

		} else { // Rutan är ifylld
			if (r == 8 && c == 8) { // Sista rutan, kolla om värdet får vara där
				return trySetNumber(r, c, sudoMx[r][c]);
			}
			if (trySetNumber(r, c, sudoMx[r][c])) { // Kollar vi om värdet får vara där
				if (c < 8) {
					return solve(r, c + 1); // Rekursivt anrop till nästa ruta (samma rad)
				} else {
					return solve(r + 1, 0); // Rekursivt anrop till nästa ruta (nästa rad)
				}
			}
		}

		return false;
	}

	/*Vi gör ett mock-objekt för att man inte ska kunna ändra den privata matrisen
	 * används den här någonsin? vi använder ju inte den för att fylla brädet i
	 * SudokuView t.ex.
	 */
	@Override
	public int[][] getNumbers() {
		int[][] mockSudoMx = new int[9][9];
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				mockSudoMx[r][c] = sudoMx[r][c];
			}
		}
		return mockSudoMx;
	}

	/**
	 * Sätter brädets matris till matrisen numbers.
	 * @param numbers
	 */
	@Override
	public void setNumbers(int[][] numbers) {
		sudoMx = numbers;

	}

}
