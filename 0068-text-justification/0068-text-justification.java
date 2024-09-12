class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        List<String> currentLine = new ArrayList<>();
        int numOfLetters = 0;

        for (String word : words) {
            // Check if adding the next word would exceed maxWidth
            if (numOfLetters + word.length() + currentLine.size() > maxWidth) {
                result.add(justify(currentLine, numOfLetters, maxWidth));
                currentLine = new ArrayList<>();
                numOfLetters = 0;
            }
            // Add the current word to the line
            currentLine.add(word);
            numOfLetters += word.length();
        }

        // Last line - left-justified
        StringBuilder lastLine = new StringBuilder();
        for (int i = 0; i < currentLine.size(); i++) {
            if (i > 0) {
                lastLine.append(' ');
            }
            lastLine.append(currentLine.get(i));
        }
        // Pad spaces to the end of the last line
        while (lastLine.length() < maxWidth) {
            lastLine.append(' ');
        }
        result.add(lastLine.toString());

        return result;
    }

    private String justify(List<String> words, int numOfLetters, int maxWidth) {
        StringBuilder line = new StringBuilder();
        int totalSpaces = maxWidth - numOfLetters;
        int numGaps = words.size() - 1;

        if (numGaps == 0) {
            // Single word in line, just add it followed by spaces
            line.append(words.get(0));
            while (line.length() < maxWidth) {
                line.append(' ');
            }
        } else {
            int spacesBetweenGaps = totalSpaces / numGaps;
            int extraSpaces = totalSpaces % numGaps;

            for (int i = 0; i < words.size(); i++) {
                if (i > 0) {
                    int spaceCount = spacesBetweenGaps + (i <= extraSpaces ? 1 : 0);
                    for (int j = 0; j < spaceCount; j++) {
                        line.append(' ');
                    }
                }
                line.append(words.get(i));
            }
        }

        return line.toString();
    }
}