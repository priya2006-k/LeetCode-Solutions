class Solution {
    private String s;
    private int i;

    public List<String> braceExpansionII(String expression) {
        s = expression;
        i = 0;
        Set<String> set = parse();
        List<String> ans = new ArrayList<>(set);
        Collections.sort(ans);
        return ans;
    }

    private Set<String> parse() {
        Set<String> res = new HashSet<>();
        Set<String> cur = new HashSet<>();
        cur.add("");

        while (i < s.length() && s.charAt(i) != '}') {
            char c = s.charAt(i);

            if (c == ',') {
                res.addAll(cur);
                cur = new HashSet<>();
                cur.add("");
                i++;
            } else {
                Set<String> part;

                if (c == '{') {
                    i++;
                    part = parse();
                    i++;
                } else {
                    part = new HashSet<>();
                    part.add(String.valueOf(c));
                    i++;
                }

                Set<String> next = new HashSet<>();
                for (String a : cur) {
                    for (String b : part) {
                        next.add(a + b);
                    }
                }
                cur = next;
            }
        }

        res.addAll(cur);
        return res;
    }
}
