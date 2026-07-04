from collections import defaultdict, deque

class Solution:
    def minScore(self, n, roads):
        graph = defaultdict(list)

        # Build undirected graph
        for a, b, distance in roads:
            graph[a].append((b, distance))
            graph[b].append((a, distance))

        queue = deque([1])
        visited = set([1])
        min_score = float('inf')

        # BFS from city 1
        while queue:
            city = queue.popleft()

            for next_city, distance in graph[city]:
                min_score = min(min_score, distance)

                if next_city not in visited:
                    visited.add(next_city)
                    queue.append(next_city)

        return min_score
