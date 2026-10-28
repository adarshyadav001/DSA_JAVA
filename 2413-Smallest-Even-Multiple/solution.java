class Solution {
    public int smallestEvenMultiple(int n)
     {
int c = Integer.MAX_VALUE;
int a = 0;
        for(int i=0;i<c;i++){
            if(i%2==0 && i%n==0){
                 a = i;
                break;
            }
        }
        return a;
    }
}