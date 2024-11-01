class Solution {
    public int[] secondGreaterElement(int[] nums) {
        int[] ans = new int[nums.length];
        Arrays.fill(ans, -1);
        LinkedList<Integer> s1 = new LinkedList<>();
        LinkedList<Integer> s2 = new LinkedList<>();
        for (int i=0; i < nums.length; i++) {
            while(!s2.isEmpty() && nums[i] > nums[s2.getLast()]) {
                ans[s2.removeLast()] = nums[i];
            }
            LinkedList temp = new LinkedList<>();
            while(!s1.isEmpty() && nums[i] > nums[s1.getLast()]) {
                temp.addFirst(s1.removeLast());
            }
            s2.addAll(temp);
            s1.addLast(i);
            // System.out.println(s1);
            // System.out.println(s2);
         // System.out.println();
        }
        return ans;
    }
}