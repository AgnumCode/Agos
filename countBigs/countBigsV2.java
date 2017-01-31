
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

	public static void main(String[] args) {
	
		//if threshold < value, value is counted "bigger"
	    int threshold = 0;

		//Test array [1 , 2 , 3 , 4 , 5]
		ArrayList<Integer> testArray = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
		System.out.print("Expect: 5\nGot: " + countBigsV2(testArray, threshold));

	}

	static int countBigsV2(List<Integer> nums, int threshold) {

		int soFar = 0;
		while (!nums.isEmpty()) {
			if (nums.get(0) > threshold) {
				soFar++;
			}
			nums = nums.subList(1, nums.size());
			countBigsV2(nums, threshold);
		}
		return soFar;
	}
}
