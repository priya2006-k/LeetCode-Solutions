import java.util.*;

class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        int[][] a = new int[n][4];

        for (int i = 0; i < n; i++) {
            a[i][0] = intervals.get(i).get(0);
            a[i][1] = intervals.get(i).get(1);
            a[i][2] = intervals.get(i).get(2);
            a[i][3] = i;
        }

        Arrays.sort(a, (x, y) -> {
            if (x[1] != y[1]) return Integer.compare(x[1], y[1]);
            return Integer.compare(x[0], y[0]);
        });

        int[] prev = new int[n];

        for (int i = 0; i < n; i++) {
            int lo = 0, hi = i - 1, ans = -1;

            while (lo <= hi) {
                int mid = (lo + hi) >>> 1;

                if (a[mid][1] < a[i][0]) {
                    ans = mid;
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }

            prev[i] = ans;
        }

        long NEG = Long.MIN_VALUE / 4;
        long[][] dp = new long[n + 1][5];
        int[][][] path = new int[n + 1][5][];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], NEG);
        }

        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int cnt = 0; cnt <= 4; cnt++) {
                dp[i][cnt] = dp[i - 1][cnt];
                path[i][cnt] = path[i - 1][cnt];
            }

            int p = prev[i - 1] + 1;

            for (int cnt = 1; cnt <= 4; cnt++) {
                if (dp[p][cnt - 1] == NEG) continue;

                long score = dp[p][cnt - 1] + a[i - 1][2];

                int[] candidate = new int[cnt];

                if (cnt > 1) {
                    System.arraycopy(
                        path[p][cnt - 1], 0,
                        candidate, 0, cnt - 1
                    );
                }

                candidate[cnt - 1] = a[i - 1][3];
                Arrays.sort(candidate);

                if (score > dp[i][cnt] ||
                    (score == dp[i][cnt] &&
                     smaller(candidate, path[i][cnt]))) {
                    dp[i][cnt] = score;
                    path[i][cnt] = candidate;
                }
            }
        }

        long best = 0;
        int[] answer = new int[0];

        for (int cnt = 1; cnt <= 4; cnt++) {
            if (dp[n][cnt] > best ||
                (dp[n][cnt] == best &&
                 smaller(path[n][cnt], answer))) {
                best = dp[n][cnt];
                answer = path[n][cnt];
            }
        }

        return answer;
    }

    private boolean smaller(int[] a, int[] b) {
        if (a == null) return false;
        if (b == null) return true;

        int len = Math.min(a.length, b.length);

        for (int i = 0; i < len; i++) {
            if (a[i] != b[i]) {
                return a[i] < b[i];
            }
        }

        return a.length < b.length;
    }
}
