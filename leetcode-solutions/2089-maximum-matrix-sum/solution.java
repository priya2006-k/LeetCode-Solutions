class Solution {
    public long maxMatrixSum(int[][] matrix) {
        int n = matrix.length;
        long sum = 0;
        int negatives = 0;
        int minAbs = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int v = matrix[i][j];
                if (v < 0) negatives++;
                int abs = Math.abs(v);
                sum += abs;
                minAbs = Math.min(minAbs, abs);
            }
        }

        if (negatives % 2 == 0) return sum;
        return sum - 2L * minAbs;
    }
}

