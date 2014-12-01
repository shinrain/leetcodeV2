/*
索引 0 1 2 3 4
值   3 2 1 4 0
数组的值是下次跳的索引位置，这样的话数组有环，比如 0 -> 3 -> 4 -> 0  1 -> 2 
-> 1， 求最长环的长度.
*/

    int detect(int next, HashMap<Integer, Integer> map)
    {
        int re = 1;
        int cur = next;
        while(map.containsKey(cur))
        {
            cur = map.get(cur);
            re++;
            if(cur == next) break;
        }
        return re;
    }

    int longestCircle(HashMap<Integer, Integer> map)
    {
        HashSet<Integer> set = new HashSet<Integer>();
        int max = 0;

        while(map.size()!=0)
        {
            set.clear();
            for(int i:map.keySet())
            {
                if(set.contains(i)) continue;

                int cur = i;
                while(!set.contains(cur) && map.keySet().contains(cur))
                {
                    set.add(cur);
                    int next = map.get(cur);
                    if(!set.contains(next)) cur = next;
                    else
                    {
                        int local = detect(next, map);
                        if(local > max) max = local;
                        for(int j:set)
                            map.remove(j);
                        break;
                    }
                }
                break;
            }
        }
        return max;
    }


===
Another cleaner solution:

		int longestCircleIndex(int[] a)
	{
		int n = a.length;
		if(n<=1) return n;

		Map<Integer, Integer> map = new HashMap<>();
		for(int i=0;i<n;i++)
		{
			map.put(i, a[i]);
		}

		
		int max = 0;


		Set<Integer> set = new HashSet<>(map.keySet());
		for(int i:set)
		{
			if(!map.containsKey(i)) continue;
			int local = dfs(map, i);
			if(local > max) max = local;
		}
		return max;
	}

	int dfs(Map<Integer, Integer> map, int i)
	{
		if(!map.containsKey(i)) return 0;
		int re = 0;
		int cur = i;
		int local = 0;
		while(!map.isEmpty())
		{
			if(!map.containsKey(cur)) break;
			local++;
			int t = cur;
			cur = map.get(cur);
			map.remove(t);
			if(cur==i)
			{
				if(re<local) re = local;
				break;
			}
		}
		return re;
	}

