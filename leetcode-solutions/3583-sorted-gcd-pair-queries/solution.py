class Solution(object):
    def gcdValues(self, nums, queries):
        """
        :type nums: List[int]
        :type queries: List[int]
        :rtype: List[int]
        """
        from bisect import bisect_right

        MAX = max(nums)

        # Frequency of each number
        freq = [0] * (MAX + 1)
        for x in nums:
            freq[x] += 1

        # cnt[d] = numbers divisible by d
        cnt = [0] * (MAX + 1)
        for d in range(1, MAX + 1):
            for m in range(d, MAX + 1, d):
                cnt[d] += freq[m]

        # exact[d] = pairs whose gcd is exactly d
        exact = [0] * (MAX + 1)
        for d in range(MAX, 0, -1):
            pairs = cnt[d] * (cnt[d] - 1) // 2
            m = 2 * d
            while m <= MAX:
                pairs -= exact[m]
                m += d
            exact[d] = pairs

        # Prefix count of gcd values
        prefix = []
        vals = []
        total = 0
        for d in range(1, MAX + 1):
            if exact[d]:
                total += exact[d]
                prefix.append(total)
                vals.append(d)

        ans = []
        for q in queries:
            idx = bisect_right(prefix, q)
            ans.append(vals[idx])

        return ans
