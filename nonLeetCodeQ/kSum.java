/*Given n distinct positive integers, integer k (k <= n) and a number target.

Find k numbers where sum is target. Calculate how many solutions there are?

Example
Given [1,2,3,4], k=2, target=5. There are 2 solutions:

[1,4] and [2,3], return 2.*/

===

DP solution:

	public int solutionKSum(int A[],int k,int target)
	{
		int n = A.length;
		if(n==0) return 0;

		int[][][] map = new int[n+1][k+1][target+1];
		for(int i=1;i<=n;i++)
		{
			for(int j=1;j<=i;j++)
			    if(A[j-1]<=target)
				    map[i][1][A[j-1]]++;

		}
		for(int i=1;i<=n;i++)
		{
			for(int j=2;j<=Math.min(i,k);j++)
			{
				for(int t=1;t<=target;t++)
				{
					map[i][j][t] += map[i-1][j][t];

					if(t-A[i-1]>0)
					{
						map[i][j][t]+=(map[i-1][j-1][t-A[i-1]]);
					}
				}
			}
		}
		return map[n][k][target];
	}
