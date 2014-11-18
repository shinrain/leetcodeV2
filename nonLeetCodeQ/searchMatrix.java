/*
Given a sorted matrix where the number below and right of you will always be bigger, write an algorithm to find if a particular number 
exist in the matrix
*/


    boolean searchMatrix(int[][] a, int val)
    {
        int m = a.length;
        if(m==0) return false;
        int n = a[0].length;
        if(n==0) return false;

        int mm = 0, nn = n-1;
        while(mm<m&&nn>0)
        {
            int l = mm, r = m-1;
            int re1 = -1;
            while(l<=r)
            {
                int mid = l+(r-l)/2;
                if(a[mid][nn] == val) return true;
                else if(a[mid][nn] >val)
                {
                    re1 = mid; r = mid-1;
                }
                else l = mid+1;
            }
            if(re1==-1) return false;

            l = 0; r = nn;
            int re2 = -1;
            while(l<=r)
            {
                int mid = l+(r-l)/2;
                if(a[re1][mid]==val) return true;
                else if(a[re1][mid] > val)
                {
                    r = mid-1;
                }
                else
                {
                    re2 = mid;
                    l = mid+1;
                }
            }
            if(re2==-1) return false;

            mm = re1; nn = re2;
        }
        return false;
    }