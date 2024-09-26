import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;

public class MemoryGameDisplay{

	public static final int RED = 1;
	public static final int YLW = 2;
	public static final int GRN = 3;
	public static final int BLU = 4;

	private static final int CORRECT_SEQUENCE[] = {RED, RED, GRN, YLW, BLU, GRN};
	private static int correctGuesses = 0;

	public static void main(String[] args){

		JFrame frame = new JFrame("Memory Puzzle Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GameButton redButton = new GameButton(100, Color.red, RED);
		GameButton yellowButton = new GameButton(100, Color.yellow, YLW);
		GameButton greenButton = new GameButton(100, Color.green, GRN);
		GameButton blueButton = new GameButton(100, Color.cyan, BLU);
		ColorSequence colorSequence  = new ColorSequence(200);

		JPanel mainPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		
		frame.getContentPane();

		mainPanel.setLayout(new BorderLayout());

		buttonPanel.add(redButton);
		buttonPanel.add(yellowButton);
		buttonPanel.add(greenButton);
		buttonPanel.add(blueButton);


		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		mainPanel.add(colorSequence, BorderLayout.CENTER);

		frame.add(mainPanel);
		frame.setSize(700, 500);
		frame.setVisible(true);
	}

	public static void checkSequence(int memColor) {
		if (CORRECT_SEQUENCE[correctGuesses] != memColor) {
			correctGuesses = 0;
			System.out.println("Wrong Guess :(");
		} else {
			correctGuesses++;
			System.out.println("Right Guess :)");

			if (correctGuesses == 6) {
				System.out.println("You did it!!!!");
				System.exit(0);
			}
		}
	}
}