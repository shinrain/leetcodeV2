Fibonacci_logN

import java.util.*;
import java.lang.*;

class fourNum
{
	int a0, a1, a2, a3;
	fourNum(int _a0, int _a1, int _a2, int _a3)
	{ a0 = _a0; a1=_a1; a2=_a2; a3=_a3;}

	public fourNum multiple(fourNum o)
	{
		fourNum re = new fourNum(0,0,0,0);
		re.a0 = a0*o.a0 + a1*o.a2;
		re.a1 = a0*o.a1 + a1*o.a3;
		re.a2 = a2*o.a0 + a3*o.a2;
		re.a3 = a2*o.a1 + a3*o.a3;
		return re;
	}
}

class Solution
{
	public fourNum helper(int n)
	{
		if(n==0 || n==1) return new fourNum(1,1,1,0);
		fourNum t = helper(n/2);
		if(n%2==0)
		{
			return t.multiple(t);
		}
		else
		{
			return t.multiple(t).multiple(new fourNum(1,1,1,0));
		}
	}

	public int fibonacci(int n)
	{
		if(n<=1) return 1;
		return helper(n).a0;
	}
}
