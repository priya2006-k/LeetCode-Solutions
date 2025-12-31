class Solution {

    public int latestDayToCross(int r, int c, int[][] cells) {

        int l = 1, h = r * c, ans = 0;

        while (l <= h) {

            int m = (l + h) / 2;

            if (ok(m, r, c, cells)) {

                ans = m;

                l = m + 1;

            } else h = m - 1;

        }

        return ans;

    }

    boolean ok(int d, int r, int c, int[][] cells) {

        int[][] g = new int[r][c];

        for (int i = 0; i < d; i++) g[cells[i][0]-1][cells[i][1]-1] = 1;

        ArrayDeque<int[]> q = new ArrayDeque<>();

        boolean[][] v = new boolean[r][c];

        for (int j = 0; j < c; j++)

            if (g[0][j] == 0) { q.add(new int[]{0,j}); v[0][j] = true; }

        int[][] D = {{1,0},{-1,0},{0,1},{0,-1}};

        while (!q.isEmpty()) {

            int[] x = q.poll();

            if (x[0] == r - 1) return true;

            for (int[] dxy : D) {

                int nr = x[0] + dxy[0], nc = x[1] + dxy[1];

                if (nr>=0 && nr<r && nc>=0 && nc<c && !v[nr][nc] && g[nr][nc]==0) {

                    v[nr][nc] = true;

                    q.add(new int[]{nr,nc});

                }

            }

        }

        return false;

    }

}
