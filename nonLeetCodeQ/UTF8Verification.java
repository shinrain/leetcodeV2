/*
Designed a method to verify utf-8 characters
*/

boolean verify(String s)
{
	char[] c = s.toCharArray();

	int n = c.length;
	if(n==0) return false;

	int[] re = new int[n];

	for(int i=0;i<n;i++)
	{
		for(int j=i;j>=0;j--)
		{
			if(i-j+1>8) break;
			if(check(c[j], i-j+1) && (j==0 || re[j-1]==1)) re[i]=1;
		}
	}
	return re[n-1]==1;
}

boolean check(char c, int i)
{
	for(int j=7;j>=0;j--)
	{
		if( ((1<<7)&c) !=0 )
		{
			i--;
		}
		else break;
	}
	return i==0;
}
