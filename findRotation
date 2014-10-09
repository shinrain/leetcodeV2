Given a rotated array, find the number of rotation

======

	public int findTotate(int[] a)
	{
		int n = a.length;
		if(n<=1) return 0;

		int l = 0, r = n-1;
		int re = -1;
		while(l<r)
		{
			int mid = l+(r-l)/2;
			if(a[mid]>a[l] && a[mid]>=a[r])
			{
				re = mid;
				l = mid;
			}else if(a[mid]<a[l] && a[mid]<=a[r])
			{
				re=mid;
				r = mid;
			}
			else
			{
				l++;
			}
		}
		return (re==-1)?0:re;
 	}