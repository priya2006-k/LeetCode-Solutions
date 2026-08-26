class Solution {
    public String shortestBeautifulSubstring(String s, int k) {

        String answer = "";

        for (int i = 0; i < s.length(); i++) {

            int ones = 0;

            for (int j = i; j < s.length(); j++) {

                if (s.charAt(j) == '1') {
                    ones++;
                }

                if (ones == k) {

                    String candidate = s.substring(i, j + 1);

                    if (answer.equals("") ||
                        candidate.length() < answer.length() ||
                        (candidate.length() == answer.length()
                         && candidate.compareTo(answer) < 0)) {

                        answer = candidate;
                    }

                    break;
                }
            }
        }

        return answer;
    }
}
