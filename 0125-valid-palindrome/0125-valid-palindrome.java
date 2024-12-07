class Solution {
    public boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        
        while (left < right) {
            // Move left pointer to the next alphanumeric character
            if (!Character.isLetterOrDigit(s.charAt(left))) {
                left++;
                continue;
            }
            // Move right pointer to the previous alphanumeric character
            if (!Character.isLetterOrDigit(s.charAt(right))) {
                right--;
                continue;
            }
            
            // Compare characters in a case-insensitive manner
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            
            // Move both pointers inward
            left++;
            right--;
        }
        
        return true;
    }
}
