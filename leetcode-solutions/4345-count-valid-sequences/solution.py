class Solution(object):
    MOD = 10 ** 9 + 7

    def countValidSequences(self, n, k):
        """
        :type n: int
        :type k: int
        :rtype: int
        """
        MOD = self.MOD

        # factorials
        fact = [1] * (n + 1)
        for i in range(1, n + 1):
            fact[i] = fact[i - 1] * i % MOD

        invFact = [1] * (n + 1)
        invFact[n] = pow(fact[n], MOD - 2, MOD)
        for i in range(n, 0, -1):
            invFact[i - 1] = invFact[i] * i % MOD

        def C(a, b):
            if b < 0 or b > a or a < 0:
                return 0
            return fact[a] * invFact[b] % MOD * invFact[a - b] % MOD

        total = C(n - 1, k - 1)

        odd = 0
        if n >= k and (n - k) % 2 == 0:
            t = (n - k) // 2
            odd = C(t + k - 1, k - 1)

        return (total - odd) % MOD
