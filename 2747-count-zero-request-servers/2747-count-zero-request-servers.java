
import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {

        int m = queries.length;
        int[] ans = new int[m];

        // Sort logs by time
        Arrays.sort(logs, (a, b) -> a[1] - b[1]);

        // Store query with its original index
        int[][] q = new int[m][2];

        for (int i = 0; i < m; i++) {
            q[i][0] = queries[i];
            q[i][1] = i;
        }

        // Sort queries by time
        Arrays.sort(q, (a, b) -> a[0] - b[0]);

        HashMap<Integer, Integer> map = new HashMap<>();

        int left = 0;
        int right = 0;

        for (int i = 0; i < m; i++) {

            int start = q[i][0] - x;
            int end = q[i][0];

            // Add logs within the current window
            while (right < logs.length && logs[right][1] <= end) {
                map.put(logs[right][0],
                    map.getOrDefault(logs[right][0], 0) + 1);
                right++;
            }

            // Remove logs outside the current window
            while (left < right && logs[left][1] < start) {
                int id = logs[left][0];

                map.put(id, map.get(id) - 1);

                if (map.get(id) == 0) {
                    map.remove(id);
                }

                left++;
            }

            // Servers with zero requests
            ans[q[i][1]] = n - map.size();
        }

        return ans;
    }
}
