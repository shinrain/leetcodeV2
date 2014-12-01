/*
Given n numbers (P1,P2,P3,.....Pn). Divide them in m contiguous partitions such that the sum of the maximum is minimum.
For e.g. (5,1,4,2,3) and m =2
then (5,1)(4,2,3)  
*/



    List<List<Integer>> minPartition(int[] a, int m)
    {
        List<List<Integer>> re = new ArrayList<>();

        int n = a.length;
        if(n==0) return re;

        if(m==1)
        {
            List<Integer> r = new ArrayList<>();
            for(int i:a) r.add(i);
            re.add(r);
            return re;
        }

        int[][] min = new int[m][n], pre = new int[m][n];
        for(int[] i:min)
            Arrays.fill(i, Integer.MAX_VALUE);
        for(int[] i:pre)
            Arrays.fill(i, -1);

        min[0][0] = a[0];
        for(int i=1;i<n;i++)
            min[0][i] = Math.max(min[0][i-1], a[i]);

        for(int i=1;i<m;i++)
        {
            for(int j=i;j<n;j++)
            {
                int minimum = Integer.MAX_VALUE, prev = -1;
                int local = a[j];
                for(int k=j;k>=i;k--)
                {
                    local = Math.max(local, a[k]);
                    if(local< minimum-min[i-1][k-1])
                    {
                        minimum = local+min[i-1][k-1];
                        prev = k-1;
                    }
                }
                min[i][j] = minimum;
                pre[i][j] = prev;
            }
        }
        return getSequence(a, pre);
    }

    List<List<Integer>> getSequence(int[] a, int[][] pre)
    {
        int cur = a.length-1, m = pre.length;
        List<List<Integer>> re = new ArrayList<>();
        while(pre[m-1][cur]!=-1)
        {
            List<Integer> r = new ArrayList<>();
            int t = pre[m-1][cur];
            for(int i=t+1;i<=cur;i++) r.add(a[i]);
            re.add(0,r);
            cur = pre[m-1][cur];
            m--;
        }
        List<Integer> r = new ArrayList<>();
        for(int i=0;i<=cur;i++)
        {
            
            r.add(a[i]);
        }
        re.add(0,r);
        return re;
    }

==========
/*
Below is wrong question.

minimum of maximum of sumation:

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