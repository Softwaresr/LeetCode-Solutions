class Solution:
    def convertDateToBinary(self, date: str) -> str:
        # Split the input date by '-'
        year, month, day = date.split('-')
        
        # Convert year, month, and day to their binary representations without leading '0b'
        year_bin = bin(int(year))[2:]
        month_bin = bin(int(month))[2:]
        day_bin = bin(int(day))[2:]
        
        # Concatenate the binary representations with dashes
        return f"{year_bin}-{month_bin}-{day_bin}"

# Example usage
solution = Solution()

# Example 1
date1 = "2080-02-29"
print(solution.convertDateToBinary(date1))  # Output: "100000100000-10-11101"

# Example 2
date2 = "1900-01-01"
print(solution.convertDateToBinary(date2))  # Output: "11101101100-1-1"
