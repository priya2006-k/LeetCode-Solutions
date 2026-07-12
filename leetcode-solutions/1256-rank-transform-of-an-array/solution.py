class Solution(object):
    def arrayRankTransform(self, arr):
        """
        :type arr: List[int]
        :rtype: List[int]
        """
        rank = {}
        current = 1

        for num in sorted(set(arr)):
            rank[num] = current
            current += 1

        return [rank[num] for num in arr]
