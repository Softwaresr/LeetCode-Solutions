
class Solution {
    public ListNode deleteDuplicates(ListNode head) {

        if (head == null || head.next== null) return head;

        ListNode dummyHead = new ListNode(0,head);
        ListNode prev = dummyHead;

        ListNode current = head;

        while (current!=null){

            if(current.next!=null && current.val==current.next.val){
                while(current.next!=null && current.val == current.next.val){
                    current = current.next;
                }
                prev.next= current.next;
            }
            else{
                prev=prev.next;
            }
            current = current.next;
        }
        return dummyHead.next;
        
    }
}