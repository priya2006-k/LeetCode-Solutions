import java.util.*;

public class Solution {
    public int minDeletionSize(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();
        int[] dp = new int[m];
        Arrays.fill(dp, 1);
        int maxLen = 1;
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < j; i++) {
                if (canKeep(strs, i, j)) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[j]);
        }
        return m - maxLen;
    }

    private boolean canKeep(String[] strs, int i, int j) {
        for (String s : strs) {
            if (s.charAt(i) > s.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}


