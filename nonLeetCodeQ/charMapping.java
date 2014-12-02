/*3. 有一个字典因为某种原因每个字符都被替换成一个别的字符了（但还是一一对应)，
但是单词的顺序没有改变，比如
cat
coffee
common
变成了
dkc
dbhhzz
dbllbq
让找出的这个替换的规则（guaranteed to have a unique one)
*/

import java.util.*;
import java.lang.*;

class Solution
{

	HashMap<Character, Character> charMapping(String[]a, String[]b)
	{
		HashMap<Character, Character> map = new HashMap<>();
		int n = a.length;
		if(n==0) return map;

		for(int i=0;i<n;i++)
		{
			for(int j=0;j<a[i].length();j++)
			{
				char aa = a[i].charAt(j);
				char bb = b[i].charAt(j);

				if(!map.containsKey(aa)) {map.put(aa,bb);}
				if(map.get(aa)!=bb) return null;
				if(!map.containsKey(bb)) {map.put(bb,aa);}
				if(map.get(bb)!=aa) return null;
			}
		}
		return map;
	}

	public static void main(String[] args) {
		String[] a = {"cat","coffee","common"};
		String[] b = {"dkc","dbhhzz","dbllbq"};

		System.out.println(new Solution().charMapping(a,b));
	}
}