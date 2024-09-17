class Solution {
    public int maxPalindromes(String s, int k) {
        int count = 0;
        int idx = 0;
        
        // greedily check for palindromes of length k or k + 1
        while (idx <= s.length() - k) {
            if (isPalindrome(s, idx, idx + k - 1)) {
                count++;
                idx += k;
            } else if (idx < s.length() - k && isPalindrome(s, idx, idx + k)) {
                count++;
                idx += k + 1;
            } else {
                idx++;
            }
        }
        return count;
    }
    
    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}