/*
一个数，比如7可以拆成 1+3+3 或者3+4。 求拆成的因子相乘积最大的那个值。
*/

import java.util.*;
import java.lang.*;

class Solution
{
	int maxProdComposition(int num)
	{
		if(num<=1) return num;
		int[] re = new int[num];
		for(int i=0;i<num;i++)
		{
			int max = Integer.MIN_VALUE;
			for(int j=i;j>=0;j--)
			{
				if(j==0) max = Math.max(max, i+1);
				else max = Math.max(max, (i-j+1)*re[j-1]);
			}
			re[i] = max;
		}
		return re[num-1];
	}

	public static void main(String[] args) {
		Solution sol = new Solution();
		System.out.println(sol.maxProdComposition(7));
	}

}