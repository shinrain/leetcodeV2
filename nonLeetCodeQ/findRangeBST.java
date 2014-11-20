
/*
In a given binary search tree, find the number of elements that lie in a given range.
*/

List<Integer> findNumInRange(TreeNode root, int min, int max)
{
	List<Integer> re = new ArrayList<>();
	helper(root, min, max);
	return re;
}

void helper(TreeNode root, int min, int max, List<Integer> re)
{
	if(root==null) return;
	else if(root.val<=min) helper(root.right, min, max, re);
	else if(root.val>=max) helper(root.left, min, max, re);
	else
	{
		re.add(root.val);
		helper(root.right, min, max, re);
		helper(root.left, min, max, re);
	}
}
