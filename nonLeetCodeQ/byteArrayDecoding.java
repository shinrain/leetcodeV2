
/*
3. 记不清楚了，比较少见的一道题，0, 1开头byte，判断最后一个字符是一个byte还
是两个byte的问题。
*/



    int checkByteArray(Byte[] a)
    {
        int n = a.length;
        if(n==0) return -1;
        if(n==1) return 1;

        if((a[n-1]&(1<<7))!=0) return 2;
        for(int i=n-2;i>0;i--)
        {
            if( (a[i]&(1<<7))==0 ) return ((n-i)%2==1)?2:1;
        }
        if( (a[0]&(1<<7))==0 ) return (n%2==1)?2:1;
        else
            return 2;
    }