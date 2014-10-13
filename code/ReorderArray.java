Reorder array, so that a[i-1]<a[i]>a[i+1]


===

	public void selection(int[] a, int k, int l, int r)
	{
		if(l+k>r) return;
		int val = a[l];

		int i = l-1, j = r+1;
		while(i<j)
		{
			while(++i<j && a[i]<=val);
			while(i<--j && a[j] >val);
			if(i>=j) break;

			int t = a[i];
			a[i] = a[j];
			a[j] = t;
		}
		int t = a[j];
		a[j] = a[l];
		a[l] = t;
		if(j-l == k) return;
		else if(j-l<k)
		{
			selection(a, k+l-j, j,r);
		}
		else
		{
			selection(a,k,l,j);
		}
	}

	void reverse(int[] a, int l, int r)
	{
		while(l<r)
		{
			int t = a[l]; a[l] = a[r]; a[r] = t;
			l++; r--;
		}
	}
	public void reorder(int[] a, int l, int r)
	{
		if(r-l+1<=2) return;

		int mid = l+(r-l)/2;

		int left = l +(mid-l)/2;
		int num = left-l+1;
		int right = mid+num;

		reverse(a, left+1, mid);
		reverse(a, mid+1, right);
		reverse(a,left+1, right);

		reorder(a, l, l+2*num-1);
		reorder(a, l+2*num, r);
	}

	public void reOrder(int[] a)
	{
		int n = a.length;
		if(n<=1) return;

		selection(a, (n-1)/2, 0, n-1);
		reorder(a,0,n-1);

	}