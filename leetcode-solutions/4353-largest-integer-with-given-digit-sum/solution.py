class Solution(object):
    def largestInteger(self, n, s):
        """
        :type n: int
        :type s: int
        :rtype: int
        """
        if s == 0:
            return 0

        if s > 9 * n:
            return -1

        ans = []

        while n > 0:
            digit = min(9, s)
            ans.append(str(digit))
            s -= digit
            n -= 1

        return int("".join(ans))
