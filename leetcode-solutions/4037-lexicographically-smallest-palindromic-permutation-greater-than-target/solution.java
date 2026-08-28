class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int[] c = new int[26];

        for (char ch : s.toCharArray())
            c[ch - 'a']++;

        int odd = 0, mid = -1;
        for (int i = 0; i < 26; i++) {
            if (c[i] % 2 == 1) {
                odd++;
                mid = i;
            }
        }

        if (odd > 1) return "";

        int n = s.length(), m = n / 2;
        int[] h = new int[26];

        for (int i = 0; i < 26; i++)
            h[i] = c[i] / 2;

        int[] r = h.clone();
        StringBuilder same = new StringBuilder();

        for (int i = 0; i < m; i++) {
            int x = target.charAt(i) - 'a';
            if (r[x] == 0) {
                same = null;
                break;
            }
            r[x]--;
            same.append((char)('a' + x));
        }

        if (same != null) {
            String ans = build(same, mid);
            if (ans.compareTo(target) > 0)
                return ans;
        }

        for (int p = m - 1; p >= 0; p--) {
            r = h.clone();

            boolean ok = true;

            for (int i = 0; i < p; i++) {
                int x = target.charAt(i) - 'a';
                if (r[x] == 0) {
                    ok = false;
                    break;
                }
                r[x]--;
            }

            if (!ok) continue;

            int x = target.charAt(p) - 'a';

            for (int y = x + 1; y < 26; y++) {
                if (r[y] == 0) continue;

                r[y]--;

                StringBuilder left = new StringBuilder();

                for (int i = 0; i < p; i++)
                    left.append(target.charAt(i));

                left.append((char)('a' + y));

                for (int j = 0; j < 26; j++)
                    while (r[j]-- > 0)
                        left.append((char)('a' + j));

                return build(left, mid);
            }
        }

        return "";
    }

    private String build(StringBuilder left, int mid) {
        String l = left.toString();
        return l + (mid == -1 ? "" : (char)('a' + mid))
                + new StringBuilder(l).reverse();
    }
}
