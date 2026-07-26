import java.util.*;

class Solution {
    public List<List<Integer>> aggregateTimeSeries(int[][] series1, int[][] series2) {
        TreeSet<Integer> times = new TreeSet<>();

        for (int[] p : series1) times.add(p[0]);
        for (int[] p : series2) times.add(p[0]);

        int i = 0, j = 0;
        int n = series1.length, m = series2.length;

        List<List<Integer>> ans = new ArrayList<>();

        for (int t : times) {

            while (i < n && series1[i][0] < t) i++;
            while (j < m && series2[j][0] < t) j++;

            int v1 = (i < n) ? series1[i][1] : 0;
            int v2 = (j < m) ? series2[j][1] : 0;

            ans.add(Arrays.asList(t, v1 + v2));
        }

        return ans;
    }
}
