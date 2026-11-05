
import java.util.Arrays;

class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {

        Arrays.sort(arr2);

        int count = 0;

        for (int x : arr1) {

            int left = 0;
            int right = arr2.length;

            // Find first element >= x - d
            while (left < right) {

                int mid = left + (right - left) / 2;

                if (arr2[mid] < (long) x - d) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            // Check whether this element is within the range
            if (left == arr2.length ||
                (long) arr2[left] > (long) x + d) {

                count++;
            }
        }

        return count;
    }
}
