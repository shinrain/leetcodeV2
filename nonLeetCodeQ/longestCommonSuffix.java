
/*
longest common suffix of two linked list
*/
    ListNode reverse(ListNode head)
    {
        if(head==null || head.next ==null) return head;
        ListNode cur = head;
        head = null;
        while(cur!=null)
        {
            ListNode t = cur.next; t.next = null;
            cur.next = head; head = cur;
            cur = t;
        }
        return head;
    }

    ListNode longestCommonSuffix(ListNode n1, ListNode n2)
    {
        if(n1==null || n2==null) return null;

        n1 = reverse(n1);
        n2 = reverse(n2);

        ListNode head = null, tail = null;
        while(n1!=null && n2!=null)
        {
            if(n1.val==n2.val)
            {
                ListNode t = new ListNode(n1.val);
                if(head==null)
                {
                    head = t; tail = t;
                }
                else
                {
                    tail.next = t; tail  = t;
                }
            }
            n1 = n1.next; n2 = n2.next;
        }
        return reverse(head);
    }
