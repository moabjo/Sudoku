package sudoku;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

class Sudoku_Test {
	private int[][] board;
	private SudokuSolver sudoku;

	@Before
	public void setUp() {
		board = new int[9][9];
		sudoku = new SudokuMain(board);

	}

	@After
	public void tearDown() {
		sudoku.clear();
	}

	@Test
	public void testEmptySudoku() {
		board = new int [][]{ { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
		
		sudoku = new SudokuMain(board);
		assertTrue(sudoku.solve()); // ett tomt bräde fylls automatiskt med nollor
	}

	@Test
	public void testSolvableSudoku() {
		board = new int[][] { { 0, 0, 8, 0, 0, 9, 0, 6, 2 }, { 0, 0, 0, 0, 0, 0, 0, 0, 5 },
				{ 1, 0, 2, 5, 0, 0, 0, 0, 0 }, { 0, 0, 0, 2, 1, 0, 0, 9, 0 }, { 0, 5, 0, 0, 0, 0, 6, 0, 0 },
				{ 6, 0, 0, 0, 0, 0, 0, 2, 8 }, { 4, 1, 0, 6, 0, 8, 0, 0, 0 }, { 8, 6, 0, 0, 3, 0, 1, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 4, 0, 0 } };
		sudoku = new SudokuMain(board);
		assertTrue(sudoku.solve());

	}
	@Test
	public void testUnsolvableSudoku() {
		board = new int [][] {{ 1,2,1}};
		sudoku = new SudokuMain(board);
		assertFalse(sudoku.solve());
	}

}
