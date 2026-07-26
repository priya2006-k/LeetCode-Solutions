class Solution(object):
    def isNumber(self, s):
        """
        :type s: str
        :rtype: bool
        """
        num = False
        dot = False
        exp = False

        for i, ch in enumerate(s):
            if ch.isdigit():
                num = True

            elif ch == '.':
                if dot or exp:
                    return False
                dot = True

            elif ch == 'e' or ch == 'E':
                if exp or not num:
                    return False
                exp = True
                num = False

            elif ch == '+' or ch == '-':
                if i != 0 and s[i - 1] != 'e' and s[i - 1] != 'E':
                    return False

            else:
                return False

        return num
