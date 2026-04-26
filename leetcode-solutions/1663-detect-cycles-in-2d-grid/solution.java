class Solution {

    public boolean solve(char[][] grid, int row, int col, int i, int j,
                         int[][] visited, char c,
                         int[] dx, int[] dy,
                         int count, int parentX, int parentY) {

        visited[i][j] = 1;

        for (int k = 0; k < 4; k++) {
            int newX = i + dx[k];
            int newY = j + dy[k];

            if (newX >= 0 && newX < row && newY >= 0 && newY < col) {

                if (grid[newX][newY] == c) {

                    if (visited[newX][newY] == 0) {
                        if (solve(grid, row, col, newX, newY,
                                  visited, c, dx, dy,
                                  count + 1, i, j)) {
                            return true;
                        }
                    }
                    else if (visited[newX][newY] == 1
                             && count > 2
                             && (newX != parentX || newY != parentY)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean containsCycle(char[][] grid) {

        int row = grid.length;
        int col = grid[0].length;

        int[][] visited = new int[row][col];

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, -1, 0, 1};

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                if (visited[i][j] == 0) {
                    if (solve(grid, row, col, i, j,
                              visited, grid[i][j],
                              dx, dy, 0, -1, -1)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
