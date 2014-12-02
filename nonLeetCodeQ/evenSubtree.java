/*An arbitrary tree. split it into as many subtrees as you can. the number of nodes of the subtree must be even. [nodes with even #nodes in the subtree can be the root of an even-subtree]
*/


===
class Solution
{

static List<TreeNode> re = new ArrayList<>();

static TreeNode split(TreeNode root)
{
    if(root==null) return null;

    if(root.left==null && root.right==null)
    {
    	return null;
    }

    if(root.left==null)
    {
    	if(split(root.right)==null)
    	{
    		re.add(root);
    		return root;
    	}
    	else
    	{
    		root.right=null;
    		return null;
    	}
    }

    if(root.right==null)
    {
    	if(split(root.left)==null)
    	{
    		re.add(root);
    		return root;
    	}
    	else
    	{
    		root.left=null;
    		return null;
    	}
    }

    TreeNode left=split(root.left), right=split(root.right);
    if(left==null && right==null) return null;
    if(left==null)
    {
    	root.right=null;
    	re.add(root);
    	return root;
    }
    if(right==null)
    {
    	root.left=null;
    	re.add(root);
    	return root;
    }
    return null;
}

public static void main(String[] args) {

	TreeNode a1=new TreeNode(0);
	TreeNode a2=new TreeNode(1);
	TreeNode a3=new TreeNode(2);
	TreeNode a4=new TreeNode(3);
	TreeNode a5=new TreeNode(4);
	TreeNode a6=new TreeNode(5);
	a1.left = a2;
	a2.left =a3;
	a2.right=a4;
	a1.right=a5;
	a5.right=a6;
	if(split(a1)==null)
		System.out.println("false");
	for(TreeNode nd:re)
		System.out.println(nd);
}

}
