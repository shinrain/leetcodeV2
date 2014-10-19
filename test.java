import java.util.*;
import java.lang.*;


class Solution {

	
}




class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
      public String toString()
      {
      		String re = "("+val;
      		String _left = (left==null)?"()":left.toString();
      		String _right = (right==null)?"()":right.toString();
      		return re+", "+_left+", "+_right+")";
      }
  }
class TreeLinkNode {
     int val;
     TreeLinkNode left, right, next;
     TreeLinkNode(int x) { val = x; }
 }

 class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }

      public String toString()
      {
      	String re = Integer.toString(val);
      	if(next!=null) re= re+"->"+next.toString();
      	return re;
      }
  }

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

class Node
{
	String str;
	Node(String _str){ str = _str;}
}

class Interval {
     int start;
     int end;
     Interval() { start = 0; end = 0; }
     Interval(int s, int e) { start = s; end = e; }

     public String toString()
     {
     	return "[ "+start+", "+end+"]";
     }
}

 class Point {
     int x;
     int y;
     Point() { x = 0; y = 0; }
    Point(int a, int b) { x = a; y = b; }
}
