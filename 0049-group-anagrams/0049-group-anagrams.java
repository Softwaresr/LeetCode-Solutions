import java.util.AbstractList;
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        return new AbstractList<List<String>>() {
            List<List<String>> result;
            public List<String> get(int index) {
                if (result == null) {
                    init();
                }

                return result.get(index);
            }
            public int size() {
                if (result == null) {
                    init();
                }
                return result.size();
            }
            private void init() {
                for (String s: strs) {
                    char[] keys = new char[26];
                    for (int i = 0; i < s.length(); i++) {
                        keys[s.charAt(i) - 'a']++;
                    }
                    String key = new String(keys);
                    List<String> list = map.get(key);
                    if (list == null) {
                        map.put(key, new ArrayList<>());
                    }
                    map.get(key).add(s);
                }
                result = new ArrayList<>(map.values());
            }
        };
    }
}