class Solution {
    public int sumFourDivisors(int[] nums) {
        int result = 0;

        for (int n : nums) {
            int sum = 0;
            int count = 0;

            for (int i = 1; i * i <= n; i++) {
                if (n % i == 0) {
                    int j = n / i;

                    if (i == j) {
                        count += 1;
                        sum += i;
                    } else {
                        count += 2;
                        sum += i + j;
                    }

                    if (count > 4) break;
                }
            }

            if (count == 4) {
                result += sum;
            }
        }

        return result;
    }
}
