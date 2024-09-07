class Solution {
    public int countVowelStrings(int n) {
        int[] vals = new int[5];
        Arrays.fill(vals, 1);
        for(int i=0;i<n;i++){
            for(int j=0;j<5;j++){
                int sum = 0;

                for(int k=4;k>=j;k--)
                    sum += vals[k];
                
                vals[j] = sum;
            }
        }
        return vals[0];
    }
}