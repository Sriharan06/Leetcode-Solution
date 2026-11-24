class Solution { // solution class expected by LeetCode
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) { // take the two reversed linked lists as input
        ListNode dummy = new ListNode(0); // dummy head lets me build the result without special casing the first node
        ListNode current = dummy; // current always points to the last node in the result list
        int carry = 0; // carry stores overflow from the previous digit addition

        while (l1 != null || l2 != null || carry != 0) { // keep going while there are digits left in either list or a carry remains
            int x = (l1 != null) ? l1.val : 0; // read the current digit from l1, or use 0 if l1 is exhausted
            int y = (l2 != null) ? l2.val : 0; // read the current digit from l2, or use 0 if l2 is exhausted
            int sum = x + y + carry; // add both digits and the carry from the previous step

            carry = sum / 10; // the new carry is the tens place of the sum
            current.next = new ListNode(sum % 10); // the current result digit is the ones place of the sum
            current = current.next; // move current forward to the node I just created

            if (l1 != null) { // only advance l1 if it still has nodes
                l1 = l1.next; // move to the next digit in l1
            }
            if (l2 != null) { // only advance l2 if it still has nodes
                l2 = l2.next; // move to the next digit in l2
            }
        }

        return dummy.next; // skip the dummy head and return the real start of the result list
    }
}