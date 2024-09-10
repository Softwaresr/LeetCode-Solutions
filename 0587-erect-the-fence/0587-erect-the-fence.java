class Solution {
     int crossProduct(int[] current, int[] nextPoint, int[] point){
        int x1 = current[0] - nextPoint[0];
        int x2 = current[0] - point[0];
        int y1 = current[1] - nextPoint[1];
        int y2 = current[1] - point[1];

        return ((y2*x1) - (y1*x2));
    }

    int distance(int[] current, int[] nextPoint, int[] point){
        int x1 = current[0] - nextPoint[0];
        int x2 = current[0] - point[0];
        int y1 = current[1] - nextPoint[1];
        int y2 = current[1] - point[1];

        return Integer.compare(y1*y1+x1*x1, y2*y2+x2*x2);
    }

    public int[][] outerTrees(int[][] trees) {
        //find the starting point 
        int min = 0;
        int xmin = trees[0][0];

        for(int i = 1; i < trees.length; i++){
            if(trees[i][0] < xmin || (xmin == trees[i][0] && trees[i][1] < trees[min][1])){
                xmin = trees[i][0];
                min = i;
            }
        }

        int [] temp = trees[min];
        trees[min] = trees[0];
        trees[0] = temp;

        int[] current = trees[0];
        int[] start = trees[0];
        List<int[]> collinearPoints = new ArrayList<>();
        Set<int[]> result = new HashSet<>();
        result.add(start);

        do{
            int[] nextPoint = trees[0];
            for(int i = 1; i < trees.length; i++){
                int[] point = trees[i];
                if(point == current) continue;

                int cp = crossProduct(current, nextPoint, point);

                if(cp > 0){
                    nextPoint = point;
                    collinearPoints.clear();
                }else if(cp == 0){
                    if(distance(current,nextPoint,point) < 0){
                        collinearPoints.add(nextPoint);
                        nextPoint = point;
                    }else{
                        collinearPoints.add(point);
                    }
                }
            }

            for(int[] points : collinearPoints){
                result.add(points);
            }

            if(nextPoint == start){
                break;
            }

            result.add(nextPoint);
            current = nextPoint;
        }while(true);

        int[][] ans = new int[result.size()][2];
        int i = 0;
        for(int[] points : result){
            ans[i++] = points;
        }

        return ans;
    }
}