
/*
In a given binary search tree, find the number of elements that lie in a given range.
*/


    void findNumInRange(TreeNode root, int min, int max)
    {
        List<Integer> re = new ArrayList<Integer>();
        helper(root, min, max, re);
        return re;
    }

    void helper(TreeNode root, int min, int max, List<Integer> re)
    {
        if(root==null) return;
        if(root>=max) helper(root.left, int min, int max, re);
        if(root<=min) helper(root.right, min, max, re);
        re.add(root);
        helper(root.left, min, root.val, re);
        helper(root.right, root.val, max, re);
    }