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
		//
		return false;
	}

	@Override
	public int getNumber(int row, int col) {
		// TODO Auto-generated method stub
		return sudoMx[row][col];
	}

	@Override
	public void removeNumber(int row, int col) {
		sudoMx[row][col] = 0;

	}

	@Override
	public boolean solve() {
		// Söndra, häska
		// Om 1 lösning, return true
		// Om ingen lösning return false
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
