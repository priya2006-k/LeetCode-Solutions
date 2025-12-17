import java.util.Arrays;

public class Solution {
    public long maximumProfit(int[] prices, int k) {
        int n = prices.length;
        long NEG = Long.MIN_VALUE / 4;

        long[][] dp = new long[k + 1][3];
        for (int t = 0; t <= k; t++) {
            Arrays.fill(dp[t], NEG);
        }

        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = prices[0];

        for (int i = 1; i < n; i++) {
            long price = prices[i];
            long[][] next = new long[k + 1][3];

            for (int t = 0; t <= k; t++) {
                Arrays.fill(next[t], NEG);

                next[t][0] = dp[t][0];
                if (t > 0) {
                    next[t][0] = Math.max(next[t][0], dp[t - 1][1] + price);
                    next[t][0] = Math.max(next[t][0], dp[t - 1][2] - price);
                }

                next[t][1] = Math.max(dp[t][1], dp[t][0] - price);
                next[t][2] = Math.max(dp[t][2], dp[t][0] + price);
            }
            dp = next;
        }

        long ans = 0;
        for (int t = 0; t <= k; t++) {
            ans = Math.max(ans, dp[t][0]);
        }
        return ans;
    }
}
