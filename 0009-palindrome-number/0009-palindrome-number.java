class Solution {
    public boolean isPalindrome(int x) {
        
        if(x<0){
            return false;
        }
        int n = x;
        int r = 0;
        while(n>0){
            int d = n%10;
            r = r*10 + d;
            n = n/10;

        }
        if(r==x){
            return true;

        }
        else{
            return false;
        }
    }
}