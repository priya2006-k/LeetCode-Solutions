class Solution:
    def closestTarget(self, words, target, startIndex):
        n = len(words)
        ans = float('inf')
        
        for i in range(n):
            if words[i] == target:
                dist = abs(i - startIndex)
                circular_dist = n - dist
                ans = min(ans, dist, circular_dist)
        
        return ans if ans != float('inf') else -1
