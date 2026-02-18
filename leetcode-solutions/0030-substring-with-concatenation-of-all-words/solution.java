class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || words.length == 0) return res;

        Map<String, Integer> map = new HashMap<>();
        for (String w : words)
            map.put(w, map.getOrDefault(w, 0) + 1);

        int len = words[0].length();
        int total = len * words.length;

        for (int i = 0; i < len; i++) {
            int left = i, count = 0;
            Map<String, Integer> seen = new HashMap<>();

            for (int right = i; right + len <= s.length(); right += len) {
                String word = s.substring(right, right + len);

                if (map.containsKey(word)) {
                    seen.put(word, seen.getOrDefault(word, 0) + 1);
                    count++;

                    while (seen.get(word) > map.get(word)) {
                        String leftWord = s.substring(left, left + len);
                        seen.put(leftWord, seen.get(leftWord) - 1);
                        left += len;
                        count--;
                    }

                    if (count == words.length)
                        res.add(left);
                } else {
                    seen.clear();
                    count = 0;
                    left = right + len;
                }
            }
        }
        return res;
    }
}

