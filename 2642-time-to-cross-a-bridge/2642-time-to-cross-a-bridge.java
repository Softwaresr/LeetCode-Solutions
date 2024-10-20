class Solution {
    public int findCrossingTime(int n, int k, int[][] time) {
        Worker[] worker = new Worker[k];
        for(int i=0;i<k;i++) {
            worker[i] = new Worker(i, time[i][0], time[i][1], time[i][2], time[i][3]);
        }
        //Four priority queues based on current position of the workers
        
        //Waiting at left side
        PriorityQueue<Worker> leftToRight = new PriorityQueue<Worker>(k);
        
        //Picking up old boxes
        PriorityQueue<Worker> pickOld = new PriorityQueue<Worker>(k, (a,b) -> a.time-b.time);
        
        //Waiting at Right Side
        PriorityQueue<Worker> rightToLeft = new PriorityQueue<Worker>(k);
        
        //Putting new boxes
        PriorityQueue<Worker> putNew = new PriorityQueue<Worker>(k, (a,b) -> a.time-b.time);
        
        //Initially all workers waiting at left
        for(int i=0;i<k;i++) {
            leftToRight.offer(worker[i]);
        }
        
        //Num boxes remaining
        int remaining = n;
        
        //Current time
        int currentTime = 0;
        
        
        while(remaining > 0) {
            //Move the workers from pickOld to rightToLeft or putNew to leftToRight if currentTime >= worker's time to complete the task
            while(!pickOld.isEmpty() && pickOld.peek().time <= currentTime) {
                Worker w = pickOld.poll();
                rightToLeft.offer(w);
            }
            while(!putNew.isEmpty() && putNew.peek().time <= currentTime) {
                Worker w = putNew.poll();
                leftToRight.offer(w);
            }
            
            //Move worker on the bridge and update the worker's time to complete the task
            if(!rightToLeft.isEmpty()) {
                Worker w = rightToLeft.poll();
                w.time = currentTime + w.RL + w.PN;
                putNew.offer(w);
                currentTime = currentTime + w.RL;
            } else if(!leftToRight.isEmpty()) {
                Worker w = leftToRight.poll();
                w.time = currentTime + w.LR + w.PO;
                pickOld.offer(w);
                remaining--;
                currentTime = currentTime + w.LR;
            } else {
                //Bridge is empty and no worker waiting, move currenttime to first person finishing the task
                currentTime = Integer.MAX_VALUE;
                if(!pickOld.isEmpty()) {
                    currentTime = Math.min(currentTime, pickOld.peek().time);
                }
                if(!putNew.isEmpty()) {
                    currentTime = Math.min(currentTime, putNew.peek().time);
                }
            }
        }
        //Now empty the right warehouse and keep workers moving
        while(!rightToLeft.isEmpty() || !pickOld.isEmpty()) {
            while(!pickOld.isEmpty() && pickOld.peek().time <= currentTime) {
                Worker w = pickOld.poll();
                rightToLeft.offer(w);
            }
            if(!rightToLeft.isEmpty()) {
                Worker w = rightToLeft.poll();
                w.time = currentTime + w.RL + w.PN;
                currentTime = currentTime + w.RL;
            } else {
                currentTime = pickOld.peek().time;
            }
        }
        return currentTime;
    }
    
    class Worker implements Comparable<Worker> {
        int index;
        int LR;
        int PO;
        int RL;
        int PN;
        int time;
        
        Worker(int i, int LR, int PO, int RL, int PN) {
            this.index = i;
            this.LR = LR;
            this.PO = PO;
            this.RL = RL;
            this.PN = PN;
            this.time = 0;
        }
        
        @Override
        public int compareTo(Worker w) {
            if(this.LR+this.RL!=w.LR+w.RL) {
                return w.LR+w.RL-this.LR-this.RL;
            }
            return w.index-this.index;
        }
        
        @Override
        public String toString() {
            return index+":"+time+":["+LR+" "+PO+" "+RL+" "+PN+"]";
        }
    }
}