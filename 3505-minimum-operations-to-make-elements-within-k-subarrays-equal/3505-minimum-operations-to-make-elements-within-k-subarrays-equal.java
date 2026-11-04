
import java.util.*;

class Solution {

    class Fenwick {
        long[] tree;
        int n;

        Fenwick(int n) {
            this.n = n;
            tree = new long[n + 1];
        }

        void update(int i, long val) {
            while (i <= n) {
                tree[i] += val;
                i += i & -i;
            }
        }

        long query(int i) {
            long sum = 0;
            while (i > 0) {
                sum += tree[i];
                i -= i & -i;
            }
            return sum;
        }

        int kth(long k) {
            int idx = 0;

            for (int step = Integer.highestOneBit(n);
                 step > 0; step >>= 1) {

                int next = idx + step;

                if (next <= n && tree[next] < k) {
                    idx = next;
                    k -= tree[next];
                }
            }

            return idx + 1;
        }
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int m = 0;

        for (int v : sorted) {
            if (m == 0 || sorted[m - 1] != v) {
                sorted[m++] = v;
            }
        }

        int[] vals = Arrays.copyOf(sorted, m);

        Fenwick countBIT = new Fenwick(m);
        Fenwick sumBIT = new Fenwick(m);

        long windowSum = 0;

        long[][] dp = new long[x + 1][k + 1];

        long INF = Long.MAX_VALUE / 4;

        for (long[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {

            int cur = i % (x + 1);
            int prev = (i - 1) % (x + 1);

            System.arraycopy(dp[prev], 0, dp[cur], 0, k + 1);

            int addIdx = Arrays.binarySearch(vals, nums[i - 1]) + 1;

            countBIT.update(addIdx, 1);
            sumBIT.update(addIdx, nums[i - 1]);

            windowSum += nums[i - 1];

            if (i > x) {

                int removeIdx =
                    Arrays.binarySearch(vals, nums[i - x - 1]) + 1;

                countBIT.update(removeIdx, -1);
                sumBIT.update(removeIdx, -nums[i - x - 1]);

                windowSum -= nums[i - x - 1];
            }

            if (i >= x) {

                int medianIdx = countBIT.kth((x + 1) / 2);
                long median = vals[medianIdx - 1];

                long leftCount = countBIT.query(medianIdx);
                long leftSum = sumBIT.query(medianIdx);

                long rightCount = x - leftCount;
                long rightSum = windowSum - leftSum;

                long cost =
                    median * leftCount - leftSum
                    + rightSum - median * rightCount;

                int source = (i - x) % (x + 1);

                for (int j = 1; j <= k; j++) {

                    if (dp[source][j - 1] != INF) {

                        dp[cur][j] = Math.min(
                            dp[cur][j],
                            dp[source][j - 1] + cost
                        );
                    }
                }
            }
        }

        return dp[n % (x + 1)][k];
    }
}
