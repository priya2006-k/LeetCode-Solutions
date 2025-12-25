import java.util.*;

class Solution {
    public long maximumHappinessSum(int[] happiness, int k) {
        Arrays.sort(happiness);
        long sum = 0;
        int n = happiness.length;

        for (int i = 0; i < k; i++) {
            int val = happiness[n - 1 - i] - i;
            if (val > 0) sum += val;
        }
        return sum;
    }
}
