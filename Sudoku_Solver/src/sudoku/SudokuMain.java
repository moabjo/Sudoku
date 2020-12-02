package sudoku;

public class SudokuMain implements SudokuSolver {

	private int[][] sudoMx;

	public SudokuMain() {
		sudoMx = new int[9][9];
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				sudoMx[r][c] = 0;
			}
		}

	}

	// Konstruktor för ett förifyllt bräde
	public SudokuMain(int[][] numbers) {
		sudoMx = numbers;

	}

	@Override
	public void clear() {
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				sudoMx[r][c] = 0;
			}
		}

	}

	@Override
	public void setNumber(int row, int col, int number) {

		sudoMx[row][col] = number;
	}

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

	@Override
	public int getNumber(int row, int col) {
		return sudoMx[row][col];
	}

	@Override
	public void removeNumber(int row, int col) {
		sudoMx[row][col] = 0;

	}

	@Override
	public boolean solve() {

		return solve(0, 0);
	}

	private boolean solve(int r, int c) { // Rekursiv metod
		if (sudoMx[r][c] == 0) {// Rutan är tom

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

				if (trySetNumber(r, c, i)) { // Går det att sätta in i
					sudoMx[r][c] = i; // I så fall sätt in i
					if (c < 8) {
						if (solve(r, c + 1)) {// Rekursivt anrop till ästa ruta (samma rad)
							return true;
						}
					} else if (solve(r + 1, 0)) {// Rekursivt anrop till ästa ruta (nästa rad)
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
					return solve(r, c + 1); // Rekursivt anrop till ästa ruta (samma rad)
				} else {
					return solve(r + 1, 0); // Rekursivt anrop till ästa ruta (nästa rad)
				}
			}
		}

		return false;
	}

	// Vi gör ett mock-objekt för att man inte ska kunna ändra den privata matrisen
	//används den här någonsin? vi använder ju inte den för att fylla brädet i SudokuView t.ex.
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

	// Den här metoden känns inte så jättebra att ha tbh
	//nej jag håller med vi använder den aldrig /moa
	@Override
	public void setNumbers(int[][] numbers) {
		sudoMx = numbers;

	}

}
