class Solution {
    public int minMoves(int[] nums) {
        int max = nums[0];
        int moves = 0;
        int c;
        for(int i = 0 ;i<nums.length;i++){
            if(max<nums[i]){
                max=nums[i];
            }
        }
        for(int i = 0;i<nums.length;i++){
            c = max-nums[i];
            moves=moves+c;
        }
     return moves;
    }
}