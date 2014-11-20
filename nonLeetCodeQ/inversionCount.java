/*
   Count inversion number in unsorted array:
   {3,1,2} -> 2, since {3,1}, {3,2}
*/


    int merge(int[] a, int l, int r, int[] c)
    {
        if(l>=r) return 0;
        int mid = l +(r-l)/2;
        int i=l, j = mid+1, k=0;
        int left = merge(a,l,mid,c);
        int right = merge(a,mid+1,r,c);
        int inversion = 0;
        while(i<=mid&&j<=r)
        {
            if(a[i]<=a[j])
            {
                c[k++] = a[i++];
            }
            else
            {
                c[k++] = a[j++];
                inversion+=(j-mid);
            }
        }
        if (i<=mid) c[k++] = a[i++];
        while(i<=mid)
        {
            c[k++] = a[i];
            inversion += (r-mid);
            i++;
        }
        while(j<=r) 
        {
            c[k++] = a[j++];
            
        }
        i=l;

        for(int in=0; in<k;in++)
        {
            a[i++] = c[in];
        }
        return inversion+left+right;
    }
