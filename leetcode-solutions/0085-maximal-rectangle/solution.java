import java.util.*;

class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;

        int rows = matrix.length, cols = matrix[0].length;
        int[] heights = new int[cols];
        int maxArea = 0;

        for (int r = 0; r < rows; r++) {
            // Build histogram heights row by row
            for (int c = 0; c < cols; c++) {
                if (matrix[r][c] == '1') heights[c] += 1;
                else heights[c] = 0;
            }
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }

        return maxArea;
    }

    private int largestRectangleArea(int[] heights) {
        Stack<Integer> st = new Stack<>();
        int max = 0;
        int n = heights.length;

        for (int i = 0; i <= n; i++) {
            int currHeight = (i == n) ? 0 : heights[i];

            while (!st.isEmpty() && currHeight < heights[st.peek()]) {
                int h = heights[st.pop()];
                int right = i;
                int left = st.isEmpty() ? -1 : st.peek();
                int width = right - left - 1;
                max = Math.max(max, h * width);
            }
            st.push(i);
        }

        return max;
    }
}
