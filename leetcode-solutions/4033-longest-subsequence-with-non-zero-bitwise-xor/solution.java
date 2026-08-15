class Solution {
    public int longestSubsequence(int[] nums) {
        int n=nums.length;
        int totalXor=0;
        for(int num:nums){
            totalXor^=num;
        }
        boolean hasAllZeros=true;
        for(int num:nums){
            if(num!=0){
                hasAllZeros=false;
                break;
            }
        }
        if(hasAllZeros)  return 0;
        if(totalXor!=0){
            return n;
        }
        return n-1;
        
    }
}
