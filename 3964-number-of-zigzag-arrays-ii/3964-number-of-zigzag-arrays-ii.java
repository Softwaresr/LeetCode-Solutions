class Solution {
    public int zigZagArrays(int n, int l, int r) {
        final int MOD = 1000000007;
        long m = (long) r - l + 1;
        if (m <= 0) return 0;
        if (n == 1) return (int) (m % MOD);
        if (m == 1) return 0;
        int d = (int) m;

        // Use matrix exponentiation
        long[][] M = new long[2 * d][2 * d];
        for (int i = 0; i < d; i++) {
            for (int j = 0; j < d; j++) {
                if (i != j) {
                    if (j > i) {
                        M[i][j + d] = 1; // up to down
                    }
                    if (j < i) {
                        M[i + d][j] = 1; // down to up
                    }
                }
            }
        }

        // Compute matrix power
        long[][] res = matrixPower(M, n - 1, 2 * d, MOD);

        // Initialize base case for length 1
        long[] dp = new long[2 * d];
        for (int i = 0; i < d; i++) {
            dp[i] = 1; // up
            dp[i + d] = 1; // down
        }

        // Multiply matrix by initial vector
        long[] result = new long[2 * d];
        for (int i = 0; i < 2 * d; i++) {
            for (int j = 0; j < 2 * d; j++) {
                result[i] = (result[i] + res[i][j] * dp[j]) % MOD;
            }
        }

        // Sum all possibilities
        long ans = 0;
        for (int i = 0; i < 2 * d; i++) {
            ans = (ans + result[i]) % MOD;
        }

        return (int) ans;
    }

    private long[][] matrixPower(long[][] M, long n, int size, int MOD) {
        if (n == 0) {
            long[][] res = new long[size][size];
            for (int i = 0; i < size; i++) {
                res[i][i] = 1;
            }
            return res;
        }
        if (n == 1) {
            return M;
        }
        long[][] half = matrixPower(M, n / 2, size, MOD);
        long[][] res = matrixMultiply(half, half, size, MOD);
        if (n % 2 == 1) {
            res = matrixMultiply(res, M, size, MOD);
        }
        return res;
    }

    private long[][] matrixMultiply(long[][] A, long[][] B, int size, int MOD) {
        long[][] res = new long[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    res[i][j] = (res[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }
        return res;
    }
}