
Given a binary tree, find the largest Binary Search Tree (BST), where largest 
means BST with largest number of nodes in it. The largest BST may or may not 
include all of its descendants.

-----


class returnType
{
	int countNode;
	int maxNode;
	TreeNode largestBST;
	TreeNode child;

	returnType(int _countNode, int _maxNode, TreeNode _largestBST, TreeNode _child)
	{
		countNode  = _countNode;
		_maxNode = maxNode;
		_largestBST = largestBST;
		_child = child;
	}
}

class Solution
{

	TreeNode helper(TreeNode p, int min, int max, returnType cur)
	{
		if(p==null)
		{
			return new returnType(0, cur.maxNode, cur.largestBST, cur.child);
		}

		if(min<p.val && p.val<max)
		{
			returnType left = helper(p.left, min, p.val, cur);
			TreeNode re = new TreeNode(p.val);
			re.left = (left.countNode)?null:left.child;

			returnType right = helper(p.right, p.val, max, cur);
			re.right = (right.countNode)?null:right.child;

			cur.child = re;
			int total = left.countNode+right.countNode+1;
			if(total > cur.maxNode)
			{
				cur.maxNode = total;
				cur.largestBST = re;
			}
			return new returnType(total, cur.maxNode, cur.largestBST, cur.child);
		}
		else
		{
			returnType re = helper(p, Integer.MIN_VALUE Integer.MAX_VALUE, cur);
			re.countNode = 0;
			return re;
		}
	}

	TreeNode findLargestBST(TreeNode root)
	{
		returnType re =  helper(root, Integer.MIN_VALUE, Integer.MAX_VALUE, new returnType(0, Integer.MIN_VALUE, null, null));
		return re.largestBST;
	}

}
