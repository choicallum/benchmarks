/**
 * Purpose: To test the efficiency of multiple different sorting algorithms. 
 * Author: Maria Litvin and Gary Litvin
 * Modified By: Callum Choi
 * Modified On: November 20th 2019
 */

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.util.Random;

public class Benchmarks extends JFrame 
{
	// Variable declaration.
	private static final long serialVersionUID = 1L;
	private static int numberOfRuns = 20;
	private JTextField arraySizeInput, display;
	private String sortMethodNames[] = { "Selection Sort", "Insertion Sort", "Mergesort", "Quicksort" };
	private JComboBox<String> chooseSortMethod;
	private final long seed;
	private int arraySize;

	// Creates the Benchmarks object and creates part of the interface.
	public Benchmarks() 
	{
		super("Benchmarks");
		
		// Creates the interface window.
		Container c = getContentPane();
		c.setLayout(new GridLayout(6, 1));

		// Creates the interface and field for the user to input how large they want the array to be.
		c.add(new JLabel(" Array size: "));
		arraySizeInput = new JTextField(4);
		arraySizeInput.setText("1000");
		arraySizeInput.selectAll();
		c.add(arraySizeInput);

		// Creates the drop down box for the user to choose which method of sorting they would like to use. 
		chooseSortMethod = new JComboBox<String>(sortMethodNames);
		c.add(chooseSortMethod);
		
		// Creates the Run button.
		JButton run = new JButton("Run");
		run.addActionListener(new RunButtonListener());
		c.add(run);

		// Creates the text box to display how long the sort took.
		c.add(new JLabel(" Avg Time (milliseconds): "));
		display = new JTextField("   Ready");
		display.setBackground(Color.YELLOW);
		display.setEditable(false);
		c.add(display);

		// Creates a seed to be used for random generation using the current time.
		seed = System.currentTimeMillis();
	}

	// Fills an array with randomly generated numbers and sorts them with a given sorting method.
	private long runSort(double[] a, int sortMethod, int numberOfRuns) 
	{
		long endTime, startTime = 0, totalTime = 0;
		Random generator = new Random(seed);

		// Runs the array generation and sort as many times as the numberOfRun parameter says to.
		while (numberOfRuns > 0) 
		{
			// Generates an array filled with random numbers.
			for (int k = 0; k < a.length; k++) 
			{
				a[k] = generator.nextDouble();
			}

			// Chooses a sorting method based on the parameter given.
			switch (sortMethod) 
			{
			case 1:
				startTime = System.currentTimeMillis();
				SelectionSort.sort(a);
				break;

			case 2:
				startTime = System.currentTimeMillis();
				InsertionSort.sort(a);
				break;

			case 3:
				startTime = System.currentTimeMillis();
				Mergesort.sort(a);
				break;

			case 4:
				startTime = System.currentTimeMillis();
				Quicksort.sort(a);
				break;
			}

			// Calculates the amount of time the sorting took.
			endTime = System.currentTimeMillis();
			long elapsedTime = endTime - startTime;
			totalTime += elapsedTime;
			numberOfRuns--;
		}

		return totalTime;
	}

	/**
	 * Purpose: To handle what occurs when the Run button is pressed.
	 * Author: Maria Litvin and Gary Litvin
	 * Modified By: Callum Choi
	 * Modified On: November 20th 2019
	 */
	private class RunButtonListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			// Takes in how large an array should be according to the user, and tries to place it into an int.
			String inputStr = arraySizeInput.getText().trim();
			try 
			{
				arraySize = Integer.parseInt(inputStr);
			}
			// If the user does not put in an integer, it returns saying that it's an invalid input.
			catch (NumberFormatException ex) 
			{
				display.setText(" Invalid array size");
				arraySize = 0;
				return;
			}

			// If the user does not put in a positive value, it also says that it is an invalid input.
			if (arraySize <= 0) 
			{
				display.setText(" Invalid array size");
				return;
			}

			// Otherwise, it returns the size of the array.
			if (arraySize <= 0)
				return;

			// Decides which sorting method should be used, and prints out 
			int sortMethod = chooseSortMethod.getSelectedIndex() + 1;
			double a[] = new double[arraySize];
			double avgTime = (double) runSort(a, sortMethod, numberOfRuns) / numberOfRuns;
			display.setText(String.format("  %.2f", avgTime));	
			
			// Displays the result of the sort. 
			arraySizeInput.selectAll();
			arraySizeInput.requestFocus();
			System.out.println("Array size = " + arraySize + " Runs = " + numberOfRuns + " " +
								sortMethodNames[sortMethod - 1] + " avg time: " + avgTime);

		}
	}

	// Main method.
	public static void main(String[] args) 
	{
		numberOfRuns = 20;

		// 
		if (args.length > 0) 
		{
			int n = -1;
			try 
			{
				n = Integer.parseInt(args[0].trim());
			} catch (NumberFormatException ex) 
			{
				System.out.println("Invalid command-line parameter");
				System.exit(1);
			}
			if (n > 0)
				numberOfRuns = n;
		}

		// Creates the initial interface for the user.
		Benchmarks window = new Benchmarks();
		window.setBounds(300, 300, 180, 200);
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		window.setVisible(true);
	}
}
