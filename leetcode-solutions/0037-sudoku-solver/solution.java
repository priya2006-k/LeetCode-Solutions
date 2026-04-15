class Solution {
    public void solveSudoku(char[][] board) {
        // sets for fast lookup
        boolean[][] rows = new boolean[9][10];
        boolean[][] cols = new boolean[9][10];
        boolean[][] boxes = new boolean[9][10];

        // list of empty cells
        java.util.List<int[]> empty = new java.util.ArrayList<>();

        // initialize
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] == '.') {
                    empty.add(new int[]{r, c});
                } else {
                    int num = board[r][c] - '0';
                    rows[r][num] = true;
                    cols[c][num] = true;
                    boxes[(r / 3) * 3 + (c / 3)][num] = true;
                }
            }
        }

        backtrack(board, empty, 0, rows, cols, boxes);
    }

    private boolean backtrack(char[][] board, java.util.List<int[]> empty, int idx,
                              boolean[][] rows, boolean[][] cols, boolean[][] boxes) {
        
        if (idx == empty.size()) return true;

        int r = empty.get(idx)[0];
        int c = empty.get(idx)[1];
        int box = (r / 3) * 3 + (c / 3);

        for (int num = 1; num <= 9; num++) {
            if (!rows[r][num] && !cols[c][num] && !boxes[box][num]) {

                // place
                board[r][c] = (char)(num + '0');
                rows[r][num] = cols[c][num] = boxes[box][num] = true;

                if (backtrack(board, empty, idx + 1, rows, cols, boxes))
                    return true;

                // backtrack
                board[r][c] = '.';
                rows[r][num] = cols[c][num] = boxes[box][num] = false;
            }
        }
        return false;
    }
}
