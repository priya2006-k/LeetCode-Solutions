class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[] component = new int[n];

        for (int i = 1; i < n; i++) {
            component[i] = component[i - 1];

            if (nums[i] - nums[i - 1] > maxDiff) {
                component[i]++;
            }
        }

        boolean[] answer = new boolean[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];

            answer[i] = component[u] == component[v];
        }

        return answer;
    }
}
