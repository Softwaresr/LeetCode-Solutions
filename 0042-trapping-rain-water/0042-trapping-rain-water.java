class Solution {
  public int trap(int[] height) {
    int volume = 0;
    int left = 0;
    int right = height.length - 1;
    int leftMax = height[left];
    int rightMax = height[right];

    while (left < right) {
      leftMax = Math.max(height[left], leftMax);
      rightMax = Math.max(height[right], rightMax);

      if (leftMax <= rightMax) {
        volume += leftMax - height[left];
        left += 1;
      } else {
        volume += rightMax - height[right];
        right -= 1;
      }
    }
    return volume;
  }
}