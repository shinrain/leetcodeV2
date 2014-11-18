
You and opponent alternate picking gold pots out of line. You can pick either from the left 
or the right. All amounts of gold in all pots are know. Write an algorithm to pick the most 
gold. Assume you opponent is using the same algorithm.    

===

int winCoinPicking(int[] a)
    {
        int n = a.length;
        if(n==0) return 0;
        if(n==1) return a[0];

        return helper(a,0,n-1,new HashMap<Integer, Integer>());
    }

    int helper(int[] a, int l, int r, HashMap<Integer, Integer> map)
    {
        if(l>r) return 0;
        if(l==r) return a[l];

        int k = l*a.length+r;
        if(map.containsKey(k)) return map.get(k);
        int re = Math.max(a[l]+Math.min(helper(a,l+1,r-1,map), helper(a,l+1,r,map)) , a[r]+Math.min(helper(a,l,r-2,map), helper(a,l+1,r-1,map)));

        map.put(k,re);
        return re;
    }