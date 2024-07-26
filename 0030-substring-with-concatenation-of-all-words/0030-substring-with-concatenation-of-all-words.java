import java.util.*;

class Solution {
 	List<Integer> solvedResult = null;

	HashMap<String, Integer> map = new HashMap<String, Integer>();
	HashMap<String, Integer> cloneMap = new HashMap<String, Integer>();

	public List<Integer> findSubstring(String s, String[] words) {
		return new AbstractList<>() {
			public Integer get(int index) {
				if (solvedResult == null) {
					solvedResult = solve(s, words);
				}
				return solvedResult.get(index);
			}

			public int size() {
				if (solvedResult == null) {
					solvedResult = solve(s, words);
				}
				return solvedResult.size();
			}
		};
	}

	private LinkedList<Integer> solve(String s, String[] words) {
		var res = new LinkedList<Integer>();

		int i = 0, j = 0, n = words.length;
		int wordSize = words[0].length();
		int totalConcatLenth = wordSize * n;

		for(int k = 0; k < words.length; k++) {
			map.put(words[k], map.getOrDefault(words[k], 0) + 1);
		}
		cloneMap = new HashMap<>(map);
		loop: while (i <= s.length() - totalConcatLenth) {
			if (j != 0) {
				cloneMap = new HashMap<>(map);
			}
			for (j = 0; j < n; j++) {
				if (!popWord(s.substring(i + (wordSize * j), i + (wordSize * (j + 1))))) {
					i++;
					continue loop;
				}
			}
			res.add(i);
			i++;
		}
		return res;
	}

	private boolean popWord(String s) {
		Integer val = cloneMap.get(s);
		if (val != null && val != 0) {
			cloneMap.put(s, --val);
			return true;
		}
		return false;
	}
}