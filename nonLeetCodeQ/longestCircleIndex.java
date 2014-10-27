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