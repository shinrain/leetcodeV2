
/* Q.Given an input string S write a function which returns true if it satisfies S = nT.
  Basically you have to find if a given string can be represented from a substring by 
  iterating it “n” times. n >= 2
*/


    boolean check(String p, String s)
    {
        int n = p.length(), m = s.length();
        if(n>m) return false;

        for(int i=0;i+n<=m;i+=n)
        {
            if(!p.equals(s.substring(i,i+n))) return false;
        }
        return true;
    }

    int destructString(String s)
    {
        int n = s.length();
        if(n<=1) return -1;

        char piv = s.charAt(0);
        for(int i=1;i<n;i++)
        {
            if(piv==s.charAt(i) && n%(i-0)==0 && check(s.substring(0,i), s.substring(i)))
                return n/i;
        }
        return -1;
    }