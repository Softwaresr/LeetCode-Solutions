class Solution {
    public int[] vowelStrings(String[] words, int[][] queries) {
        int acpt[] = new int[words.length];
        for(int i=0;i<words.length;i++) acpt[i] = check(words[i]);
        for(int i= 1; i<words.length;i++) acpt[i]+=acpt[i-1];
        int res[]=new int[queries.length];
        for(int i=0; i<queries.length;i++)
        res[i] = acpt[queries[i][1]] - (queries[i][0] == 0 ? 0 : acpt[queries[i][0] - 1]);
            
        
        return res;
       
    }
     int check(String s){
            String v = "aeiouAEIOU";
            return v.contains(s.charAt(0)+"")&& v.contains(s.charAt(s.length()-1)+"") ?1:0;
        }

}
