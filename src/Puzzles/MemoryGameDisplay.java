import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;

public class MemoryGameDisplay{

	public static final int RED = 1;
	public static final int YLW = 2;
	public static final int GRN = 3;
	public static final int BLU = 4;

	public static JPanel memoryPuzzle;

	private static final int CORRECT_SEQUENCE[] = {RED, RED, GRN, YLW, BLU, GRN};
	private static int correctGuesses = 0;

	public static void startMemoryPuzzle(){
		memoryPuzzle = new JPanel();
		memoryPuzzle.setSize(700, 500);

		GameButton redButton = new GameButton(100, Color.red, RED);
		GameButton yellowButton = new GameButton(100, Color.yellow, YLW);
		GameButton greenButton = new GameButton(100, Color.green, GRN);
		GameButton blueButton = new GameButton(100, Color.cyan, BLU);
		ColorSequence colorSequence  = new ColorSequence(200);

		JPanel mainPanel = new JPanel();
		JPanel buttonPanel = new JPanel();

		mainPanel.setLayout(new BorderLayout());

		buttonPanel.add(redButton);
		buttonPanel.add(yellowButton);
		buttonPanel.add(greenButton);
		buttonPanel.add(blueButton);


		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		mainPanel.add(colorSequence, BorderLayout.CENTER);

		memoryPuzzle.add(mainPanel);
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