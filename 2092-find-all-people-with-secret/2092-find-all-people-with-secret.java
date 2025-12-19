class Solution {
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[2], b[2]));
        int[] parent = new int[n];
        for(int i = 0; i < n; i++) parent[i] = i;
        union(0, firstPerson, parent);

        int i = 0;
        int m = meetings.length;
        while(i<m){
            int currentTime = meetings[i][2];
            List<Integer> peopleAtThisTime = new ArrayList<>();
            int j = i;
            while(j < m && meetings[j][2] == currentTime){
                int u = meetings[j][0];
                int v = meetings[j][1];
                union(u, v, parent);
                peopleAtThisTime.add(u);
                peopleAtThisTime.add(v);
                j++;

            }
            int secretRoot = find(0, parent);
            for(int person : peopleAtThisTime){
                if(find(person, parent) != secretRoot){
                    parent[person] = person;

                }
            }
            i = j;
        }
        List<Integer> result = new ArrayList<>();
        int secretRoot = find(0, parent);
        for(int k = 0; k < n; k++){
            if(find(k, parent) == secretRoot){
                result.add(k);

            }
        }
        return result;
        
        
    }
    private int find(int i, int[] parent){
        if(parent[i] == i) return i;
        return parent[i] = find(parent[i], parent);

    }
    private void union(int i, int j, int[] parent){
        int rootI = find(i, parent);
        int rootJ = find(j, parent);
        if(rootI != rootJ){
            parent[rootI] = rootJ;
        }
    }
}