class Solution {
    private static final int X_IDX = 0;
    private static final int Y_IDX = 1;
    private static final int TIME_IDX = 2;
    
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        // Find the maximum meeting time.
        int maxTime = 0;
        for (int[] mtg : meetings)
            if (mtg[TIME_IDX] > maxTime)  
                maxTime = mtg[TIME_IDX];
        
        // Allocate an array to contain a list of meetings for each time 
        // from time 0 to the maximum meeting time.  Scan through the 
        // meetings to copy the meetings to this array at the index which 
        // is the time of the meeting.  Some array entries may still be 
        // null because there weren't any meetings at that time.
        ArrayList<int[]>[] mtgsByTime = new ArrayList[maxTime + 1];
        for (int[] mtg : meetings) {
            ArrayList<int[]> list = mtgsByTime[mtg[TIME_IDX]];
            if (list == null)
                list = mtgsByTime[mtg[TIME_IDX]] = new ArrayList<int[]>();
            list.add(mtg);
        }

        // Setup the union find for people to be grouped with person 0.
        int[] parents = new int[n];
        for (int i = 1; i < n; i++)  parents[i] = i;
        parents[firstPerson] = 0;
        int[] people = new int[meetings.length * 2];
        // Loop through all possible meeting times.
        for (int tim = 1; tim <= maxTime; tim++) {
            if (mtgsByTime[tim] != null) {
                // Do a union find of all meetings at the current time.
                int peopleCount = 0;
                for (int[] mtg : mtgsByTime[tim]) {
                    int x = find(mtg[X_IDX], parents);
                    int y = find(mtg[Y_IDX], parents);
                    if (x != y) {
                        if (x < y)
                            parents[y] = x;
                        else
                            parents[x] = y;
                        if (x != 0 && y != 0) {
                            people[peopleCount++] = mtg[X_IDX];
                            people[peopleCount++] = mtg[Y_IDX];
                        }
                    }
                }
                // Loop for people who were unionized at the current time.  
                // If those people are not part of person 0's group, then 
                // un-unionize them.
                for (int i = 0; i < peopleCount; i++) {
                    int person = people[i];
                    if (find(person, parents) != 0) 
                        parents[person] = person;
                }
            }
        }

        // Search through the union find parents to determine which 
        // people know the secret.  Any person who union finded to 
        // person 0's group, knows the secret.
        ArrayList<Integer> knowsList = new ArrayList();
        for (int person = 0; person < n; person++)
            if (parents[person] == 0)
                knowsList.add(person);
        return knowsList;
    }
    
    
    private int find(int u, int[] parents) {
        if (parents[u] == u)  return u;
        return (parents[u] = find(parents[u], parents));
    }
}