class Solution:
    def multiply(self, num1, num2):
        if num1 == "0" or num2 == "0":
            return "0"

        m = len(num1)
        n = len(num2)

        result = [0] * (m + n)

        for i in range(m - 1, -1, -1):
            for j in range(n - 1, -1, -1):
                multiplication = int(num1[i]) * int(num2[j])

                position1 = i + j
                position2 = i + j + 1

                total = multiplication + result[position2]

                result[position2] = total % 10
                result[position1] += total // 10

        # Convert result array to string
        answer = ''.join(map(str, result))

        # Remove leading zeros
        return answer.lstrip('0')
