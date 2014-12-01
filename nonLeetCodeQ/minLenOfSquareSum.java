/*	Input: 38
	Output: [2,3,5], since 2^2+3^2+5^2 = 38*/

===

Iteration:
====

Cleaner solution:

List<Integer> minLenOfSquareSum(int num)
{
	List<Integer> re = new ArrayList<>();
	if(num<1) return re;

	int[] minimum = new int[num+1];
	int[] pred = new int[num+1];

	Arrays.fill(minimum, Integer.MAX_VALUE);
	Arrays.fill(pred, -1);

	for(int i=1;i<=num;i++)
	{
		int sroot = (int)Math.sqrt(i);
		if(sroot*sroot==i)
		{
			minimum[i] = 1;
			pred[i] = sroot;
		}
		else
		{
			int min = Integer.MAX_VALUE;
			int pre = -1;
			for(int j=sroot; j>0; j--)
			{
				int comp = i-j*j;
				if(minimum[comp]<min)
				{
					min = minimum[comp];
					pre = j;
				}
			}
			minimum[i] = min+1;
			pred[i] = pre;
		}
	}

	return getSequence(pred);
}

List<Integer> getSequence(int[] pred)
{
	List<Integer> re = new ArrayList<>();

	int cur = pred.length-1;
	System.out.println(cur);
	while(pred[cur]!=-1)
	{
		re.add(pred[cur]);
		cur-=(pred[cur]*pred[cur]);
	}
	return re;
}



====
    List<Integer> shortestSquareSum(int N)
    {
        List<Integer> result = new ArrayList<Integer>();

        if(N<0) return result;
        if(N<=1) { result.add(N); return result;}

        int n = (int)(Math.sqrt(N));

        if(n==-1) return result;

        int[][] prefix = new int[n][N];
        int[][] arr = new int[n][N];
        for(int i=0;i<n;i++)
            for(int j=0;j<N;j++)
                arr[i][j] = Integer.MAX_VALUE;
        for(int i=0;i<n;i++)
            for(int j=0;j<N;j++)
                prefix[i][j] = -1;


        for(int i=0;i<N;i++)
        {
            
            for(int j = n-1;j>=0;j--)
            {
                int cur = (j+1)*(j+1);
                int min = Integer.MAX_VALUE;
                int pre = -1;
                if(cur>i+1) continue;
                else if(cur==i+1) { min = 1; pre = -1;}
                else
                {
                    int val = i+1-cur;
                    for(int k = j;k>=0;k--)
                    {
                        if(arr[k][val-1]!= Integer.MAX_VALUE && arr[k][val-1]+1<min)
                        {
                            min = arr[k][val-1]+1;
                            pre = k;
                        }
                    }
                }
                arr[j][i] = min;
                prefix[j][i] = pre;
            }
        }

        int ind=-1;
        int min = Integer.MAX_VALUE;
        for(int i=0;i<n;i++)
            if(arr[i][N-1]<min)
            {
                min = arr[i][N-1];
                ind = i;
            }
        if(min==Integer.MAX_VALUE) return null;

        result = new ArrayList<Integer>();
        int res = N-1;
        while(ind!=-1)
        {
            result.add(ind+1);
            int t = ind;
            ind = prefix[ind][res];
            res = res-(t+1)*(t+1);
        }
        return result;
    }

====

Recursion:

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

	===

	Recursion 2:
	    List<Integer> shortestSquareSum(int N)
    {
        List<Integer> result = new ArrayList<Integer>();

        if(N<0) return result;
        if(N<=1) { result.add(N); return result;}

        int re = -1;
        int l = 1, r = N;
        while(l<=r)
        {
            int mid = l+(r-l)/2;
            if(N/mid == mid) { re = mid; break;}
            else if(N/mid < mid) { r = mid-1; }
            else { re = mid; l = mid+1;}
        }

        if(re==-1) return result;

        int n = re;
        int[] arr = new int[n];
        for(int i=0;i<n;i++)
            arr[i] = (i+1)*(i+1);

        for(int i:arr)
            System.out.print(i+" ");
        System.out.println();
        HashMap<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        return helper(arr, 0, N,map);
    }

    List<Integer> helper(int[] arr, int k, int sum, HashMap<Integer, List<Integer>> map)
    {
        
        if(sum==0) return new ArrayList<Integer>();
        if(k==arr.length || sum<0 || arr[k]>sum) return null;


        int key = arr.length*sum+k;
        if(map.containsKey(key)) return map.get(key);


        List<Integer> list = helper(arr, k+1, sum, map);
        int min = (list==null)?Integer.MAX_VALUE:list.size();

        int count = 0;
        int minCount = 0;
        while(sum-arr[k]>=0)
        {
            count++;
            sum-=arr[k];
            List<Integer> tmp = helper(arr, k+1, sum, map);
            if(tmp!=null && (tmp.size()+count<min||list==null))
            {
                list = tmp;
                min = tmp.size()+count;
                minCount = count;
            }
        }
        
        if(list==null || min==Integer.MAX_VALUE) return null;
        
        List<Integer>re = new ArrayList<Integer>(list);
        int sqt = (int)Math.sqrt(arr[k]);
        for(int i=0;i<minCount;i++)
            re.add(sqt);
        map.put(key, re);

        return re;
    }
