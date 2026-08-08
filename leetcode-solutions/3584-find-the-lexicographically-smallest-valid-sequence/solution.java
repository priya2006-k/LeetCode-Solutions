import java.util.*;

class Solution {

    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // Store positions of every character in word1.
        ArrayList<Integer>[] pos = new ArrayList[26];

        for (int i = 0; i < 26; i++) {
            pos[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            pos[word1.charAt(i) - 'a'].add(i);
        }

        /*
         * latest0[j]:
         * Largest possible first index in word1 for word2[j...]
         * with 0 mismatches.
         *
         * latest1[j]:
         * Largest possible first index in word1 for word2[j...]
         * with at most 1 mismatch.
         */
        int[] latest0 = new int[m + 1];
        int[] latest1 = new int[m + 1];

        Arrays.fill(latest0, -1);
        Arrays.fill(latest1, -1);

        // Empty suffix.
        latest0[m] = n;
        latest1[m] = n;

        for (int j = m - 1; j >= 0; j--) {
            int c = word2.charAt(j) - 'a';

            // Exact match.
            int bound = latest0[j + 1];

            if (bound > 0) {
                int p = predecessor(pos[c], bound);

                if (p != -1) {
                    latest0[j] = p;
                }
            }

            // At most one mismatch:
            // Option 1: current character matches.
            bound = latest1[j + 1];

            if (bound > 0) {
                int p = predecessor(pos[c], bound);

                if (p != -1) {
                    latest1[j] = p;
                }
            }

            // Option 2: current character is the one mismatch.
            bound = latest0[j + 1];

            if (bound > 0) {
                int p = bound - 1;

                if (p >= 0 && word1.charAt(p) == word2.charAt(j)) {
                    p--;
                }

                if (p >= 0) {
                    latest1[j] = Math.max(latest1[j], p);
                }
            }
        }

        if (latest1[0] == -1) {
            return new int[0];
        }

        int[] answer = new int[m];

        int prev = -1;
        boolean mismatchAvailable = true;

        for (int j = 0; j < m; j++) {
            char target = word2.charAt(j);

            int bestIndex = Integer.MAX_VALUE;
            boolean useMismatch = false;

            // Option 1: exact match.
            ArrayList<Integer> list = pos[target - 'a'];

            int idx = upperBound(list, prev);
            if (idx < list.size()) {
                int p = list.get(idx);

                if (latest1[j + 1] > p) {
                    bestIndex = p;
                    useMismatch = false;
                }
            }

            // Option 2: mismatch.
            if (mismatchAvailable) {
                int p = prev + 1;

                if (p < n && word1.charAt(p) == target) {
                    p++;
                }

                if (p < n && latest0[j + 1] > p) {
                    if (p < bestIndex) {
                        bestIndex = p;
                        useMismatch = true;
                    }
                }
            }

            if (bestIndex == Integer.MAX_VALUE) {
                return new int[0];
            }

            answer[j] = bestIndex;
            prev = bestIndex;

            if (useMismatch) {
                mismatchAvailable = false;
            }
        }

        return answer;
    }

    // Largest value in list that is strictly less than bound.
    private int predecessor(ArrayList<Integer> list, int bound) {
        int lo = 0;
        int hi = list.size() - 1;
        int ans = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (list.get(mid) < bound) {
                ans = list.get(mid);
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }

    // First index in list whose value is > target.
    private int upperBound(ArrayList<Integer> list, int target) {
        int lo = 0;
        int hi = list.size();

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (list.get(mid) <= target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        return lo;
    }
}
