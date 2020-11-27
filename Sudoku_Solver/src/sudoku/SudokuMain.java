package sudoku;

import sudoku.SudokuSolverInterface.SudokuSolver;

public class SudokuMain implements SudokuSolver {

	private int[][] sudoMx;

	public SudokuMain() {
		sudoMx = new int[9][9];
		// Lägg in i matrisen från grafiskt


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
		return (rowCheck(row, number) && colCheck(col, number) && sqrCheck(row, col, number)); 

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
		int startSqrRow = (row/3) * 3; //heltalsdivision * 3 ger platserna 0,3,6
		int startSqrCol = (col/3) * 3;
		
		for(int i = startSqrRow; i < startSqrRow + 3; i++) {
			for (int j = startSqrCol; j < startSqrCol + 3; j++) {
				if(sudoMx[i][j]==number) {
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

	private boolean solve(int r, int c) {

		if (c > 8 && r > 8) { // detta kan bli fel om sista rutan är ifylld tror vi??
			return true;
		} else if (sudoMx[r][c] == 0) { // om rutan inte är ifylld
			for (int i = 0; i < 9; i++) {

				if (trySetNumber(r, c, i)) {
					sudoMx[r][c] = i;
					if (c < 9) {
						if (solve(r, c + 1)) { // Den ska gå till nästa ruta.
							return true;

						} else {
							if (solve(r + 1, 0)) { // om vi nått slutet av raden ska vi hoppa ner en rad och börja om :)
								return true;
							}

						}
					}
					// Det här är samma sak
					// if (c < 9) {
					// c += 1;
					// } else {
					// r += 1;
					// c=0;
					// }
					// if solve((r, c)) {
					// return true;
					// }

				} else {
					// Det funkar inte vi måste backa
					sudoMx[r][c] = 0;
					return false;
				}
			}
		} else {
			if (c < 9) {
				if (solve(r, c + 1)) {
					return true;// Den ska gå till nästa ruta.
				}
			} else {
				if (solve(r + 1, 0)) {
					return true;
				}

			}
		}
		// Det gick inte att lösa. Returnera false.
		return false;
	}

	// Vi gör ett mock-objekt för att man inte ska kunna ändra den privata matrisen
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

	@Override
	public void setNumbers(int[][] numbers) {
		sudoMx = numbers;

	}

}
