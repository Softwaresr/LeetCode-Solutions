// Hard problem, just read it
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int currentLength = 0;
        int numWords = 0;
        List<String> line = new ArrayList<>();

        for (String word : words) {
            if (currentLength + word.length() + numWords <= maxWidth) {
                line.add(word);
                currentLength += word.length();
                numWords++;
            } else {
                // Form a line
                result.add(justifyLine(line, maxWidth, currentLength, numWords));
                line = new ArrayList<>();
                line.add(word);
                currentLength = word.length();
                numWords = 1;
            }
        }

        // Handle the last line
        String lastLine = String.join(" ", line);
        int padding = maxWidth - lastLine.length();
        lastLine += " ".repeat(padding);
        result.add(lastLine);

        return result;
    }

    private String justifyLine(List<String> line, int maxWidth, int currentLength, int numWords) {
        if (numWords == 1) {
            // Single word on the line, left-justify
            return String.format("%-" + maxWidth + "s", line.get(0));
        }

        int totalSpaces = maxWidth - currentLength;
        int spacesBetweenWords = numWords - 1;
        int extraSpaces = totalSpaces % spacesBetweenWords;
        int spaces = totalSpaces / spacesBetweenWords;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < line.size() - 1; i++) {
            sb.append(line.get(i));
            sb.append(" ".repeat(spaces));
            if (extraSpaces > 0) {
                sb.append(" ");
                extraSpaces--;
            }
        }
        sb.append(line.get(line.size() - 1));

        return sb.toString();
    }
}