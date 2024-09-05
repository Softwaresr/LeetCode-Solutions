class Solution:
    def longestBeautifulSubstring(self, word: str) -> int:
        vowels = ['a', 'e', 'i', 'o', 'u']
        i = 0
        curr_idx = 0
        count = 0
        ans = 0
        while i < len(word):
            if word[i] == vowels[curr_idx]:
                count += 1
            elif (i > 0 
                and word[i - 1] == vowels[curr_idx]
                and curr_idx < len(vowels) - 1 
                and word[i] == vowels[curr_idx + 1]):
                curr_idx += 1
                count += 1
            else:
                if curr_idx == len(vowels) - 1:
                    ans = max(ans, count)
                curr_idx = 0
                count = 0
                if word[i] == vowels[curr_idx]:
                    count += 1
            i += 1

        if curr_idx == len(vowels) - 1:
            ans = max(ans, count)
        
        return ans


            
        