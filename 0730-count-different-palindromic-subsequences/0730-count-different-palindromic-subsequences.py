class Solution:
    def countPalindromicSubsequences(self, s: str) -> int:
        @lru_cache(None)
        def dp(left:int ,right:int,res=0):
            for ch in 'abcd':
                l,r=s.find(ch,left,right),s.rfind(ch,left,right)
                res+=0 if l==-1 else 1 if l==r else dp(l+1,r)+2
            return res %1_000_000_007
        return dp(0,len(s))