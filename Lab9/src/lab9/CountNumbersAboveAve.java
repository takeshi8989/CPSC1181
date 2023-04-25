package lab9;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author Takeshi Hashimoto
 * This class generate random integer 0 to 100, and output it in output.txt file.
 * Then, calculate the average value of the ten numbers, count the number of numbers that are greater
 * than the average.
 * 
 */
public class CountNumbersAboveAve {

	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args){
        int numElements = 10;
        String fileName = "output.txt";
        System.out.println("\nThe " + numElements + " random numbers are:\n");
        int total = writeRandomNumbers(fileName, numElements);

        
        double average = (double)total / numElements;
        int aboveNums = countAboveNums(fileName, average);
        System.out.println("The average is " + average + " and there are " + aboveNums + " numbers above the average.");
        System.out.println();
	}

	/**
	 * write random numbers onto the file. return the sum of these numbers
	 * @param fileName output filename
	 * @param numElements the number of random integers
	 * @return total value of the numbers
	 */
    public static int writeRandomNumbers(String fileName, int numElements) {
        int maxValue = 100;
        int total = 0;
        try {
            PrintWriter writer = new PrintWriter(fileName);
            for(int i = 0; i < numElements; i++) {
                int num = (int)(Math.random() * maxValue);
                total += num;
                writer.write(num + " ");
                System.out.print(num + " ");
            }
            writer.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("\n");
        return total;
    }

    /**
     * read numbers from a file, then count how many values are greater than the average
     * average is provided by argument
     * @param fileName filename that the function read numbers from
     * @param average average of the numbers
     * @return the number of elements above average
     */
    public static int countAboveNums(String fileName, double average) {
        File file = new File(fileName);
        Scanner scan = null;
        int count = 0;
        try{
            scan = new Scanner(file);
            while(scan.hasNextInt()){
                int num = scan.nextInt();
                if(num > average) count++;
            }
            scan.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        return count;  
    }
}