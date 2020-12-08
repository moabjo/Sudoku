package sudoku;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

public class SudokuView extends JFrame {

	private JFrame frame;
	private JPanel buttonPanel;
	private JPanel boardPanel;
	private OneDigitField[][] fields;
	private JLabel messagePromt;
	private SudokuSolver board;
	
	/*
	 * Klass för att skapa det grafiska gränssnittet till 
	 * sudokut
	 * */
	public SudokuView(String title, int width, int height) {

		/** initierar alla komponenter **/
		frame = new JFrame(title);
		boardPanel = new JPanel();
		buttonPanel = new JPanel();
		board = new MBSudokuSolver();
		fields = new OneDigitField[9][9];
		messagePromt = new JLabel("message promt");
		/*****************************/

		/** inställningar för meddelanderutan **/
		messagePromt.setHorizontalAlignment(SwingConstants.CENTER);
		messagePromt.setPreferredSize(new Dimension(this.getWidth(), 50));
		messagePromt.setBackground(new Color(255, 150, 255));
		messagePromt.setForeground(new Color(69, 69, 69));
		messagePromt.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 20));
		messagePromt.setText("Welcome to the Sudoku solver");
		//messagePromt.set
		/*****************************/

		/** inställningar för huvudfönstret **/
		frame.setMinimumSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/**********************************/

		/** stoppar in knapparna i knappanelen */
		buttonPanel.add(new SolveButton("Solve"));
		buttonPanel.add(new ClearButton("Clear"));
		/************************************/

		boardPanel.setLayout(new GridLayout(9, 9)); // gridlayout för sudokurutorna

		/** Hittade kod för oneDigitField som vi använder som sudokurutor **/
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				fields[i][j] = new OneDigitField();

				/** de olika panelerna delas av med fina färger **/
				if (i < 3 && (j < 3 || j >= 6) || i >= 3 && i < 6 && j >= 3 && j < 6 || i >= 6 && (j < 3 || j >= 6)) {
					fields[i][j].setBackground(new Color(133, 169, 132));
				} else {
					fields[i][j].setBackground(new Color(217, 218, 207));
				}
				/**************************************************************/

				/* centrerar siffrorna i rutan */
				fields[i][j].setHorizontalAlignment(SwingConstants.CENTER);

				boardPanel.add(fields[i][j]);

			}

		}
		/***************************************************/

		/** Lägger in de olika panelerna i huvudfönstret **/
		frame.add(boardPanel, BorderLayout.CENTER);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		frame.add(messagePromt, BorderLayout.NORTH);
		/**********************************************/

		frame.setVisible(true);// Gör huvudfönstret synligt

	}

	/**
	 * SolveButton försöker lösa
	 * sudokut när man trycker på knappen.
	 * 
	 * @param name
	 */
	@SuppressWarnings("serial")
	private class SolveButton extends JButton implements ActionListener {

		private SolveButton(String name) {
			super(name);
			this.setCursor(Cursor.getPredefinedCursor(12));
			addActionListener(this);

		}

		public void actionPerformed(ActionEvent e) {

			
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {

					try {
						board.setNumber(i, j,
								Integer.parseInt(fields[i][j].getText()));
					} catch (NumberFormatException e1) { //står det ingenting annat än 0 stoppar vi in 0
						board.setNumber(i, j, 0);
					}

				}
			}

			if (board.solve()) { //går sudokut att lösa? meddela resultatet till användaren
				for (int i = 0; i < 9; i++) {
												
					for (int j = 0; j < 9; j++) {
						fields[i][j].setText(String.valueOf(board.getNumber(
								i, j)));
					}
				}
				messagePromt.setText("<html><body><center>The sudoku was solvable!<br/>The result is shown below</center></body></html>");
			} else {
				messagePromt.setText("<html><body><center>The sudoku was unsolvable<br/> Remember the rules of sudoku! :)</center></body></html>");
			}

		}

}

/**
 * ClearButton är en knapp som nollställer sudokut
 * 
 * @param name 
 */
@SuppressWarnings("serial")
private class ClearButton extends JButton implements ActionListener {

	public ClearButton(String name) {
		super(name);
		this.setCursor(Cursor.getPredefinedCursor(12));
		addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) {

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				board.setNumber(i, j, 0);
				fields[i][j].setText("");
			}
		
		}
		messagePromt.setText("Welcome to the Sudoku solver");

	}

}

/**
 * Skapar ett fält där man bara kan lägga in siffror och max ett tecken.
 * 
 * source: http://fileadmin.cs.lth.se/cs/Education/EDAA01/inl/sudoku/
 * OneLetterField.java
 * 
 */
@SuppressWarnings("serial")
private class OneDigitField extends JTextField {

	/**
	 * Creates a text field to display only one character.
	 */
	public OneDigitField() {
		super("");
		((AbstractDocument) this.getDocument()).setDocumentFilter(new OneDigitFilter());
	}

	private class OneDigitFilter extends DocumentFilter {
		OneDigitFilter() {
			super();

		}

		public void insertString(FilterBypass fb, int offset, String str, AttributeSet attr)
				throws BadLocationException {
			if ((fb.getDocument().getLength() + str.length()) > 1) {
				return;
			}
			if (!str.isEmpty() && !Character.isDigit(str.charAt(0))) {
				return;
			}
			
			fb.insertString(offset, str, attr);
		}

		public void replace(FilterBypass fb, int offset, int length, String str, AttributeSet attr)
				throws BadLocationException {
			if ((fb.getDocument().getLength() + str.length() - length) > 1) {
				return;
			}
			if (!str.isEmpty() && !Character.isDigit(str.charAt(0))) {
				messagePromt.setText("<html><body><center> Invalid character<br/>Choose a number between 1 and 9 instead!</center></body></html>");
				return;
			}
			if(str.equals("0")) {
				messagePromt.setText("<html><body><center> 0 is not valid <br/>Choose a number between 1 and 9 instead!</center></body></html>");
				return;
			}
			messagePromt.setText("Welcome to the Sudoku solver");
			fb.replace(offset, length, str, attr);
		}
	}
	

}
public static void main(String[] args) {
	//SudokuSolver sudokuSolver = new SudokuMain();
	SudokuView window = new SudokuView("Sudoku", 500, 500);
}

}