
import java.util.*;

class Solution {

    static final long BASE = 911382323L;
    static final long MOD1 = 1000000007L;
    static final long MOD2 = 1000000009L;

    long[] h1, h2, p1, p2;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        h1 = new long[n + 1];
        h2 = new long[n + 1];
        p1 = new long[n + 1];
        p2 = new long[n + 1];

        p1[0] = p2[0] = 1;

        // Precompute prefix hashes and powers
        for (int i = 0; i < n; i++) {
            p1[i + 1] = p1[i] * BASE % MOD1;
            p2[i + 1] = p2[i] * BASE % MOD2;

            h1[i + 1] = (h1[i] * BASE + nums[i]) % MOD1;
            h2[i + 1] = (h2[i] * BASE + nums[i]) % MOD2;
        }

        int low = 1, high = n;
        int ans = n;

        // Binary search for minimum length
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (check(n, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private boolean check(int n, int len) {
        HashMap<Long, Integer> map = new HashMap<>();

        for (int i = 0; i + len <= n; i++) {

            long hash1 = (h1[i + len]
                    - h1[i] * p1[len] % MOD1 + MOD1) % MOD1;

            long hash2 = (h2[i + len]
                    - h2[i] * p2[len] % MOD2 + MOD2) % MOD2;

            // Combine both hashes
            long hash = (hash1 << 32) | hash2;

            map.put(hash, map.getOrDefault(hash, 0) + 1);
        }

        // Check if any subarray occurs exactly once
        for (int count : map.values()) {
            if (count == 1) {
                return true;
            }
        }

        return false;
    }
}
