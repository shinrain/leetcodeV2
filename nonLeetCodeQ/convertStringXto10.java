
/*
3. give an input stream like 1010x0x1, give all the result that transfer x into 0 or 1
*/

    List<String> convertStringXto10(String s)
    {
        List<String> re = new ArrayList<String>();
        int n = s.length();
        if(n==0) return re;

         helper(s,0,new char[n], re);
         return re;
    }
    void helper(String s, int k, char[] r, List<String> re)
    {
        if(k==s.length())
        {
            re.add(new String(r));
            return;
        }

        if(s.charAt(k) =='x')
        {
            r[k] = '0';
            helper(s,k+1,r,re);
            r[k] = '1';
            helper(s,k+1,r,re);
        }
        else
        {
            r[k] = s.charAt(k);
            helper(s,k+1,r,re);
        }
    }