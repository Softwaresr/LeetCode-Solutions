class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        nums.sort()
        ans=[]
        for i in range(len(nums) - 2):
            if nums[0]>0:
                break
            if i>0 and nums[i] == nums[i-1]:
                continue
            l=i+1
            r=len(nums)-1

            while l<r:
                t=nums[i]+nums[l]+nums[r]
                if t<0:
                    l+=1
                elif t>0:
                    r-=1
                else:
                    triplet=[nums[i],nums[l],nums[r]]
                    ans.append(triplet)
                    while l<r and nums[l]==triplet[1]:
                        l+=1
                    while l<r and nums[r]==triplet[2]:
                        r-=1
        return ans

        
