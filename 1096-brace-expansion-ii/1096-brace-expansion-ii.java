import java.util.*;

class Solution {
    public List<String> braceExpansionII(String expr) {
        return new ArrayList<>(expand(expr));
    }

    private Set<String> expand(String s) {
        Set<String> res = new TreeSet<>(); // TreeSet keeps sorted order
        int brace = 0, last = 0;
        List<Set<String>> parts = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '{') brace++;
            if (c == '}') brace--;
            if ((c == ',' && brace == 0) || i == s.length() - 1) {
                String sub = s.substring(last, i == s.length() - 1 ? i + 1 : i);
                parts.add(product(sub));
                last = i + 1;
            }
        }
        for (Set<String> p : parts) res.addAll(p);
        return res;
    }

    private Set<String> product(String s) {
        List<Set<String>> groups = new ArrayList<>();
        for (int i = 0; i < s.length();) {
            if (s.charAt(i) == '{') {
                int j = i, bal = 0;
                for (; i < s.length(); i++) {
                    if (s.charAt(i) == '{') bal++;
                    if (s.charAt(i) == '}') bal--;
                    if (bal == 0) break;
                }
                groups.add(expand(s.substring(j + 1, i)));
                i++;
            } else {
                groups.add(new TreeSet<>(Arrays.asList("" + s.charAt(i))));
                i++;
            }
        }
        Set<String> res = new TreeSet<>(Arrays.asList(""));
        for (Set<String> g : groups) {
            Set<String> tmp = new TreeSet<>();
            for (String a : res) for (String b : g) tmp.add(a + b);
            res = tmp;
        }
        return res;
    }
}
