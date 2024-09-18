class Solution {
    public boolean search(int[] arr, int target) {
        int st = 0;
        int end = arr.length - 1;
        
        while (st <= end) {
            int mid = st + (end - st) / 2;
            
            if (arr[mid] == target) {
                return true;
            }
            
            // If we can't decide the sorted half, move pointers closer
            if (arr[st] == arr[mid] && arr[mid] == arr[end]) {
                st++;
                end--;
            } else if (arr[st] <= arr[mid]) {
                // Left half is sorted
                if (arr[st] <= target && target < arr[mid]) {
                    end = mid - 1;
                } else {
                    st = mid + 1;
                }
            } else {
                // Right half is sorted
                if (arr[mid] < target && target <= arr[end]) {
                    st = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        
        return false;
    }
}