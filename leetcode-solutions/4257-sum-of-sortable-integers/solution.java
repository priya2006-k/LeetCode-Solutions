import java.util.*;

class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int ans = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;

            boolean ok = true;

            for (int s = 0; s < n && ok; s += k) {
                int desc = 0, pos = -1;

                for (int j = 0; j < k; j++) {
                    int a = nums[s + j];
                    int b = nums[s + (j + 1) % k];

                    if (a > b) {
                        desc++;
                        pos = j;
                        if (desc > 1) break;
                    }
                }

                if (desc > 1) {
                    ok = false;
                    break;
                }

                int r = (desc == 0) ? 0 : (pos + 1) % k;

                for (int j = 0; j < k; j++) {
                    if (nums[s + (r + j) % k] != sorted[s + j]) {
                        ok = false;
                        break;
                    }
                }
            }

            if (ok) ans += k;
        }

        return ans;
    }
}
