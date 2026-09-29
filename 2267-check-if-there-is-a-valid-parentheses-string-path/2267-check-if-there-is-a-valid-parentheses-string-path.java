import java.util.*;
class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if ((m + n - 1) % 2 != 0)
            return false;
        Set<Integer>[][] dp = new HashSet[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                dp[i][j] = new HashSet<>();
        if (grid[0][0] == ')')
            return false;
        dp[0][0].add(1);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0)
                    continue;
                if (i > 0) {
                    for (int b : dp[i - 1][j]) {
                        int x = b;
                        if (grid[i][j] == '(')
                            x++;
                        else
                            x--;
                        if (x >= 0)
                            dp[i][j].add(x);
                    }
                }
                if (j > 0) {
                    for (int b : dp[i][j - 1]) {
                        int x = b;
                        if (grid[i][j] == '(')
                            x++;
                        else
                            x--;
                        if (x >= 0)
                            dp[i][j].add(x);
                    }
                }
            }
        }
       return dp[m - 1][n - 1].contains(0);
    }
}