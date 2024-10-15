class Solution {
    public int maximumSetSize(int[] nums1, int[] nums2) {
        // first identify the number of distinct numbers in nums1.
        // then identify the number of distinct

        // max set size is when the two sets are completely disjoint -> create a set containing
        // all the second sets elements. Then, up until n/2 add from the first set if the other set cant

         HashSet<Integer> stor2 = new HashSet<>();         HashSet<Integer> stor1 = new HashSet<>();
         HashSet<Integer> container = new HashSet<>();
         int n = nums1.length;

         for (int num : nums1) {
            stor1.add(num);
         }


         for (int num : nums2) { 
            if (container.size() == n / 2) break;
            if (!stor1.contains(num)) container.add(num);
         }

        int first_size = container.size();
        if (first_size < n / 2 && stor1.size() > n / 2) {
            // maximized the distinct now add dupes
            for (int num : nums2) {
                if (container.size() == n / 2) break;
                if (!container.contains(num) && stor1.contains(num)) {
                    container.add(num);
                    stor1.remove(num);
                }
                }
            }
            return container.size() + Math.min(nums1.length / 2, stor1.size());
    }
        }
        