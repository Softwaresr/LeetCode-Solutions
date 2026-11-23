class ProductOfNumbers {
    private final List<Integer> prefix = new ArrayList<>(List.of(1));  // prefix[i]: the product of the first i numbers after the last zero

    public void add(int num) {
        if (num == 0) {
            prefix.clear();  // a window that reaches this zero is 0, so forget everything before it
            prefix.add(1);
        } else {
            prefix.add(prefix.get(prefix.size() - 1) * num);
        }
    }

    public int getProduct(int k) {
        int n = prefix.size();
        if (k >= n)  // the window reaches back to a zero
            return 0;
        return prefix.get(n - 1) / prefix.get(n - 1 - k);
    }
}