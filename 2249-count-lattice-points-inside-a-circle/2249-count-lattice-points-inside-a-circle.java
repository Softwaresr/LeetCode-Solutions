class Solution {
    public int countLatticePoints(int[][] circles) {
        Set<String> points = new HashSet<>();
        for(int[] circle : circles){
            int cx = circle[0];
            int cy = circle[1];
            int r = circle[2];

            for(int x = cx - r; x <= cx + r; x++){
                for(int y = cy - r; y <= cy + r; y++){
                    if((x - cx) *  (x - cx) + (y - cy) * (y - cy) <= r * r){
                        points.add(x + " , " + y);

                    }
                }
            }
        }
        return points.size();
        
    }
}