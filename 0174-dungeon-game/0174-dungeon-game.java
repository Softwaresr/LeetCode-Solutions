class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int[][] cache = new int[dungeon.length][dungeon[0].length];

        for(int[] c:cache)
            Arrays.fill(c,-1);
        return function(0,0,dungeon,cache);
    }

    int function(int i,int j,int[][] mat,int[][] cache){

        if(i>=mat.length || j>=mat[0].length) return Integer.MAX_VALUE;

        if(cache[i][j] !=-1) return cache[i][j];

        if(i==mat.length-1 && j==mat[0].length-1){
            //  1 - any +ve = -ve
            // 1 - any -ve = +ve;
            return cache[i][j]= Math.max(1,1-mat[i][j]);
        }

        

        int right = function(i,j+1,mat,cache);
        int down = function(i+1,j,mat,cache);

        int minEnergy = Math.min(right,down);

        //decide energey level
        // when current has sufficent energy to manage prev damage, 
        //it will become-ve and 1 will be returned
        return cache[i][j]=  Math.max(1,minEnergy-mat[i][j]);//energey required at curerent level

    }
}