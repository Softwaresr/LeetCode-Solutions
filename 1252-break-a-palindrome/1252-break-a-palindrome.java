class Solution {
    public String breakPalindrome(String p) {
        if(p.length()==1) return ""; //yaha = nahi he return me
        String ans="";
        if(p.charAt(0)!='a'){
            return 'a'+p.substring(1);
        }
        
        
        
        for(int i=0;i<p.length()/2;i++){
            if(p.charAt(i)!='a'){
                return p.substring(0,i)+'a'+p.substring(i+1,p.length());

            }

        }
        return p.substring(0,p.length()-1)+'b';

    }
}