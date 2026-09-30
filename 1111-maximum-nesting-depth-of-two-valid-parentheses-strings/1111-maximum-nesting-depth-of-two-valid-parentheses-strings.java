class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int a=0;
        int length=seq.length();
        int[]ans=new int[length];
        for(int i=0;i<length;i++){
            if(seq.charAt(i)=='('){
                ++a;
                ans[i]=a%2;
            }else{
                ans[i]=a%2;
                --a;
            }
        }
        return ans;
    }
}