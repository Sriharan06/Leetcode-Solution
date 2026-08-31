class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int first=-1, prev=-1, min=Integer.MAX_VALUE, idx=1;
        ListNode a=head, b=head.next, c=b.next;
        while(c!=null){
            if((b.val>a.val && b.val>c.val)||(b.val<a.val && b.val<c.val)){
                if(prev!=-1) min=Math.min(min,idx-prev);
                if(first==-1) first=idx; prev=idx;
            } a=b; b=c; c=c.next; idx++;
        }
        return prev==first?new int[]{-1,-1}:new int[]{min,prev-first};
    }
}
