import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int startR = 0, startC = 0;
        List<int[]> litter = new ArrayList<>();
        int[][] litterIndex = new int[m][n];

        for (int[] row : litterIndex) {
            Arrays.fill(row, -1);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    startR = i;
                    startC = j;
                } else if (ch == 'L') {
                    litterIndex[i][j] = litter.size();
                    litter.add(new int[]{i, j});
                }
            }
        }

        int totalLitter = litter.size();

        if (totalLitter == 0) return 0;

        int fullMask = (1 << totalLitter) - 1;

        boolean[][][][] visited =
                new boolean[m][n][1 << totalLitter][energy + 1];

        Queue<int[]> queue = new ArrayDeque<>();

        // row, col, collectedMask, remainingEnergy, moves
        queue.offer(new int[]{startR, startC, 0, energy, 0});
        visited[startR][startC][0][energy] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            int r = cur[0];
            int c = cur[1];
            int mask = cur[2];
            int remainingEnergy = cur[3];
            int moves = cur[4];

            // If energy is 0, cannot move unless current cell is R.
            if (remainingEnergy == 0) {
                continue;
            }

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }

                char cell = classroom[nr].charAt(nc);

                if (cell == 'X') {
                    continue;
                }

                int newEnergy = remainingEnergy - 1;
                int newMask = mask;

                // Reset energy
                if (cell == 'R') {
                    newEnergy = energy;
                }

                // Collect litter
                if (litterIndex[nr][nc] != -1) {
                    newMask |= (1 << litterIndex[nr][nc]);
                }

                // All litter collected
                if (newMask == fullMask) {
                    return moves + 1;
                }

                if (!visited[nr][nc][newMask][newEnergy]) {
                    visited[nr][nc][newMask][newEnergy] = true;

                    queue.offer(new int[]{
                        nr, nc, newMask, newEnergy, moves + 1
                    });
                }
            }
        }

        return -1;
    }
}
