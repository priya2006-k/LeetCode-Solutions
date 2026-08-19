class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        HashMap<Integer, List<Integer>> map = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            if (!map.containsKey(row)) {
                map.put(row, new ArrayList<>());
            }

            map.get(row).add(col);
        }

        int answer = 2 * n;

        for (int row : map.keySet()) {

            List<Integer> seats = map.get(row);

            boolean left = true;
            boolean middle = true;
            boolean right = true;

            for (int seat : seats) {

                if (seat >= 2 && seat <= 5) {
                    left = false;
                }

                if (seat >= 4 && seat <= 7) {
                    middle = false;
                }

                if (seat >= 6 && seat <= 9) {
                    right = false;
                }
            }

            if (left && right) {

            } else if (left || middle || right) {

                answer -= 1;
            } else {
            
                answer -= 2;
            }
        }

        return answer;
    }
}
