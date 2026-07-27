class Solution(object):
    def fullJustify(self, words, maxWidth):
        """
        :type words: List[str]
        :type maxWidth: int
        :rtype: List[str]
        """
        res = []
        i = 0
        n = len(words)

        while i < n:
            j = i
            line_len = 0

            while j < n and line_len + len(words[j]) + (j - i) <= maxWidth:
                line_len += len(words[j])
                j += 1

            num_words = j - i
            spaces = maxWidth - line_len

            # Last line or only one word
            if j == n or num_words == 1:
                line = " ".join(words[i:j])
                line += " " * (maxWidth - len(line))
            else:
                gaps = num_words - 1
                even = spaces // gaps
                extra = spaces % gaps

                line = ""
                for k in range(gaps):
                    line += words[i + k]
                    line += " " * (even + (1 if k < extra else 0))
                line += words[j - 1]

            res.append(line)
            i = j

        return res
