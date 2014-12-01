
/*
第二题，直线上有一个机器人从原点开始移动，每次可以向左移，也可以向右移，移动
n步，再回到原点的概率是多少, 可以写程序实现。*/

===

cleaner solution:

class Solution
{
    static int origin =0, total =0;

    double randomWalk(int n)
    {
        helper(n,0,0);
        return (double)origin/total;
    }

    void helper(int n, int i, int j)
    {
        if(i+j==n)
        {
            if(i==j) origin++;
            total++;
        }
        else
        {
            helper(n, i+1,j);
            helper(n, i,j+1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().randomWalk(3));
    }
}

===
    static class returnType
    {
        int origin;
        int total;
        returnType()
        {
            origin = 0; total = 0;
        }
    }

    returnType helper(int n, int i, int j)
    {
        if(i+j>n) return new returnType();
        int origin = 0;
        int total = 0;
        if(i+j==n)
        {
            if(i==j) origin++;
            total++;
        }
        else
        {
            returnType res1=helper(n,i+1,j);
            returnType res2=helper(n,i,j+1);
            origin = res1.origin+res2.origin;
            total = res1.total+res2.total;
        }

        returnType re= new returnType();
        re.origin = origin;
        re.total = total;
        return re;
    }
