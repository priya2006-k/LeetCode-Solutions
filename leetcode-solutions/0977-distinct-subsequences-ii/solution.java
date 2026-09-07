class Solution {
    public int distinctSubseqII(String s) {
        long[] dp = new long[26];
        long total = 0;
        long MOD = 1000000007L;

        for (char c : s.toCharArray()) {
            int x = c - 'a';
            long add = (total + 1) % MOD;

            total = (total + add - dp[x] + MOD) % MOD;
            dp[x] = add;
        }

        return (int) total;
    }
}
