// class Solution {
//     public int removeElement(int[] nums, int val) {
//          int c=0;
//         for(int i=0; i<nums.lenght;i++){
//             if(nums[i]!=val){
//                 nums[c]=nums[i];
//                 count++;


//             }
//         }
//         return count;
//         system.out.println(nums, int val));

        
//     }
// }
// //     static int removeElement(int[] nums, int val){
// //         int c=0;
// //         for(int i=0; i<nums.lenght;i++){
// //             if(nums[i]!=val){
// //                 nums[c]=nums[i];
// //                 count++;


// //             }
// //         }
// //         return count;
// //     }
// // }


class Solution{
    public int removeElement(int[] nums,int val){
        int len = nums.length;
        int i=0;
        while(i<len){
            if(nums[i] == val){
                nums[i]=nums[len - 1];
                len--;

            }
            else{
                i++;
            }

        }
        return len;
    }
}