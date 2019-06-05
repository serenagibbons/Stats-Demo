import java.util.Scanner; 
import java.io.*;	// File I/O import statement

/**    This class reads numbers from a file, calculates the    
 * 	mean and standard deviation, and writes the results    
 * 	to a file. */ 

public class StatsDemo {  

	public static void main(String[] args) throws IOException { 
		double sum = 0; // The sum of the numbers 
		int count = 0; // The number of numbers added
		double mean = 0; // The average of the numbers 
		double stdDev = 0;   // The standard deviation 
		String line; // To hold a line from the file 
		double difference;   // The value and mean difference 
		
		// Create an object of type Scanner
		Scanner keyboard = new Scanner (System.in); 
		String filename;	// The user input file name
		
		// Prompt the user and read in the file name 
		System.out.println("This program calculates " + 
				"statistics on a file " + "containing a series of numbers"); 
		System.out.print("Enter the file name:  "); 
		filename = keyboard.nextLine(); 
		
		// Create a FileReader object passing it the filename
		FileReader freader = new FileReader(filename);
		
		// Create a BufferedReader object passing FileReader object 
		BufferedReader inputFile = new BufferedReader(freader);
		
        line = inputFile.readLine();			// Perform a priming read to read the first line of the file 
		
        // Loop until you are at the end of the file 
		while(line != null) {
			sum += Double.parseDouble(line);	// Convert the line to a double value and add the value to sum 
			count++;							// Increment the counter
	        line = inputFile.readLine();		// Read a new line from the file 
		}
		inputFile.close();	// Close the input file 
		freader.close();
		
		mean = sum/count;	// Store the calculated mean 
		
		// Reconnect FileReader object passing it the filename 
		freader = new FileReader(filename);	
		
		// Reconnect BufferedReader object passing FileReader object 
		inputFile = new BufferedReader(freader);

		sum = 0;	// Reinitialize the sum of the numbers 
		count = 0;	// Reinitialize the number of numbers added
		
		line = inputFile.readLine(); 	// Perform a priming read to read the first line of the file 
		
		// Loop until you are at the end of the file 
		while(line != null) {
			difference = Double.parseDouble(line) - mean;	// Convert the line to a double value and subtract the mean
			sum += Math.pow(difference, 2);		// Add the square of the difference to the sum 
			count++;							// Increment the counter
	        line = inputFile.readLine();		// Read a new line from the file 
		}
		inputFile.close();				// Close the input file 
		freader.close();
		
        stdDev = Math.sqrt(sum/count);	// Store the calculated standard deviation
		
        // Create a FileWriter object using "Results.txt" 
		FileWriter fwriter = new FileWriter("Results.txt");
		
		// Create a PrintWriter object passing the FileWriter object
		PrintWriter outputFile = new PrintWriter(fwriter);
	
		// Print the results to the output file in three-decimal format
		outputFile.printf("Mean: %.3f\n", mean); 
		outputFile.printf("Standard Deviation: %.3f\n", stdDev); 

		outputFile.close();		// Close the output file
		keyboard.close(); 		// Close Scanner object
		
		System.out.println("Data written to the file.");
	}
}