from bisect import bisect_left, bisect_right

class Solution:
    def sumAndMultiply(self, s, queries):
        MOD = 10**9 + 7
        n = len(s)

        # Prefix sum of non-zero digits
        prefix_sum = [0] * (n + 1)

        positions = []
        digits = []

        for i, ch in enumerate(s):
            d = int(ch)
            prefix_sum[i + 1] = prefix_sum[i]
            if d != 0:
                prefix_sum[i + 1] += d
                positions.append(i)
                digits.append(d)

        k = len(digits)

        # Powers of 10
        pow10 = [1] * (k + 1)
        for i in range(1, k + 1):
            pow10[i] = (pow10[i - 1] * 10) % MOD

        # Prefix values of concatenated non-zero digits
        pref = [0] * (k + 1)
        for i in range(k):
            pref[i + 1] = (pref[i] * 10 + digits[i]) % MOD

        ans = []

        for l, r in queries:
            left = bisect_left(positions, l)
            right = bisect_right(positions, r) - 1

            if left > right:
                ans.append(0)
                continue

            length = right - left + 1

            x = (pref[right + 1] - pref[left] * pow10[length]) % MOD
            digit_sum = prefix_sum[r + 1] - prefix_sum[l]

            ans.append((x * digit_sum) % MOD)

        return ans
