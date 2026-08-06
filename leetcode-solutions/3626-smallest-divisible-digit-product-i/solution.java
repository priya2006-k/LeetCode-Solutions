class Solution {
    public int smallestNumber(int n, int t) {
        while (true) {
            if (isValid(n, t)) {
                return n;
            }
            n++;
        }
    }

    private boolean isValid(int num, int t) {
        int product = 1;
        int x = num;

        while (x > 0) {
            product *= (x % 10);
            x /= 10;
        }

        return product % t == 0;
    }
}
