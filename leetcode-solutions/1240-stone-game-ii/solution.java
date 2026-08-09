import java.util.*;

class Solution {
    private int[][] memo;
    private int[] suffix;
    private int n;

    public int stoneGameII(int[] piles) {
        n = piles.length;

        suffix = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        memo = new int[n + 1][n + 1];

        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return dp(0, 1);
    }

    private int dp(int i, int M) {
        if (i >= n) {
            return 0;
        }

        if (memo[i][M] != -1) {
            return memo[i][M];
        }

        // Current player can take all remaining piles.
        if (i + 2 * M >= n) {
            return memo[i][M] = suffix[i];
        }

        int best = 1;

        for (int X = 1; X <= 2 * M; X++) {
            int opponent = dp(i + X, Math.max(M, X));

            // Total remaining stones - opponent's maximum
            int current = suffix[i] - opponent;

            best = Math.max(best, current);
        }

        return memo[i][M] = best;
    }
}
