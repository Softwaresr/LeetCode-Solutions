class Solution {

    int result = -1;
    Set<String> alreadyChecked = new HashSet<>();

    public int findMinStep(String board, String hand) {
        Set<String> handPermutations = new StringPermutations().permute(hand);        
        for (String handSinlePermutation : handPermutations) {
            dfs(board, handSinlePermutation);            
        }
        return result != -1 ? hand.length() - result : - 1;
    }

    private void dfs(String board, String charsLeft) {        
        String memoKey = board + "#" + charsLeft;
        if (alreadyChecked.contains(memoKey) || charsLeft.length() <= result) {
            return;
        }

        char charToInsert = charsLeft.charAt(0);
        charsLeft = charsLeft.substring(1, charsLeft.length());
        
        for (int i = 1; i <= board.length(); i++) {
            if (!isGoodPlaceToInsert(board, i, charToInsert)) {
                continue;
            }
            String newBoard = add(board, charToInsert, i);
            newBoard = reduce(newBoard, i);
            if (newBoard.length() == 0) {
                result = Math.max(result, charsLeft.length());
            } else if (!charsLeft.isEmpty()) {
                dfs(newBoard, charsLeft);
            }
        }    
        alreadyChecked.add(memoKey);
    }

    private boolean isGoodPlaceToInsert(String board, int i, char charToInsert) {
        return board.charAt(i - 1) == charToInsert 
            || (i >= 1 && i < board.length() && board.charAt(i) == board.charAt(i - 1));
    }

    private String add(String board, char c, int index) {
        StringBuilder out = new StringBuilder();
        out.append(board.substring(0, index));
        out.append(c);
        if (index < board.length()) {
            out.append(board.substring(index, board.length()));
        }        
        return out.toString();
    }

    private String reduce(String board, int index) {    
        char c = board.charAt(index);
        int left = index;
        while (left > 0 && board.charAt(left - 1) == c) {
            left--;
        }
        int right = index;
        while (right < board.length() - 1 && board.charAt(right + 1) == c) {
            right++;
        }
        if ((right - left + 1) >= 3) {
            board = remove(board, left, right);
            if (board.length() > 2) {                
                int newIndex = (left != 0) ? left - 1 : left + 1;                
                board = reduce(board, newIndex);                 
            }            
        }
        return board;
    }

    private String remove(String board, int left, int right) {
        StringBuilder out = new StringBuilder();
        if (left >= 0) {
            out.append(board.substring(0, left));
        }
        if (right < board.length()) {
            out.append(board.substring(right + 1, board.length()));
        }
        return out.toString();
    }

    public class StringPermutations {

        public Set<String> permute(String text) {
            Set<String> result = new HashSet<>();        
            result.add(text);
            permutation(0, text.toCharArray(), result);
            return result;
        }

        private void permutation(int start, char[] chars, Set<String> result) {
            for (int i = start; i <= chars.length - 1; i++) {                
                swap(chars, start, i);                
                if (i != start) {
                    result.add(new String(chars));                          
                }                
                permutation(start + 1, chars, result);
                swap(chars, start, i);
            }
        }

        private void swap(char[] chars, int a, int b) {
            if (a == b) {
                return;
            }
            char temp = chars[b];
            chars[b] = chars[a];
            chars[a] = temp;            
        }
    }
}