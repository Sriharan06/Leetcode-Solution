class Solution {
    public boolean isPalindrome(int x) {
        if(x<0) return false;
        int digit, rev=0, copyNum=x;
        while(copyNum!=0)
        { 
            digit=copyNum%10;
            rev=(rev*10)+digit;
            copyNum=copyNum/10;
        }
        return rev==x;
    }
}