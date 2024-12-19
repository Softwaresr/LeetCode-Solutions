class Solution {
    final long MOD = (long)(1e9+7);
    public int countPalindromes(String s) {
        char[] S = s.toCharArray();
        //maps 1 digit number to how many times it occurs
        long[] prefarr = new long[10];

        //maps 2 digit number to how many times that subsequence occurs
        long[][] prefgrid = new long[s.length()][100];

        for (int i = 0; i < S.length; i++) {
            int curr = charint(S[i]);

            //updating pref arrays
            if (i > 0) {
                for (int j = 0; j < prefgrid[0].length; j++) {
                    prefgrid[i][j] = prefgrid[i-1][j];
                }
            }

            for (int digit = 0; digit <= 9; digit++) {
                int combine = digit*10 + curr;
                prefgrid[i][combine] += prefarr[digit] * 1;
            }

            prefarr[curr]++;
        }

        //now looping backwards
        //maps 1 digit number to how many times it occurs
        long[] sufarr = new long[10];

        //maps 2 digit number to how many times that subsequence occurs
        long[][] sufgrid = new long[s.length()][100];

        long ret = 0;

        for (int i = S.length - 1; i >= 0; i--) {
            //updating suff arrays
            int curr = charint(S[i]);
            if (i < S.length - 1) {
                for (int j = 0; j < sufgrid[0].length; j++) {
                    sufgrid[i][j] = sufgrid[i+1][j];
                }
            }

            for (int digit = 0; digit <= 9; digit++) {
                int combine = digit*10 + curr;
                sufgrid[i][combine] += sufarr[digit] * 1;
            }

            //now letting i be midpoint
                //can reduce constant factor here
            if (i > 1 && i < S.length - 2) {

                for (int twodigits = 0; twodigits <= 99; twodigits++) {
                    ret += prefgrid[i-1][twodigits] * sufgrid[i+1][twodigits];
                    ret %= MOD;
                }
            }



            sufarr[curr]++;
        }

        return (int)(ret%MOD);
    }
    //converts character into integer
    private int charint(char c) {
        return Integer.parseInt(String.valueOf(c));
    }
}