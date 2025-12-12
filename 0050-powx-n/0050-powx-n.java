class Solution {
    /**
     * Calculates x raised to the power n (x^n).
     * Uses the iterative Binary Exponentiation (Exponentiation by Squaring) method.
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public double myPow(double x, int n) {
        // --- 1. Handle Negative Exponent ---
        // x^-n = 1 / x^n. We update x and make the exponent positive.
        // We must use a long for the exponent to safely handle the case 
        // n = Integer.MIN_VALUE (-2^31), as its positive counterpart (2^31) 
        // overflows a standard 32-bit 'int'.
        long N = n; // Cast n to long

        if (N < 0) {
            x = 1 / x;
            N = -N;
        }

        // --- 2. Core Binary Exponentiation (Iterative) ---
        double result = 1.0;
        double currentX = x;
        
        // Loop while the exponent is greater than 0
        while (N > 0) {
            // Check if the current bit of N is 1 (i.e., N is odd)
            if (N % 2 == 1) {
                // If odd, multiply the result by the current power of x
                // This corresponds to a '1' bit in the binary representation of N
                result *= currentX;
            }
            
            // Square the base for the next iteration: x^2, x^4, x^8, ...
            currentX *= currentX;
            
            // Right-shift the exponent: N = N / 2 (integer division)
            N /= 2;
        }
        
        return result;
    }
}