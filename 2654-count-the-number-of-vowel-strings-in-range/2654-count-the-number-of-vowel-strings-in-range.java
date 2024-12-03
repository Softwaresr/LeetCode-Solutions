class Solution {
    public int vowelStrings(String[] words, int left, int right) {
        int ans=0;
        for(int i=left;i<=right;i++){
            if(isVowel(words[i].charAt(0)) && isVowel(words[i].charAt(words[i].length()-1))){
                //uper wali line me words[i].charAt.words[i].lenght()-1 he
                ans++;
            }
        }

        return ans;
        
    }
    public boolean isVowel(char ch){
        if(ch=='a' || ch=='i' || ch=='o'||ch=='e' || ch=='u'){ 
            //idhar == he
            return true;
        }
        return false;
    }
}
