class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        HashMap<Integer, Integer> fmap = new HashMap<>();
        for (int val: arr){
            fmap.put(val, fmap.getOrDefault(val, 0)  + 1);

        }
        
       HashSet<Integer> unique = new HashSet<>();
       for(int freq: fmap.values()){
        if (unique.contains(freq)){
            return false;

        } 
        else{
            unique.add(freq);
        }

       }
       return true;
       
    }
}