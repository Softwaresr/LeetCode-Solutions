class Solution {
    public String shortestPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        build(sb, s.length(), s.toCharArray());
        return sb.toString();
    }
    public void build(StringBuilder sb, int end, char[] chars) {

        int index = 0;
        for (int i = end - 1; i >= 0; i--) {
            if (chars[index] == chars[i]) index++;
        }
        if (index == end) {
            sb.append(chars, 0, end);
            return;
        }
        for (int i = end - 1; i >= index; i--) {
            sb.append(chars[i]);
        }

        build(sb, index, chars);
        sb.append(chars, index, end - index);
    }
}