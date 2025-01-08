class Solution {
    public int minSteps(int n) {
        int steps = 0;
        for (int i = 2; i <= n; i++) {
            while (n % i == 0) { // Factorize n
                steps += i;     // Add the factor to the steps
                n /= i;         // Reduce n
            }
        }
        return steps;
    }
}
