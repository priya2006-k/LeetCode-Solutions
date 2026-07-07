class Solution:
    def sumAndMultiply(self, n):
        non_zero = ""

        for ch in str(n):
            if ch != '0':
                non_zero += ch

        if non_zero == "":
            return 0

        x = int(non_zero)
        digit_sum = 0

        for ch in non_zero:
            digit_sum += int(ch)

        return x * digit_sum
