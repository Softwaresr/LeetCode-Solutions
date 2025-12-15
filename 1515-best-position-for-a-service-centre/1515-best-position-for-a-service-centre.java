class Solution {
    public double getMinDistSum(int[][] positions) {
        double x = 0;
        double y = 0;

        double res = distanceSum(x, y, positions);
        double step = 50;

        OUT: while(step > 0.000001){
            for(int[] direction : new int[][] { {1, 0}, {-1, 0}, {0, 1}, {0, -1}}){
                double nx = x + step * direction[0];
                double ny = y + step * direction[1];
                double distance = distanceSum(nx, ny, positions);
                if(distance < res){
                    res = distance;
                    x = nx;
                    y = ny;
                    continue OUT;

                }
            }
            step /= 2;

        }
        return res;

        
        
    }
    private double distanceSum(double x, double y, int[][] positions){
        double res = 0;
        for(int[] p : positions){
            res += Math.sqrt((p[0] - x) * (p[0] - x) + (p[1] - y) * (p[1] - y));
        }
        return res;
    }
}