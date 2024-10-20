class Solution {
    class WorkerOnBank{
        int details[], id;
        WorkerOnBank(int details[], int id){
            this.details = details;
            this.id = id;
        }
    }
    class WorkerInWarehouse{
        int id, outTime;
        WorkerInWarehouse(int id, int outTime){
            this.id = id;
            this.outTime = outTime;
        }
    }
    class WorkerOnBankComparator implements Comparator<WorkerOnBank>{
        public int compare(WorkerOnBank a, WorkerOnBank b){
            if(a.details[0] + a.details[2] == b.details[0] + b.details[2])
                return b.id - a.id;
            return (b.details[0] + b.details[2]) - (a.details[0] + a.details[2]);
        }
    }
    class WorkerInWarehouseComparator implements Comparator<WorkerInWarehouse>{
        public int compare(WorkerInWarehouse a, WorkerInWarehouse b){
           return a.outTime - b.outTime;
        }
    }
    public int findCrossingTime(int n, int k, int[][] time) {
        int t = 0, i = 0, ans = 0;

        PriorityQueue<WorkerOnBank> leftBank = new PriorityQueue<>(k, new WorkerOnBankComparator());
        PriorityQueue<WorkerOnBank> rightBank = new PriorityQueue<>(k, new WorkerOnBankComparator());

        PriorityQueue<WorkerInWarehouse> oldWarehouse = new PriorityQueue<>(k, new WorkerInWarehouseComparator());
        PriorityQueue<WorkerInWarehouse> newWarehouse = new PriorityQueue<>(k, new WorkerInWarehouseComparator());

        for(int tt[] : time) leftBank.add(new WorkerOnBank(tt,i++));
        
        while(n > 0 || rightBank.size() > 0 || oldWarehouse.size() > 0 || newWarehouse.size() > 0){
            if(rightBank.size() == 0){
                if(n > 0 && leftBank.size() > 0){
                    t += leftBank.peek().details[0];
                    oldWarehouse.add(new WorkerInWarehouse(leftBank.peek().id, t + leftBank.poll().details[1]));
                    n -= 1;
                } else {
                    int minTime = Integer.MAX_VALUE;
                    if(oldWarehouse.size() > 0) minTime = Math.min(minTime, oldWarehouse.peek().outTime);
                    if(newWarehouse.size() > 0) minTime = Math.min(minTime, newWarehouse.peek().outTime);

                    t = minTime;
                }
            } else {
                t += rightBank.peek().details[2]; ans = t;
                newWarehouse.add(new WorkerInWarehouse(rightBank.peek().id, t + rightBank.poll().details[3]));
            }

            while(oldWarehouse.size() > 0 && oldWarehouse.peek().outTime <= t)
                rightBank.add(new WorkerOnBank(time[oldWarehouse.peek().id], oldWarehouse.poll().id));
            
            while(newWarehouse.size() > 0 && newWarehouse.peek().outTime <= t)
                leftBank.add(new WorkerOnBank(time[newWarehouse.peek().id], newWarehouse.poll().id));
        }

        return ans;
    }
}