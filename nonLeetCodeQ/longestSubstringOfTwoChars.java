/*
given a string ,return the longest substring that contains at most two 
characters.
*/
    String longestSubstringOfTwoChars(String s)
    {
        int n = s.length();
        if(n<=2) return s;

        char[] c = s.toCharArray();
        int i=0, j=0;

        int start = 0, end = 0;

        for(;j<n && c[j]==c[i];j++);
        if(j==n) return s;

        char a = c[i], b = c[j];
        for(;j<n;j++)
        {
            if(c[j]==a || c[j]==b) continue;

            if(j-i>end-start+1)
            {
                start = i; end = j-1;

            }

            i=j-1;
            while(i>=0 && c[i]==c[j-1]) i--;
            if(i<0) return s.substring(start, end+1);
            i++;
            a = c[i]; b=c[j];
        } 
        if(j-i>end-start+1) { start = i; end = j-1; }
        return s.substring(start, end+1);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().longestSubstringOfTwoChars("aabbbbbaxbbbaanaaaaaaaaax"));
    }