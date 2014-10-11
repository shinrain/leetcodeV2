ReverseWordsinaString

Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".

----

public class Solution {
    public void reverse(char[] s, int l, int r)
    {
        while(l<r)
        {
            char c = s[l];
            s[l] = s[r];
            s[r] = c;
            l++; r--;
        }
    }
    public String reverseWords(String s) {
        char[] c = s.toCharArray();
        int n = c.length;
        
        int i=0, j=0;
        for(;j<n;j++)
        {
            while(j<n && c[j] == ' ') j++;
            if(j==n) break;
            
            if(i!=0)    c[i++]= ' ';
            while(j<n && c[j]!=' ') c[i++] = c[j++];
            if(j==n) break;
        }
        if(i==0) return "";
        n=i;
        reverse(c, 0, i-1);
        i=0; j=0;
        for(;j<n;j++)
        {
            i=j;
            for(;j<n && c[j]!=' ';j++);
            reverse(c,i,j-1);
        }
        char[] newC = new char[n];
        for(i=0; i<n;i++)
        	newC[i] = c[i];
        return new String(newC);
    }
}