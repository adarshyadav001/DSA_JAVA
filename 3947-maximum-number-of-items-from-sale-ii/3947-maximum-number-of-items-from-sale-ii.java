
import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {

        int n = items.length;

        // Step 1: Count occurrences of each factor
        int[] freq = new int[n + 1];

        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        // Step 2: Calculate gain for each factor
        int[] gain = new int[n + 1];

        for (int f = 1; f <= n; f++) {
            int count = 0;

            for (int j = f; j <= n; j += f) {
                count += freq[j];
            }

            gain[f] = count - 1;
        }

        // Step 3: Sort items by price
        // Pack price and gain into a long
        long[] sorted = new long[n];

        for (int i = 0; i < n; i++) {
            int price = items[i][1];
            int g = gain[items[i][0]];

            sorted[i] = ((long) price << 32)
                      | (g & 0xffffffffL);
        }

        Arrays.sort(sorted);

        // Step 4: Buy boosted copies greedily
        long remaining = budget;
        long answer = 0;

        for (long item : sorted) {

            int price = (int) (item >>> 32);
            int g = (int) item;

            // Boosted copies are not worthwhile beyond this price
            if (price > 2L * minPrice) {
                break;
            }

            if (g == 0) continue;

            long buy = Math.min(g, remaining / price);

            answer += 2 * buy;
            remaining -= buy * price;
        }

        // Step 5: Spend remaining budget on cheapest item
        answer += remaining / minPrice;

        return (int) answer;
    }
}
