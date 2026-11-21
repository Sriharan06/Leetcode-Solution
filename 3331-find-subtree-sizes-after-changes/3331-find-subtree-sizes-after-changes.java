import java.util.*;
class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        List<Integer>[] original = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            original[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            original[parent[i]].add(i);
        }
        int[] newParent = parent.clone();
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);
        int[] stackNode = new int[n];
        int[] stackIndex = new int[n];
        int[] stackPrev = new int[n];
        int top = 0;
        stackNode[0] = 0;
        stackIndex[0] = 0;
        int rootChar = s.charAt(0) - 'a';
        stackPrev[0] = lastSeen[rootChar];
        lastSeen[rootChar] = 0;
        while (top >= 0) {
            int node = stackNode[top];

            // Process next child
            if (stackIndex[top] < original[node].size()) {
                int child = original[node].get(stackIndex[top]++);
                int ch = s.charAt(child) - 'a';

                // Find nearest matching ancestor
                int nearest = lastSeen[ch];

                if (nearest != -1) {
                    newParent[child] = nearest;
                }

                // Enter child
                top++;
                stackNode[top] = child;
                stackIndex[top] = 0;
                stackPrev[top] = lastSeen[ch];

                lastSeen[ch] = child;
            } else {
                // Exit node and restore lastSeen
                int ch = s.charAt(node) - 'a';
                lastSeen[ch] = stackPrev[top];
                top--;
            }
        }
        List<Integer>[] updated = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            updated[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            updated[newParent[i]].add(i);
        }
        int[] order = new int[n];
        int count = 0;
        int[] stack = new int[n];
        top = 0;
        stack[top] = 0;
        while (top >= 0) {
            int node = stack[top--];
            order[count++] = node;
            for (int child : updated[node]) {
                stack[++top] = child;
            }
        }
        int[] answer = new int[n];
        Arrays.fill(answer, 1);
        for (int i = n - 1; i >= 0; i--) {
            int node = order[i];
            if (node != 0) {
                answer[newParent[node]] += answer[node];
            }
        }
        return answer;
    }
}