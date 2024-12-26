class Solution {

    private static final int[] PRIMES_UP_TO_SQRT_1000 = new int[] {
        2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31
    };

    public long beautifulSubstrings(String s, int k) {
        final int[] vowelsAfter = new int[1 + s.length()];
        for (int i = 0; i < s.length(); ++i) {
            final char ch = s.charAt(i);
            final boolean vowel;
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                vowelsAfter[i + 1] = vowelsAfter[i] + 1;
            } else {
                vowelsAfter[i + 1] = vowelsAfter[i];
            }
        }
        final int multiples = computeMultiples(k); // sqrt(m)
        final int step = (multiples << 1);
        long answer = 0;
        for (int i = 0; i < s.length(); ++i) {
            for (int j = (i + step); j <= s.length(); j += step) {
                final int length = j - i;
                final int vowels = vowelsAfter[j] - vowelsAfter[i];
                final int consonants = length - vowels;
                if (vowels == consonants) {
                    answer++;
                }
            }
        }
        return answer;
    }

    private int computeMultiples(int k) {
        int squared = 1;
        int remainder = k;
        int i = 0;
        while (remainder > 1 && i < PRIMES_UP_TO_SQRT_1000.length && remainder >= PRIMES_UP_TO_SQRT_1000[i]) {
            final int factor = PRIMES_UP_TO_SQRT_1000[i++];
            boolean changed = false;
            while ((remainder % factor) == 0) {
                squared *= factor;
                remainder /= factor;
                changed = true;
            }
            if (changed && !isSquare(squared)) {
                squared *= factor; // each prime factor must have even power
            }
        }
        // remainder effectively squared as well to have even power
        return remainder * (int) Math.sqrt(squared);
    }

    private boolean isSquare(int value) {
        final int a = (int) Math.sqrt(value);
        return ((a * a) == value);
    }

}