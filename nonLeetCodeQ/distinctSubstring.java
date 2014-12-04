/*
Output the distinct substring of a given string

eg: banana -- 15;
*/
class Solution{
	static class strComp implements Comparator<String>
	{
		public int compare(String s1, String s2)
		{
			int i=0;
			char[] a = s1.toCharArray(), b=s2.toCharArray();
			while(i<a.length && i<b.length)
			{
				if(a[i]<b[i]) return -1;
				else if(a[i]>b[i]) return 1;
				i++;
			}
			return b.length-a.length;
		}
	}

	int distinctSubstring(String s)
	{
		int n = s.length();
		if(n<2) return n;

		List<String> list = new ArrayList<>();
		for(int i=0;i<n;i++)
		{
			list.add(s.substring(i));
		}

		Collections.sort(list, new strComp());

		int re = list.get(0).length();
		for(int i=1;i<n;i++)
		{
			String pre = list.get(i-1);
			String cur = list.get(i);
			re += (cur.length() - helper(pre, cur));
		}
		return re;
	}

	int helper(String s1, String s2)
	{
		int i=0;
		while(i<s1.length() && i<s2.length())
		{
			if(s1.charAt(i)!=s2.charAt(i)) break;
			i++;
		}
		return i;
	}
	public static void main(String[] args) {
		System.out.println(new Solution().distinctSubstring("banana"));
	}