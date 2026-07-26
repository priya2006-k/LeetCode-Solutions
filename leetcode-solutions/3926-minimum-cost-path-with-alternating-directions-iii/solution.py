import heapq

class Solution(object):
    def minCost(self, m, n, penalty):
        INF = 10 ** 30

        dist = [[[INF] * 2 for _ in range(n)] for _ in range(m)]
        dist[0][0][0] = 1

        pq = [(1, 0, 0, 0)]

        directions = [
            (1, 0),
            (-1, 0),
            (0, 1),
            (0, -1)
        ]

        while pq:
            cost, r, c, parity = heapq.heappop(pq)

            if cost != dist[r][c][parity]:
                continue

            if r == m - 1 and c == n - 1:
                return cost

            # Wait
            newCost = cost + penalty[r][c]
            if newCost < dist[r][c][parity ^ 1]:
                dist[r][c][parity ^ 1] = newCost
                heapq.heappush(pq, (newCost, r, c, parity ^ 1))

            # Move
            for dr, dc in directions:
                nr = r + dr
                nc = c + dc

                if nr < 0 or nr >= m or nc < 0 or nc >= n:
                    continue

                enter = (nr + 1) * (nc + 1)
                extra = 0

                if parity == 0:
                    if not (dr == 1 or dc == 1):
                        extra = penalty[r][c]
                else:
                    if not (dr == -1 or dc == -1):
                        extra = penalty[r][c]

                newCost = cost + enter + extra

                if newCost < dist[nr][nc][parity ^ 1]:
                    dist[nr][nc][parity ^ 1] = newCost
                    heapq.heappush(
                        pq,
                        (newCost, nr, nc, parity ^ 1)
                    )

        return -1
