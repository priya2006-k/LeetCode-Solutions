import java.util.*;

class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        int[] dp = new int[m + 1];

        for (int j = 1; j <= m; j++) dp[j] = dp[j - 1] + s2.charAt(j - 1);

        for (int i = 1; i <= n; i++) {
            int prevDiag = dp[0];
            dp[0] += s1.charAt(i - 1);

            for (int j = 1; j <= m; j++) {
                int temp = dp[j];
                char c1 = s1.charAt(i - 1), c2 = s2.charAt(j - 1);

                if (c1 == c2) {
                    dp[j] = prevDiag;
                } else {
                    dp[j] = Math.min(dp[j] + c1, dp[j - 1] + c2);
                }
                prevDiag = temp;
            }
        }
        return dp[m];
    }
}
