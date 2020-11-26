package sudoku;

import sudoku.SudokuSolverInterface.SudokuSolver;

public class SudokuApplication {

	public static void main(String args[][]) {
		SudokuSolver sudokuSolver = new SudokuMain();
		
		//Lägg in lite siffror
		sudokuSolver.setNumber(1, 1, 2);
		
		//Om man klickar på clear:
		sudokuSolver.clear();
		
		//Om man klickar på solve: 
		
		//Kolla om det går att lösa
		if (sudokuSolver.solve()) {
			//Det här ska vi presentera i grafiska:
			sudokuSolver.getNumbers();
		} else {
			
			//Skriv ut meddelande; "Går ej att lösa"
		}
	}
	
	
}
