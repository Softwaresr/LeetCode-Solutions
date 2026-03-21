class Solution {
    public int findMinMoves(int[] machines) {
        int n = machines.length;
        int sum = 0;

        for (int num : machines) {
            sum += num;

        }

        // If equal distribution is impossible
        if (sum % n != 0) {
            return -1;
        }

        int avg = sum / n;
        int[] leftSums = new int[n];
        int[] rightSums = new int[n];

        // Caculate prefix sums
        for (int i = 1; i < n; i++) {
            leftSums[i] = leftSums[i - 1] + machines[i - 1];
        }

        for (int i = n - 2; i >= 0; i--) {
            rightSums[i] = rightSums[i + 1] + machines[i + 1];
        }


        int move = 0;


        // Calculate maximum imbalance
        for (int i = 0; i < n; i++) {
            int expLeft = i * avg;
            int expRight = (n - i - 1) * avg;


            int left = 0, right = 0;


            if (expLeft > leftSums[i]) {
                left = expLeft - leftSums[i];
            }


            if (expRight > rightSums[i]) {
                right = expRight - rightSums[i];
            }


            move = Math.max(move, left + right); 
        }


        return move;
        
    }
}