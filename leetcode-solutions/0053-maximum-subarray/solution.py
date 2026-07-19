class Solution(object):
    def maxSubArray(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        current = maximum = nums[0]

        for i in range(1, len(nums)):
            current = max(nums[i], current + nums[i])
            maximum = max(maximum, current)

        return maximum
