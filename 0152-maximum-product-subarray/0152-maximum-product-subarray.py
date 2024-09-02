class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        res=max(nums)
        currmax , currmin = 1, 1
        for n in nums:
            temp = currmax* n
            currmax = max(currmax*n,currmin*n,n)
            currmin= min(temp,currmin*n,n)
            res=max(res,currmax)
        return res
