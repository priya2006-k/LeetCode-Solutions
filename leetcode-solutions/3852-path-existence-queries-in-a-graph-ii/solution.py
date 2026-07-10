class Solution(object):
    def pathExistenceQueries(self, n, nums, maxDiff, queries):
        """
        :type n: int
        :type nums: List[int]
        :type maxDiff: int
        :type queries: List[List[int]]
        :rtype: List[int]
        """

        order = sorted(range(n), key=lambda i: nums[i])

        arr = [0] * n
        pos = [0] * n

        for i in range(n):
            arr[i] = nums[order[i]]
            pos[order[i]] = i

        component = [0] * n

        for i in range(1, n):
            component[i] = component[i - 1]

            if arr[i] - arr[i - 1] > maxDiff:
                component[i] += 1

        nxt = [0] * n
        right = 0

        for i in range(n):
            if right < i:
                right = i

            while right + 1 < n and arr[right + 1] - arr[i] <= maxDiff:
                right += 1

            nxt[i] = right

        LOG = n.bit_length()
        jump = [[0] * n for _ in range(LOG)]

        jump[0] = nxt[:]

        for k in range(1, LOG):
            for i in range(n):
                jump[k][i] = jump[k - 1][jump[k - 1][i]]

        answer = []

        for u, v in queries:
            if u == v:
                answer.append(0)
                continue

            left = pos[u]
            target = pos[v]

            if left > target:
                left, target = target, left

            if component[left] != component[target]:
                answer.append(-1)
                continue

            current = left
            steps = 0

            for k in range(LOG - 1, -1, -1):
                if jump[k][current] < target:
                    current = jump[k][current]
                    steps += 1 << k

            answer.append(steps + 1)

        return answer
