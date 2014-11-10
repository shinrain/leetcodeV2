
/*
其他都比较常规，有一道图的题目，一个 n*n 矩阵，每个房间可能是封闭的房间，可
能是警察，可能是开的房间，封闭的房间不能过，返回一个 n*n矩阵， 每一个元素是
最近的警察到这个房间的最短距离。
*/


==

    int[][] findPolice(int[][] houses)
    {
        int m = houses.length;
        if(m==0) return null;
        int n = houses[0].length;
        if(n==0) return null;

        int[][] re = new int[m][n];
        boolean[][] visit = new boolean[m][n];
        for(int i=0;i<m;i++)
            for(int j=0;j<n;j++)
                if(houses[i][j] ==-1) visit[i][j] = true;
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(houses[i][j] == 0)
                {
                    visit[i][j] = true;
                    re[i][j] = helper(houses, i, j, visit);
                    visit[i][j] = false;
                }
            }
        }
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
                System.out.print(re[i][j]+" ");
            System.out.println();
        }
        return re;
    }

    int helper(int[][] houses, int i, int j, boolean[][] visit)
    {
        if(houses[i][j]==1) return 0;
        if(houses[i][j]==-1) return -1;
        int min = Integer.MAX_VALUE;
        if(i-1>=0 && !visit[i-1][j] )
        {
            visit[i-1][j] = true;
            int t = helper(houses, i-1, j, visit);
            if(t!=-1 && t+1<min)
            {
                min = t+1;
            }
            visit[i-1][j] =false;
        }

        if(i+1<houses.length && !visit[i+1][j])
        {
            visit[i+1][j] = true;
            int t = helper(houses, i+1, j, visit);
            if(t!=-1 && t+1<min)
            {
                min = t+1;
            }
            visit[i+1][j] = false;
        }
        if(j-1>=0 && !visit[i][j-1])
        {
            visit[i][j-1] = true;
            int t = helper(houses, i, j-1, visit);
            if(t!=-1 && t+1<min)
            {
                min = t+1;
            }
            visit[i][j-1] = false;
        }
        if(j+1<houses[0].length && !visit[i][j+1])
        {
            visit[i][j+1] = true;
            int t = helper(houses, i, j+1, visit);
            if(t!=-1 && t+1<min)
            {
                min = t+1;
            }
            visit[i][j+1] = false;
        }
        if(min==Integer.MAX_VALUE) min = -1;
        return min;
    }
   