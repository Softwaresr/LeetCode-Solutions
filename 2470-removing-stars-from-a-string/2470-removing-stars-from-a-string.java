class Solution {
    public String removeStars(String s) {
        
        char []arr=s.toCharArray();
        int n=arr.length;
        char []arr2=new char[n];
        int j = -1;
        for(int i=0 ; i<n;i++){
            if(arr[i]!='*'){
                j++;
                arr2[j]=arr[i];

            }
            else{
                j--;

            }

        }
        String s2="";
        for(int i=0;i<=j;i++){
            s2+=arr2[i];
        }
        return s2;

    }
}