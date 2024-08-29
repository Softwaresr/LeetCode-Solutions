class Solution:
    def fourSum(self, nums: List[int], target: int) -> List[List[int]]:
        nums.sort()
        q=[]
        n=len(nums)

        for a in range(n-3):
            if a>0 and nums[a] == nums[a-1]:
                continue
            for b in range(a + 1, n - 2):
                if b >a + 1 and nums[b]==nums[b-1]:
                    continue
                
                left,right=b+1,n-1

                while left < right:
                    curr=nums[a]+nums[b]+nums[left]+nums[right]

                    if curr == target :
                        q.append([nums[a],nums[b],nums[left],nums[right]])
                        while left<right and nums[left]==nums[left + 1]:
                             left+=1
                        while left<right and nums[right]==nums[right -1]:
                            right-=1
                        left+=1
                        right-=1
                    elif curr < target:
                        left+=1
                    else:
                        right-=1
        return q          