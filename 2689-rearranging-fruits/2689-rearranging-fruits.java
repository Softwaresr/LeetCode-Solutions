class Solution {
    public long minCost(int[] basket1, int[] basket2) {
        // basket1 = [4,2,2,2], basket2 = [1,4,1,2]
        /**
            2 -> 3
            4 -> 1

            1 -> 2
            2 -> 1
            4 -> 1
         */

        Arrays.sort(basket1);
        Arrays.sort(basket2);
       

        int i = 0;
        int j = 0;
        int n = basket1.length;
        int movesA = 0;
        int movesB = 0;
        long finalAns = 0;
        List<Integer> list = new ArrayList<>();
        while(i < n && j < n) {
            int count1 = 0;
            int count2 = 0;
            int currEle1 = basket1[i];
            int currEle2 = basket2[j];

            if(currEle1 < currEle2) {
                while(i < n && currEle1 == basket1[i]) {
                    i++;
                    count1++;
                }
            } else if(currEle2 < currEle1) {
                while(j < n && currEle2 == basket2[j]) {
                    j++;
                    count2++;
                }
            } else {
                while(i < n && j < n && basket1[i] == basket2[j]) {
                    i++;
                    j++;
                }
            }

            if(currEle1 != currEle2) {
                if(count1 % 2 != 0 || count2 % 2 != 0) {
                    return -1;
                } else {
                    if(currEle1 < currEle2) {
                        movesB = movesB + count1 / 2;
                        finalAns += currEle1 * (count1 / 2);
                        for(int k = 0; k < count1 / 2; k++) {
                            list.add(currEle1);
                        }
                        // System.out.println("ff1: " + finalAns);
                    } else if(currEle2 < currEle1) {
                        movesA = movesA + count2 / 2;
                        finalAns += currEle2 * (count2 / 2);
                        for(int k = 0; k < count2 / 2; k++) {
                            list.add(currEle2);
                        }
                        // System.out.println("ff2: " + finalAns);
                    }
                }
            } 
        }
        if(i < n) {
            int currEle1 = basket1[i];
            int count1 = n-i;
            if(count1 % 2 != 0) {
                return -1;
            }
            movesB = movesB + count1 / 2;
            // finalAns += currEle1 * (count1 / 2);
        }
        if(j < n) {
            int currEle2 = basket2[j];
            int count2 = n-j;
            if(count2 % 2 != 0) {
                return -1;
            }
            movesA = movesA + count2 / 2;
            // finalAns += currEle2 * (count2 / 2);
        }
        // System.out.println("i: " + i + ", j: " + j);
        // System.out.println("movesA: " + movesA + ", movesB: " + movesB);
        if(movesA != movesB) {
            return -1;
        }
        long ans = 0;
        
        for(int k = 0; k < movesA; k++) {
            ans += Math.min((long)Math.min(basket1[0], basket2[0]) * 2, list.get(k));
        }
        return ans;
    }
}