class Solution {
    public boolean isReachable(int targetX, int targetY) {
        if (targetX > targetY)
            return isReachable(targetY, targetX);
        if (targetX % 2 == 0)
            return isReachable(targetX / 2, targetY);
        if (targetY % 2 == 0)
            return isReachable(targetX, targetY / 2);
            
        if (targetX == 1) 
            return true;
        
        if (targetY == targetX)
            return false;

        return isReachable(targetX, (targetX + targetY) / 2);
    }
}