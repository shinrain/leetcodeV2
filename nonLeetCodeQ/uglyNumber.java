/*
Print out all the ugly number sequence below a certain number

Ugly Number is the product of only 2,3,5

Example: 1 2 3 4 5 6 8 9 10 12 
*/


    void uglyNumber(int N)
    {
        if(N<=0){ System.out.println(0); }
        else if(N==1) { System.out.println(1);}
        else
        {
            int[] re = new int[N];
            int cur = 0;
            int Two = 0, Three = 0, Five = 0;
            re[cur++] = 1;
            for(int i=1;i<N;i++)
            {
                int min = Math.min(re[Two]*2, Math.min(re[Three]*3,re[Five]*5));
                re[cur++] = min;
                while(re[Two]*2 <= min) Two++;
                while(re[Three]*3 <= min) Three++;
                while(re[Five]*5 <= min) Five++; 
            }
            for(int i:re)
                System.out.print(i+" ");
            System.out.println();
        }
    }
    public static void main(String[] args) {
        new Solution().uglyNumber(100);
    }
}
