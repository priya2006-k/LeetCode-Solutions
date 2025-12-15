class Solution {
    public int numberOfWays(String corridor) {
        int mod = 1000000007;
        int seats = 0;
        long ways = 1;
        int plantsBetween = 0;
        boolean started = false;

        for (int i = 0; i < corridor.length(); i++) {
            char c = corridor.charAt(i);

            if (c == 'S') {
                seats++;
                if (seats == 2) {
                    started = true;
                } else if (seats > 2 && seats % 2 == 1) {
                    ways = (ways * (plantsBetween + 1)) % mod;
                    plantsBetween = 0;
                }
            } else {
                if (started && seats % 2 == 0) {
                    plantsBetween++;
                }
            }
        }

        if (seats == 0 || seats % 2 != 0) return 0;
        return (int) ways;
    }
}
