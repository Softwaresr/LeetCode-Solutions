// class Solution {
//     private static final int MOD = 1000000007;

//     private int check_all_records(int cur_ind, int count_a, int count_l, int n, int[][][] temp) {
//         if (cur_ind == n) {
//             return 1;
//         }
//         if (temp[cur_ind][count_a][count_l] != -1) {
//             return temp[cur_ind][count_a][count_l];
//         }
//         int with_a_next = (count_a == 0) ? check_all_records(cur_ind + 1, count_a + 1, 0, n, temp) : 0;
//         int with_l_next = (count_l == 2) ? 0 : check_all_records(cur_ind + 1, count_a, count_l + 1, n, temp);
//         int with_p_next = check_all_records(cur_ind + 1, count_a, 0, n, temp);
//         int total = ((with_a_next + with_l_next) % MOD + with_p_next) % MOD;

//         temp[cur_ind][count_a][count_l] = total;
//         return total;
//     }

//     public int checkRecord(int n) {
//         int[][][] temp = new int[n][2][3];
//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < 2; j++) {
//                 for (int k = 0; k < 3; k++) {
//                     temp[i][j][k] = -1;
//                 }
//             }
//         }
//         return check_all_records(0, 0, 0, n, temp);
//     }
// }


// class Solution {
//     private static final int MOD = 1000000007;

//     public int checkRecord(int n) {
//         // Initialize dp arrays
//         int[][] dp_last = new int[2][3]; // previous state
//         int[][] dp_current = new int[2][3]; // current state

//         dp_last[0][0] = 1; // empty string

//         for (int i = 0; i < n; i++) {
//             for (int count_a = 0; count_a < 2; count_a++) {
//                 for (int count_l = 0; count_l < 3; count_l++) {
//                     // choose "P"
//                     dp_current[count_a][0] = (dp_current[count_a][0] + dp_last[count_a][count_l]) % MOD;
//                     // choose "A"
//                     if (count_a == 0) {
//                         dp_current[count_a + 1][0] = (dp_current[count_a + 1][0] + dp_last[count_a][count_l]) % MOD;
//                     }
//                     // Choose "L"
//                     if (count_l < 2) {
//                         dp_current[count_a][count_l + 1] = (dp_current[count_a][count_l + 1] + dp_last[count_a][count_l]) % MOD;
//                     }
//                 }
//             }
//             dp_last = dp_current; // Reference current to previous
//             dp_current = new int[2][3]; // make new current
//         }

//         // Sum up the counts for all combinations of length 'n' with different count_a and count_l.
//         int res = 0;
//         for (int count_a = 0; count_a < 2; count_a++) {
//             for (int count_l = 0; count_l < 3; count_l++) {
//                 res = (res + dp_last[count_a][count_l]) % MOD;
//             }
//         }
//         return res;
//     }
// }

class Solution {
    int m = 1000000007;

    public int checkRecord(int n) {
        long[] dp = helper(n);
        long records = 0;
        for (long r : dp) {
            records += r;
        }
        return (int) (records % m);
    }

    public long[] helper(int n) {

        if (n < 8) {
            long[] dp = new long[] { 1, 1, 0, 1, 0, 0 };
            while (--n > 0) {
                step(dp);
            }
            return dp;
        }
        long[] second = helper(n / 2 - 3);
        long[] first = second.clone();
        step(first);
        step(first);
        step(first);
        long[] dp = new long[6];
        for (int i = 0; i < 3; i++) {
            long t = 0, s = 0;
            for (int j = 0; j <= i; j++) {
                t += first[j];
                s += first[j + 3];
            }
            t %= m;
            // the ...P case: first... (L)P second
            for (int j = 0; j < 6; j++) {
                dp[j] += t * second[j];
                dp[j] %= m;
            }
            // the ...A case: first... (L)A second
            for (int j = 3; j < 6; j++) {
                dp[j] += t * second[j - 3];
                dp[j] %= m;
            }
            // case where A is in the first half
            for (int j = 3; j < 6; j++) {
                dp[j] += s * second[j - 3];
                dp[j] %= m;
            }
            step(second);
        }
        if (n % 2 != 0) {
            step(dp);
        }
        return dp;
    }

    public void step(long[] dp) {
        long t0 = (dp[0] + dp[1] + dp[2]) % m;
        long t3 = (t0 + dp[3] + dp[4] + dp[5]) % m;
        dp[2] = dp[1];
        dp[1] = dp[0];
        dp[5] = dp[4];
        dp[4] = dp[3];
        dp[3] = t3;
        dp[0] = t0;
    }
}