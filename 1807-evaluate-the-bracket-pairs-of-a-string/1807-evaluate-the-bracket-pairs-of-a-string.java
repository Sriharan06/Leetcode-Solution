import java.util.*;
class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String,String> map=new HashMap<>();
        for(List<String> kv:knowledge) map.put(kv.get(0),kv.get(1));
        StringBuilder ans=new StringBuilder(), key=new StringBuilder();
        boolean in=false;
        for(char c:s.toCharArray()){
            if(c=='('){in=true; key.setLength(0);}
            else if(c==')'){ans.append(map.getOrDefault(key.toString(),"?")); in=false;}
            else if(in) key.append(c);
            else ans.append(c);
        }
        return ans.toString();
    }
}
