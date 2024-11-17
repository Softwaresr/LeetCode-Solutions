class Solution {
 public int magnificentSets(int n, int[][] edges) {
        int[] degree = new int[n + 1];
        for (int[] e : edges) {
            degree[e[0]]++;
            degree[e[1]]++;
        }
        int[][] adjacent = new int[n + 1][];
        for (int i = 1; i <= n; i++) adjacent[i] = new int[degree[i]];
        for (int[] e : edges) {
            int a = e[0];
            int b = e[1];
            adjacent[a][--degree[a]] = b;
            adjacent[b][--degree[b]] = a;
        }

        Queue<Integer> bfs = new LinkedList<>();
        int[] distance = new int[n + 1];
        int[] maxDistance = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(distance, -1);
            bfs.offer(i);
            distance[i] = 0;
            int maxDist = 0;
            while (bfs.size() > 0) {
                int node = bfs.poll();
                int nextDist = distance[node] + 1;
                for (int nextNode : adjacent[node])
                    if (distance[nextNode] < 0) {
                        distance[nextNode] = nextDist;
                        maxDist = nextDist;
                        bfs.offer(nextNode);
                    }
            }
            maxDistance[i] = maxDist;
        }

        Arrays.fill(distance, -1);
        int res = 0;
        for (int i = 1; i <= n; i++) {
            if (distance[i] >= 0) continue;
            bfs.offer(i);
            distance[i] = 0;
            int diameter = 0;
            while (bfs.size() > 0) {
                int node = bfs.poll();
                diameter = Math.max(diameter, maxDistance[node]);
                int nextDist = distance[node] + 1;
                for (int nextNode : adjacent[node]) {
                    if (distance[nextNode] < 0) {
                        distance[nextNode] = nextDist;
                        bfs.offer(nextNode);
                    } else if ((nextDist + distance[nextNode]) % 2 != 0) return -1;
                }
            }
            res += diameter + 1;
        }
        return res;
    }
}

//Each node in the graph belongs to exactly one group.
//