/*1. Given a array of integers , find 3 indexes i,j,k such that, i<j<k and a[i] < a[j] < a[k]. Best possible is a O(n) algorithm.
*/

import java.util.*;
import java.lang.*;

class Solution
{
	List<Integer> threeIncreaseElem(int[] a)
	{
		List<Integer> re = new ArrayList<>();
		int n = a.length;
		if(n==0) return re;

		int[] min = new int[n];

		min[0] = a[0];
		for(int i=1;i<n;i++)
		{
			min[i] = Math.min(min[i-1], a[i]);
		}

		int max = a[n-1];
		for(int i=n-2;i>=0;i--)
		{
			if(max<=a[i]) max = a[i];
			else
			{
				if(a[i]>min[i])
				{
					re.add(min[i]); re.add(a[i]);re.add(max);
					return re;
				}
			}
		}
		return re;
	}
	public static void main(String[] args) {
		int[] a = {2,2,1,2,3,2,1,32};
		System.out.println(new Solution().threeIncreaseElem(a));
	}
}