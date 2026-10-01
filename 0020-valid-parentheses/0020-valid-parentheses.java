class Solution {
    public boolean isValid(String s) {
        Stack<Character> stck=new Stack<>();
        for(int ind=0; ind<s.length(); ind++)
        {
            char ch=s.charAt(ind);
            if(ch=='(' || ch=='{' || ch=='[')
               stck.push(ch);
            else
            {
                if(stck.isEmpty())
                    return false;
                char topData=stck.pop();
                if(ch==')' && topData!='(' || ch=='}' && topData!='{' || ch==']' && topData!='[')
                     return false;
            }
        }
        return stck.isEmpty();
   }
}