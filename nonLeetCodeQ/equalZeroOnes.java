/*
Google:

2. an array with 0's and 1's, find in O(n) time and O(1) space the longest 
sequence with equal number of 1's and 0's.

can do it with O(n) time and O(n) space. How to do O(1) space.
*/

import java.util.*;
import java.lang.*;

class Solution
{
	int equalZeroOnes(int[] a)
	{
		int n = a.length;
		if(n<=1)  return 0;

		int[] sum = new int[n];
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0,-1);
		sum[0] = (a[0]==0)?-1:1;
		for(int i=1;i<n;i++)
		{
			if(a[i]==0) sum[i] = sum[i-1]-1;
			else
				sum[i] = sum[i-1]+1;
		}
		int max = 0;

		for(int i=0;i<n;i++)
		{
			if(!map.containsKey(sum[i])) map.put(sum[i],i);
			else
			{
				int local = i-map.get(sum[i]);
				if(local>max) max = local;
			}
		}
		return max;
	}
	public static void main(String[] args) {
		int[] a = {0,0,1,1,1,1,0,0,0,1,1,0};
		System.out.println(new Solution().equalZeroOnes(a));
	}
}
