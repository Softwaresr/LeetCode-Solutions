class Solution {
    public String minWindow(String s, String t) {
        int[] tElements = new int[123];
        for(char c: t.toCharArray()){
            tElements[c]++;
        }

        int left = 0, right = 0;
        int find = t.length();
        char[] arr = s.toCharArray();

        int start = 0;
        int minLength = Integer.MAX_VALUE;

        while(right < arr.length){
            if(tElements[arr[right]] > 0) find--;
            tElements[arr[right++]]--;

            while(find == 0){
                if(right - left < minLength){
                    start = left;
                    minLength = right - left;
                }

                if(tElements[arr[left]] == 0) find++;
                tElements[arr[left]]++;
                left++;
            }
        }

        if(minLength == Integer.MAX_VALUE) return "";
        return s.substring(start, start+minLength);
    }
}