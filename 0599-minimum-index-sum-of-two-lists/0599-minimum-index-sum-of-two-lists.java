class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        HashMap<String, Integer> map1 = new HashMap<>();
        HashMap<String, Integer> map2 = new HashMap<>();
        List<String> val = new ArrayList<>();
        int minIndexSum = Integer.MAX_VALUE;

        for (int i = 0; i < list1.length; i++) {
            map1.put(list1[i], i);
        }

        for (int j = 0; j < list2.length; j++) {
            map2.put(list2[j], j);

            if (map1.containsKey(list2[j])) {
                int currentIndexSum = j + map1.get(list2[j]);

                if (currentIndexSum < minIndexSum) {
                    minIndexSum = currentIndexSum;
                    val.clear();
                    val.add(list2[j]);
                } else if (currentIndexSum == minIndexSum) {
                    val.add(list2[j]);
                }
            }
        }

        // Convert the List<String> val to String[]
        return val.toArray(new String[0]);
    }
}