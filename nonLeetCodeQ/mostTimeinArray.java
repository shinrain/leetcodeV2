
/*
find out in a sorted array which number appears the most times
*/

    int mostTimeinArray(int[] a)
    {
        int n = a.length;
        if(n<=1) return n;

        int max = 0;
        int i = 0;
        while(i<n)
        {

            int l = i, r = n-1;
            int re = -1;
            while(l<=r)
            {
                int mid = l+(r-l)/2;
                if(a[mid]==a[i])
                {
                    re = mid; l = mid+1;
                }
                else if(a[mid]>a[i]) r = mid-1;
                else l = mid+1;
            }
            if(re==-1) break;
            if(re+1-i>max) max = re+1-i;
            i = re+1;

        }
        return max;
    }