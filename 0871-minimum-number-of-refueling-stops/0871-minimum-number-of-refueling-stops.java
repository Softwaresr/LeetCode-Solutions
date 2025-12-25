import java.util.PriorityQueue;
import java.util.Collections;

class Solution {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int fule = startFuel;
        int stops = 0;
        int i = 0;
        int n = stations.length;
        while(fule < target){
            while(i < n && stations[i][0] <= fule){
                maxHeap.offer(stations[i][1]);
                i++;

            }
            if(maxHeap.isEmpty()) return -1;
            fule += maxHeap.poll();
            stops++;
        }
        return stops;
        
    }
}