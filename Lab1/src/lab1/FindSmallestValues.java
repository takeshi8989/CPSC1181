/**
 * 
 */
package lab1;

/**
 * @author Takeshi Hashimoto
 * generate 1 dimension array of integers from 2 dimension array by taking the smallest numbers from each row
 */
public class FindSmallestValues {

	/**
	 * @param args
	 * Main Method
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] sample = new int[4][5];
		generateRandomArray(sample);
		int[] result = smallestValues(sample);
		
		for(int i = 0; i < result.length; i++) {
			System.out.print(result[i] + " ");
		}
	}
	
	/**
	 * This method create 1d array with smallest value of each row from given 2d array
	 * 
	 * @param num 2d integer array
	 * @return the 1d array with smallest values in rows
	 */
	public static int[] smallestValues(int[][] nums) {
		int[] array = new int[nums.length];
		for(int i = 0; i < nums.length; i++) {
			// minimal value in each row
			int min = nums[i][0];
			for(int j = 1; j < nums[i].length; j++) {
				if(nums[i][j] < min) {
					min = nums[i][j];
				}
			}
			array[i] = min;
		}
		return array;
	}
	
	/**
	 * This method assign random value from 0 to 9 into a 2d array.
	 * 
	 * @param array 2d integer array
	 */
	public static void generateRandomArray(int[][] array) {
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array[i].length; j++) {
				// random value 0 to 9
				int random = (int)(Math.random() * 10);
				array[i][j] = random;
			}
		}
	}

}
