class Solution {
    public int totalNumbers(int[] digits) {

        int[] count = new int[10];

        for (int digit : digits) {
            count[digit]++;
        }

        int answer = 0;

        for (int num = 100; num <= 999; num++) {


            if (num % 2 != 0) {
                continue;
            }

            int a = num / 100;          
            int b = (num / 10) % 10;   
            int c = num % 10;           

            count[a]--;
            count[b]--;
            count[c]--;

            if (count[a] >= 0 && count[b] >= 0 && count[c] >= 0) {
                answer++;
            }

            count[a]++;
            count[b]++;
            count[c]++;
        }

        return answer;
    }
}
