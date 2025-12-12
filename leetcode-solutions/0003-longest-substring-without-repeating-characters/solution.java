import java.util.*;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] last = new int[256];
        Arrays.fill(last, -1);
        int start = 0, best = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            start = Math.max(start, last[c] + 1);
            best = Math.max(best, i - start + 1);
            last[c] = i;
        }
        return best;
    }
}
