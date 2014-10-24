/*	Input: 38
	Output: [2,3,5], since 2^2+3^2+5^2 = 38*/


	List<Integer> helper(int target, int max, HashMap<Integer, List<Integer>> map, int magic)
	{
		if(target==0) return new ArrayList<Integer>();
		if(target<0 || max<=0)
		{
			return null;
		}
		int sqrt = (int)Math.sqrt(target);
		int upper = Math.min(sqrt, max);
		if(upper==0)
		{
			return null;
		}

		int key = max*magic+target;
		if(map.containsKey(key))
		{
			return map.get(key);
		}

		List<Integer> rr = helper(target, upper-1, map, magic);
		int min = -1;
		int siz = -1, minCount = 0;
		if(rr==null)
		{
			siz = 0;
			min = Integer.MAX_VALUE;
		}
		else
		{
			siz = rr.size();
			min = rr.size();
		}
		int prod = upper*upper;
		int count = 0;
		while(target - prod>=0)
		{
			target -=prod;
			count++;

			List<Integer> rr_local = helper(target,upper-1, map, magic);
			if(rr_local == null) continue;

			if(rr_local.size()+count<min)
			{

				rr = rr_local;
				minCount = count;
				min = rr.size()+count;
			}
		}
		List<Integer> re = new ArrayList<Integer>(rr);
		for(int i=0;i<minCount;i++)
			re.add(upper);
		map.put(key, re);
		return map.get(key);
	}

	List<Integer> minFactorization(int a)
	{
		List<Integer> re = new ArrayList<Integer>();
		if(a<=0) return re;
		if(a == 1)
		{
			re.add(1); return re;
		}

		int magic = (int)(Math.sqrt(a));

		return helper(a, magic, new HashMap<Integer, List<Integer>>(), a);

	}