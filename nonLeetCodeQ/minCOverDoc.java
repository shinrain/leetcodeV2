/*
2. Give a list of documents, find the minimal document set that can cover 
all the characters in all the documents, like
“a b c h j”,  "c d”, “a b c” “a f g” “a h j”
the result should be "a b c h j" "c d" and "a f g"
*/

import java.util.*;
import java.lang.*;

class Solution
{
	static class lenComp implements Comparator<String>
	{
		public int compare(String s, String p)
		{
			return s.length()-p.length();
		}
	}
	HashSet<String> minCoverDoc(String[] str)
	{
		HashSet<String> re = new HashSet<>();
		HashSet<Character> set = new HashSet<>();
		for(String s:str)
			for(char c:s.toCharArray())
				set.add(c);
		Arrays.sort(str, new lenComp());

		while(!set.isEmpty())
		{
			int max = 0;
			HashSet<Character> tmp = null;
			String tStr = "";
			for(String s:str)
			{
				if(!re.contains(s))
				{
					HashSet<Character> r = helper(new HashSet<Character>(set), s);
					if(r.size()>max)
					{
						max = r.size();
						tmp = r;
						tStr = s;
					}
				}
			}
			if(tmp==null) break;
			re.add(tStr);
			for(char c:tmp) set.remove(c);
		}
		return re;
	}

	HashSet<Character> helper(HashSet<Character> set, String s)
	{
		HashSet<Character> newSet = new HashSet<>();
		for(char c:s.toCharArray())
		{
			if(set.contains(c)) newSet.add(c);
		}
		return newSet;
	}

	public static void main(String[] args) {
		String[] a = {"abchj","cd", "abc", "afg","ahj"};

		System.out.println(new Solution().minCoverDoc(a));
	}
}