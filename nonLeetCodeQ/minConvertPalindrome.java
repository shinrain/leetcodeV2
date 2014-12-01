/*
Given a string, convert it into a palindrome with the lease number of insertions possible
*/


===
if just compute the minimum # of insertions, iterative DP is good:

int minConvertPalindrome(String s)
{
	int n = s.length();
	if(n<=1) return 0;

	int[][] map = new int[n][n];
	for(int j=0;j<n;j++)
	{
		for(int i=j-1;i>=0;i--)
		{

			if(s.charAt(i)!=s.charAt(j))
			{
				if(i+1>=j) map[i][j] = 1;
				else
				{
					map[i][j] = Math.min(map[i+1][j], map[i][j-1])+1;
				}
			}
			else
			{
				if(i+1>=j-1) map[i][j] = 0;
				else
					map[i][j] = map[i+1][j-1];
			}

		}
	}
	for(int[] i:map)
	{
		for(int j:i)
			System.out.print(j+" ");
		System.out.println();
	}
	return map[0][n-1];
}




===
    static class Result
    {
        int num;
        String s;
        Result(int _num, String _s)
        {
            num = _num; s=_s;
        }
        public String toString()
        {
            return "{"+num+", "+s+"}";
        }
    }
    String minPalindrome(String s)
    {
        int n = s.length();
        if(n<=1) return s;

        Result re = helper(s, 0, n-1, new HashMap<Integer, Result>());
        System.out.println(s);
        System.out.println(re.num);
        return re.s;
    }

    Result helper(String s, int l, int r, HashMap<Integer, Result> map)
    {
        if(l>r)
        {
            Result re = new Result(0,"");
            return re;
        }
        if(l==r)
        {
            Result re = new Result(0, s.substring(l,l+1));
            return re;
        }

        int key = l*s.length()+r;
        if(map.containsKey(key)) return map.get(key);

        Result re = null;

        Result left = helper(s,l,r-1,map);
        Result right = helper(s,l+1,r,map);

        if(left.num<right.num)
        {
            re = new Result(left.num+1, s.charAt(r)+left.s+s.charAt(r));
        }
        else
        {
              re = new Result(right.num+1, s.charAt(l)+right.s+s.charAt(l));
        }

        if(s.charAt(l)==s.charAt(r))
        {
            Result tmp = helper(s,l+1,r-1,map);

            if(tmp.num<re.num)
            {
                re.num = tmp.num; re.s = tmp.s;
                re.s = s.charAt(l)+re.s+s.charAt(l);
            }
        }

        map.put(key, re);
        return re;
    }
