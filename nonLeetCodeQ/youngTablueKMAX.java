给A，B 2个array，里面都是integer，已经排好序了，由大到小，他们的长度都是N

现在从A和B里各选出一个数，总成一个sum，请返回前N个最大的sum




class Pair implements Comparable
{
	int val, i, j;
	Pair(int _i, int _j, int ai, int bj)
	{
		val = ai+bj;
		i = _i; j = _j;
	}

	public int compareTo(Object o)
	{
		Pair p = (Pair)o;
		return val-p.val;
	}
}

class Solution
{
	public List<Integer> youngTablueKMAX(int[] a, int[] b, int k)
	{
		List<Integer> re = new ArrayList<Integer>();

		int m = a.length, n = b.length;
		if(k<=0 || k>=m*n) return re;

		if(k==1)
		{
			re.add(a[0]+b[0]);
			return re;
		}

		PriorityQueue<Pair> q = new PriorityQueue<Pair>();
		for(int i=0;i<n;i++)
		{
			q.add(new Pair(0,i,a[0], b[i]));
		}
		int count = 0;
		while(!q.isEmpty() && count<=k)
		{
			Pair cur = q.poll();
			if(cur == null) break;
			re.add(cur.val);

			cur.i++;
			if(cur.i == m) continue;

			q.add(new Pair(cur.i, cur.j, a[cur.i], b[cur.j]));
		}
		return re;
	}
}
