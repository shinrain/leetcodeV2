/*如何找比一个int大的下一个palindrome。 Write a function which returns the next palindrome greater than the given number n. e.g., f(231) = 232; f(999) = 1001; f(111) = 121
*/
===
class Solution
{
	static int[] nextPalind(int[] a)
	{
		int n = a.length;
		int[] re = new int[n];
		if(n==1)
		{
			re[0] = a[0]+1; 
			return correct(re);
		}

		boolean flg = true;
		int l = 0, r =n-1;
		while(l<=r)
		{
			if(a[l]!=a[r]) break;
			l++;r--;

		}
		if(l>r)
		{
			int carry = 1;
			for(int i=n-1;i>=0;i--)
			{
				a[i]+=carry;
				carry=a[i]/10;
				a[i]%=10;
			}
			if(carry!=0)
			{
				int[] b = new int[n+1];
				b[0]=carry;
				for(int i=0;i<n;i++)
					b[i+1] =a[i];
				return nextPalind(b);
			}
			return nextPalind(a);
		}
		l=0;r=n-1;
		while(l<=r)
		{
			if(a[l]==a[r])
			{
				if(l+1==r && flg)
				{
					a[l]++;a[r]++;
				}
				else if(l==r && flg)
					a[l]++;
			}
			else if(a[l]>a[r])
			{
				a[r]=a[l]; flg=false;
			}
			else
			{
				a[r]=a[l]; flg=true;
			}
			l++;r--;
		}
		return correct(a);
	}

	static int[] correct(int[] re)
	{

		int i, j;
		int n=re.length;
		if(re.length%2==0)
		{
			i=(re.length-1)/2;
			j=i+1;
		}
		else
		{
			i=re.length/2;
			j=i;
		}
		int carry =0;
		while(i>=0&&j<n)
		{
			re[i]+=carry;
			if(i!=j) re[j]+=carry;
			if(re[i]>=10)
			{
				re[i]%=10;
				re[j]%=10;
				carry=1;
			}
			else
			{
				carry=0;
			}
			i--;j++;
		}
		if(carry==1)
		{
			int[] a = new int[n+2];
			a[0]=a[n+1]=1;
			for(i=0;i<n;i++)
			{
				a[i+1]=re[i];
			}
			return a;
		}
		return re;
	}

	public static void main(String[] args) {
		// int[] num = {9, 4, 1, 8, 7, 9, 7, 8, 3, 2, 2};
		int[] num = {2,3,1};
		// f(231) = 232; f(999) = 1001; f(111) = 121
		for(int i:nextPalind(num))
			System.out.print(i+" ");
		System.out.println();

		int[] num1 = {9,9,9};
		for(int i:nextPalind(num1))
			System.out.print(i+" ");
		System.out.println();

		int[] num2 = {1,1,1};
		for(int i:nextPalind(num2))
			System.out.print(i+" ");
		System.out.println();

	}
}