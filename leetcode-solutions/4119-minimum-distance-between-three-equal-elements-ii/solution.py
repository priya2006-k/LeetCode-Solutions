from collections import defaultdict

class Solution:
    def minimumDistance(self, nums):
        index_map = defaultdict(list)
        
        # Step 1: store indices
        for i, num in enumerate(nums):
            index_map[num].append(i)
        
        ans = float('inf')
        
        # Step 2: check triplets
        for indices in index_map.values():
            if len(indices) >= 3:
                for i in range(len(indices) - 2):
                    dist = 2 * (indices[i+2] - indices[i])
                    ans = min(ans, dist)
        
        return ans if ans != float('inf') else -1
