class Solution {
    static final int MOD = 1_000_000_007;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        int[] last = new int[100001];
        java.util.Arrays.fill(last, -1);

        long[] sum = new long[4 * n];
        long[] square = new long[4 * n];
        long[] lazy = new long[4 * n];

        long ans = 0;

        for (int i = 0; i < n; i++) {
            int left = last[nums[i]] + 1;
            update(1, 0, n - 1, left, i, sum, square, lazy);

            ans = (ans + square[1]) % MOD;
            last[nums[i]] = i;
        }

        return (int) ans;
    }

    private void update(int node, int start, int end, int l, int r,
                        long[] sum, long[] square, long[] lazy) {
        if (l > end || r < start) return;

        if (l <= start && end <= r) {
            square[node] = (square[node] + 2 * sum[node] + (end - start + 1)) % MOD;
            sum[node] = (sum[node] + end - start + 1) % MOD;
            lazy[node]++;
            return;
        }

        push(node, start, end, sum, square, lazy);

        int mid = (start + end) / 2;
        update(node * 2, start, mid, l, r, sum, square, lazy);
        update(node * 2 + 1, mid + 1, end, l, r, sum, square, lazy);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        square[node] = (square[node * 2] + square[node * 2 + 1]) % MOD;
    }

    private void push(int node, int start, int end,
                      long[] sum, long[] square, long[] lazy) {
        if (lazy[node] == 0 || start == end) return;

        int mid = (start + end) / 2;
        apply(node * 2, start, mid, lazy[node], sum, square, lazy);
        apply(node * 2 + 1, mid + 1, end, lazy[node], sum, square, lazy);

        lazy[node] = 0;
    }

    private void apply(int node, int start, int end, long delta,
                       long[] sum, long[] square, long[] lazy) {
        long len = end - start + 1;
        square[node] = (square[node] + 2 * delta % MOD * sum[node]
                + delta * delta % MOD * len) % MOD;
        sum[node] = (sum[node] + delta * len) % MOD;
        lazy[node] = (lazy[node] + delta) % MOD;
    }
}
