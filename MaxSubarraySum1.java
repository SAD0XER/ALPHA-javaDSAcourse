//Max Sum of Subarray-1 (Brute Force): Time Complexity O(n³)
public class MaxSubarraySum {

	public static void MaxSumSubArray1(int[] array) {
		int max = Integer.MIN_VALUE, sum = 0;

		for (int i = 0; i < array.length; i++) {
			sum = 0;
			for (int j = i; j < array.length; j++) {
				for (int k = i; k <= j; k++) {
					sum += array[k];
					System.out.print(array[k] + " ");
				}
				System.out.println();
				if (max < sum) max = sum;
			}
			System.out.println();
		}
		System.out.println("max = " + max);
	}

	public static void main(String[] sadoxer) {
		int[] array = {-3, 4, -1, 7, 5, -8};
		MaxSumSubArray1(array);
	}
}