class Solution {
    public double myPow(double x, int n) {
        long p = n;
        if (p < 0) {
            x = 1 / x;
            p = -p;
        }

        double ans = 1.0;

        while (p > 0) {
            if ((p & 1) == 1) {     // if odd
                ans *= x;
            }
            x *= x;                 // square base
            p >>= 1;                // divide exponent by 2
        }

        return ans;
    }
}
