/*
Second interviewer gave me array, with elements first strictly increasing, then strictly
decreasing. Asked me to find the largest number. 
*/

    int findGlobalMax(int[] a)
    {
        int n = a.length;
        if(n<3) return -1;

        int l = 0, r = n-1;
        while(l<=r)
        {
            int mid = l+(r-l)/2;
            if(mid==0) l++;
            else if(mid==n-1) r--;
            else
            {
                if(a[mid-1]<a[mid] && a[mid+1]<a[mid]) return a[mid];
                else if(a[mid-1]<a[mid]) l =mid+1;
                else r = mid-1;
            }
        }
        return -1;
    }