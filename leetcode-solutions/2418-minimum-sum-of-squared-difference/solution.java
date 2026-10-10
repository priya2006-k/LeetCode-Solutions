import java.util.*;

class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;
        int[] freq = new int[100001];
        long total = 0;

        for (int i = 0; i < n; i++) {
            int d = Math.abs(nums1[i] - nums2[i]);
            freq[d]++;
            total += d;
        }

        if (k >= total) {
            return 0;
        }

        for (int d = 100000; d > 0 && k > 0; d--) {
            if (freq[d] == 0) {
                continue;
            }

            int next = Math.max(d - 1, 0);
            int count = (int) Math.min((long) freq[d], k);

            freq[d] -= count;
            freq[next] += count;
            k -= count;
        }

        long result = 0;

        for (int d = 1; d < freq.length; d++) {
            result += (long) d * d * freq[d];
        }

        return result;
    }
}
