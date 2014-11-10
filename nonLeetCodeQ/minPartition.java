/*
Given n numbers (P1,P2,P3,.....Pn). Divide them in m contiguous partitions such that the sum of the maximum is minimum.
For e.g. (5,1,4,2,3) and m =2
then (5,1)(4,2,3)  
*/



    List<List<Integer>> minPartition(int[] a, int m)
    {
        int n = a.length;
        if(n<m) return 0;

        int[][] min = new int[n][m];
        int[][] prefix = new int[n][m];

        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++)
            {
                min[i][j] = Integer.MAX_VALUE;
                prefix[i][j] = -1;
            }
       
        int sum = a[0];

        min[0][0] = a[0];
        for(int i=1;i<n; i++)
            min[i][0] = min[i-1][0]+a[i];

        for(int i=1;i<n;i++)
        {

            sum+=a[i];
            for(int j=1;j<m&&j<=i;j++)
            {
                int sub = 0; int pre = -1;
                int minimum = Integer.MAX_VALUE;
                for(int k = i;k>=j;k--)
                {
                    sub+=a[k];

                    if(Math.max(sub, min[k-1][j-1]) < minimum)
                    {
                        minimum = Math.max(sub, min[k-1][j-1]);
                        pre = k-1;
                    }
                }
                min[i][j] = minimum;
                prefix[i][j] = pre;
            }
        }
        List<List<Integer>> re = new ArrayList<List<Integer>>();
        int cur = n-1;
        int count = m-1;
        while(cur!=-1)
        {
            int pre = prefix[cur][count];
            List<Integer> rr = new ArrayList<Integer>();
            for(int i = pre+1;i<=cur;i++)
                rr.add(a[i]);
            re.add(rr);
            count--;
            cur = pre;
        }
        return re;
    }