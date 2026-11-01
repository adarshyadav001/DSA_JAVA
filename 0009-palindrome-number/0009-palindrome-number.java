class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) {
    return false;
}
        Scanner sc = new Scanner(System.in);
        int a ;
        int rev = 0;
        int b = x;
        String numStr = String.valueOf(x);
        int length = numStr.length();
       for(int i = 0 ;i<length;i++){
       a = x%10;
       rev = rev*10+a;
       x=x/10;
       }
       if(b==rev){
        return true;
       }
       else
       return false;
    }
}