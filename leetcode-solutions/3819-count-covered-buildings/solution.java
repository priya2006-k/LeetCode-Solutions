import java.util.*;

class Solution {
    public int countCoveredBuildings(int n, int[][] buildings) {
        HashMap<Integer,Integer> rowMin = new HashMap<>(), rowMax = new HashMap<>();
        HashMap<Integer,Integer> colMin = new HashMap<>(), colMax = new HashMap<>();

        for(int[] b : buildings){
            int x = b[0], y = b[1];
            rowMin.put(x, Math.min(rowMin.getOrDefault(x, y), y));
            rowMax.put(x, Math.max(rowMax.getOrDefault(x, y), y));
            colMin.put(y, Math.min(colMin.getOrDefault(y, x), x));
            colMax.put(y, Math.max(colMax.getOrDefault(y, x), x));
        }

        int count = 0;
        for(int[] b : buildings){
            int x = b[0], y = b[1];
            if(rowMin.get(x) < y && y < rowMax.get(x) && colMin.get(y) < x && x < colMax.get(y)) {
                count++;
            }
        }
        return count;
    }
}
