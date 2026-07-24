class Solution {
    public int uniqueXorTriplets(int[] nums) {
        final int MAXX = 2048;

        boolean[][] dp = new boolean[4][MAXX];
        dp[0][0] = true;

        for (int v : nums) {
            boolean[][] ndp = new boolean[4][MAXX];

            for (int i = 0; i < 4; i++) {
                System.arraycopy(dp[i], 0, ndp[i], 0, MAXX);
            }

            for (int cnt = 0; cnt <= 3; cnt++) {
                for (int x = 0; x < MAXX; x++) {
                    if (!dp[cnt][x]) continue;

                    for (int t = 1; t <= 3; t++) {
                        if (cnt + t <= 3) {
                            int nx = x ^ ((t % 2 == 1) ? v : 0);
                            ndp[cnt + t][nx] = true;
                        }
                    }
                }
            }

            dp = ndp;
        }

        int ans = 0;
        for (boolean b : dp[3]) {
            if (b) ans++;
        }

        return ans;
    }
}
