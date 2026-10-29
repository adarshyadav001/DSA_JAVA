class Solution {
    public int[] twoSum(int[] nums, int target) {
       HashMap <Integer,Integer> Map = new HashMap<>();
for(int i = 0 ;i<nums.length;i++){
    int needed = target-nums[i];

    if(Map.containsKey(needed)){
        return new int[]{Map.get(needed),i};
    }
    Map.put(nums[i],i);
}
return new int[]{};
    }
}