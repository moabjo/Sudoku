package sudoku;

import sudoku.SudokuSolver;

public class SudokuApplication {

	public static void main(String[] args) {
		//SudokuSolver sudokuSolver = new SudokuMain();
		SudokuView window = new SudokuView("Sudoku", 500, 500);
		
		
		//Lägg in lite siffror
		// det här är ett lösbart sudoku
//		sudokuSolver.setNumber(0, 3, 8);
//		sudokuSolver.setNumber(0, 5, 1);
//		
//		sudokuSolver.setNumber(1, 6, 4);
//		sudokuSolver.setNumber(1, 7, 3);
//		sudokuSolver.setNumber(2, 0, 5);
//		sudokuSolver.setNumber(3, 4, 7);
//		sudokuSolver.setNumber(3, 6, 8);
//		sudokuSolver.setNumber(4, 6, 1);
//		sudokuSolver.setNumber(5, 1, 2);
//		sudokuSolver.setNumber(5, 4, 3);
//		sudokuSolver.setNumber(6, 0, 6);
//		sudokuSolver.setNumber(6, 7, 7);
//		sudokuSolver.setNumber(6, 8, 5);
//		sudokuSolver.setNumber(7, 2, 3);
//		sudokuSolver.setNumber(7, 3, 4);
//		sudokuSolver.setNumber(8, 3, 2);
//		sudokuSolver.setNumber(8, 6, 6);


		//System.out.print(sudokuSolver.solve());
		


//		
//		//Om man klickar på clear:
//		//sudokuSolver.clear();
//		
//		//Om man klickar på solve: 
//		
//		//Kolla om det går att lösa
//		System.out.print(sudokuSolver.solve());
//		
		
//		if (sudokuSolver.solve()) {
//			//Det här ska vi presentera i grafiska:
//			System.out.println(sudokuSolver.getNumbers());
//		} else {
//			
//			//Skriv ut meddelande; "Går ej att lösa"
//		}

		
		
	}
	
	
}
