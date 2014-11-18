
/*
2. Given a list of words, find two strings S & T such that:
    a. S & T have no common character
    b. S.length() * T.length() is maximized
*/


    static class wrapper implements Comparable
    {
        String s;
        wrapper(String _s)
        {
            s = _s;
        }

        public int compareTo(Object o)
        {
            return -(s.length()-((wrapper)o).s.length());
        }
    }

    boolean checkCommon(String S, String T)
    {
        int m = S.length(), n = T.length();
        if(m*n==0) return true;

        int i =0, j=0;
        while(i<m&&j<n)
        {
            if(S.charAt(i)<T.charAt(j)) i++;
            else if(S.charAt(i)>T.charAt(j)) j++;
            else return true;
        }
        return false;
    }

    int findMaxPairStringNoCommonChar(String[] str)
    {
        int n = str.length;
        if(n<=1) return 0;

        wrapper[] tmp = new wrapper[n];
        for(int i=0;i<n;i++)
        {
            char[] t = str[i].toCharArray();
            Arrays.sort(t);
            str[i] = new String(t);
            tmp[i] = new wrapper(str[i]);
        }
        Arrays.sort(tmp);
        for(int i=0;i<n;i++)
            str[i] = tmp[i].s;

        int max = Integer.MIN_VALUE;
        int upper = n;
        for(int i=0;i<upper;i++)
        {
            for(int j=i+1;j<upper;j++)
            {
                if(!checkCommon(str[i],str[j]))
                {
                    if(str[i].length()*str[j].length() >max)
                    {
                        max = str[i].length()*str[j].length();
                        upper = j;
                        break;
                    }
                }
            }
        }
        return max;
    }