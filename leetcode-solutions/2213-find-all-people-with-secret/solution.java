import java.util.*;

class Solution {
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[2], b[2]));

        boolean[] knowsSecret = new boolean[n];
        knowsSecret[0] = true;
        knowsSecret[firstPerson] = true;

        int i = 0;
        while (i < meetings.length) {
            int time = meetings[i][2];
            Map<Integer, List<Integer>> graph = new HashMap<>();
            Set<Integer> participants = new HashSet<>();

            while (i < meetings.length && meetings[i][2] == time) {
                int x = meetings[i][0];
                int y = meetings[i][1];
                graph.computeIfAbsent(x, k -> new ArrayList<>()).add(y);
                graph.computeIfAbsent(y, k -> new ArrayList<>()).add(x);
                participants.add(x);
                participants.add(y);
                i++;
            }

            Queue<Integer> queue = new LinkedList<>();
            Set<Integer> visited = new HashSet<>();

            for (int p : participants) {
                if (knowsSecret[p]) {
                    queue.offer(p);
                    visited.add(p);
                }
            }

            while (!queue.isEmpty()) {
                int curr = queue.poll();
                for (int nei : graph.getOrDefault(curr, Collections.emptyList())) {
                    if (!visited.contains(nei)) {
                        visited.add(nei);
                        queue.offer(nei);
                    }
                }
            }

            for (int p : visited) {
                knowsSecret[p] = true;
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int p = 0; p < n; p++) {
            if (knowsSecret[p]) {
                result.add(p);
            }
        }
        return result;
    }
}

