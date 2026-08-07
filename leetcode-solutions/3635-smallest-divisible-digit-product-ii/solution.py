class Solution:
    def smallestNumber(self, num: str, t: int) -> str:
        # Step 1: Factorize t into prime factors 2, 3, 5, 7
        temp = t
        required = {2: 0, 3: 0, 5: 0, 7: 0}
        for p in [2, 3, 5, 7]:
            while temp % p == 0:
                required[p] += 1
                temp //= p
        
        # If t has prime factors other than 2, 3, 5, 7, it's impossible to form with digits 1-9
        if temp > 1:
            return "-1"
            
        # Helper to calculate the absolute minimum number of digits needed to fulfill prime criteria
        def min_digits_needed(r2, r3, r5, r7):
            r2, r3, r5, r7 = max(0, r2), max(0, r3), max(0, r5), max(0, r7)
            
            cnt9 = r3 // 2
            r3 %= 2
            
            cnt8 = r2 // 3
            r2 %= 3
            
            cnt7 = r7
            cnt5 = r5
            
            cnt6 = cnt4 = cnt3 = cnt2 = 0
            if r2 == 2 and r3 == 1:
                cnt4, cnt3 = 1, 1
            elif r2 == 1 and r3 == 1:
                cnt6 = 1
            elif r2 == 2 and r3 == 0:
                cnt4 = 1
            elif r2 == 1 and r3 == 0:
                cnt2 = 1
            elif r2 == 0 and r3 == 1:
                cnt3 = 1
                
            return cnt2 + cnt3 + cnt4 + cnt5 + cnt6 + cnt7 + cnt8 + cnt9

        # Helper to construct the lexicographically smallest valid suffix of a fixed length
        def solve_suffix(rem_2, rem_3, rem_5, rem_7, length):
            res = []
            for i in range(length):
                rem_len = length - 1 - i
                for d in range(1, 10):
                    n2, n3, n5, n7 = rem_2, rem_3, rem_5, rem_7
                    if d == 2: n2 -= 1
                    elif d == 3: n3 -= 1
                    elif d == 4: n2 -= 2
                    elif d == 5: n5 -= 1
                    elif d == 6: n2 -= 1; n3 -= 1
                    elif d == 7: n7 -= 1
                    elif d == 8: n2 -= 3
                    elif d == 9: n3 -= 2
                    
                    if min_digits_needed(n2, n3, n5, n7) <= rem_len:
                        res.append(str(d))
                        rem_2, rem_3, rem_5, rem_7 = n2, n3, n5, n7
                        break
            return "".join(res)

        n = len(num)
        first_zero = num.find('0')
        limit = first_zero if first_zero != -1 else n
        
        # Calculate prime factors provided by prefix tracking
        prefix_factors = {2: 0, 3: 0, 5: 0, 7: 0}
        for i in range(limit):
            d = int(num[i])
            if d == 2: prefix_factors[2] += 1
            elif d == 3: prefix_factors[3] += 1
            elif d == 4: prefix_factors[2] += 2
            elif d == 5: prefix_factors[5] += 1
            elif d == 6: prefix_factors[2] += 1; prefix_factors[3] += 1
            elif d == 7: prefix_factors[7] += 1
            elif d == 8: prefix_factors[2] += 3
            elif d == 9: prefix_factors[3] += 2
                
        # Check if the original number is already zero-free and completely valid
        if first_zero == -1:
            if all(prefix_factors[p] >= required[p] for p in [2, 3, 5, 7]):
                return num

        # Step 2: Traverse backwards to mutate a digit to something larger
        for i in range(limit, -1, -1):
            if i < n:
                d_curr = int(num[i])
                if d_curr == 2: prefix_factors[2] -= 1
                elif d_curr == 3: prefix_factors[3] -= 1
                elif d_curr == 4: prefix_factors[2] -= 2
                elif d_curr == 5: prefix_factors[5] -= 1
                elif d_curr == 6: prefix_factors[2] -= 1; prefix_factors[3] -= 1
                elif d_curr == 7: prefix_factors[7] -= 1
                elif d_curr == 8: prefix_factors[2] -= 3
                elif d_curr == 9: prefix_factors[3] -= 2
            
            start_digit = int(num[i]) + 1 if i < n else 1
            if i == first_zero:
                start_digit = 1
                
            for d in range(start_digit, 10):
                n2, n3, n5, n7 = required[2] - prefix_factors[2], required[3] - prefix_factors[3], required[5] - prefix_factors[5], required[7] - prefix_factors[7]
                if d == 2: n2 -= 1
                elif d == 3: n3 -= 1
                elif d == 4: n2 -= 2
                elif d == 5: n5 -= 1
                elif d == 6: n2 -= 1; n3 -= 1
                elif d == 7: n7 -= 1
                elif d == 8: n2 -= 3
                elif d == 9: n3 -= 2
                
                rem_space = n - 1 - i
                if min_digits_needed(n2, n3, n5, n7) <= rem_space:
                    return num[:i] + str(d) + solve_suffix(n2, n3, n5, n7, rem_space)
                
            if i == 0:
                break

        # Step 3: Increase length of the number if same-length is mathematically impossible
        total_needed = min_digits_needed(required[2], required[3], required[5], required[7])
        target_len = max(n + 1, total_needed)
        return solve_suffix(required[2], required[3], required[5], required[7], target_len)
        
