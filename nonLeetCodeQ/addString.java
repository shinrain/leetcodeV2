/*Implement add: 

"1000", "-100" == "900"

*/
	String addString(String s1, String s2)
	{
		s1=s1.trim();
		s2=s2.trim();
		int m = s1.length(), n=s2.length();
		if(m==0) return s2;
		if(n==0) return s1;
		boolean f1=(s1.charAt(0)=='-'), f2=(s2.charAt(0)=='-');

		if(s1.charAt(0)=='+'||s1.charAt(0)=='-') s1=s1.substring(1);
		if(s2.charAt(0)=='+'||s2.charAt(0)=='-') s2=s2.substring(1);

		String re = "";
		if((f1&&f2)||(!f1 && !f2))
		{
			re = helper(s1,s2, true);
			if(f1) re = "-"+re;
		}
		else
		{
			re = (f1)?helper(s2,s1,false):helper(s1,s2,false);
		}
		return re;
	}

	String helper(String s1, String s2, boolean add)
	{
		String re = "";
		int i=s1.length()-1, j=s2.length()-1;
		int carry=0;
		while(i>=0 || j>=0)
		{
			if(i<0)
			{
				int b = (!add)?('0'-s2.charAt(j)):(s2.charAt(j)-'0');
				int r = b+carry;
				if(r>=0)
				{
					re = Integer.toString(r%10)+re;
					carry = r/10;
				}
				else
				{
					re = Integer.toString(r+10)+re;
					carry = -1;
				}
				j--;
			}
			else if(j<0)
			{
				int a = s1.charAt(i--)-'0';
				int r = a+carry;
				if(r>=0)
				{
					re = Integer.toString(r%10)+re;
					carry = r/10;
				}
				else
				{
					re = Integer.toString(r+10)+re;
					carry = -1;
				}
			}
			else
			{
				int a = s1.charAt(i)-'0';
				int b = (!add)?('0'-s2.charAt(j)):(s2.charAt(j)-'0');
				int r = a+b+carry;
				if(r>=0)
				{
					re = Integer.toString(r%10)+re;
					carry = r/10;
				}
				else
				{
					re = Integer.toString(r+10)+re;
					carry = -1;
				}
				i--;
				j--;
			}
		}
		while(re.length()!=0 && re.charAt(0)=='0') re=re.substring(1);
		if(re.length()==0) return "0";
		if(carry==-1) re="-"+re;
		return re;
	}

	public static void main(String[] args) {
		System.out.println(new Solution().addString("-1000","-1000"));
	}
}
