/*
Google:

2. an array with 0's and 1's, find in O(n) time and O(1) space the longest 
sequence with equal number of 1's and 0's.

can do it with O(n) time and O(n) space. How to do O(1) space.
*/

import java.util.*;
import java.lang.*;

class Solution
{
	int longestEqualSequence(int[] num)
	{
        int n = num.length;
        if(n<=1) return 0;
        int max = 0;
        int sum = 0;
        for(int i:num)
            sum+=i;
        int diff = (sum*2-n);
        int i=n-1, j = n-1;

        int one = 0, zero = 0;
        for(;i>=0;i--)
        {
            if(num[i]==1) one++;
            else zero++;
            if(one-zero==diff)
                break;
        }
        max = i;
        int start = 0, end = i-1;


        j = 0;

        for(;j<i&&i<=n-1;j++)
        {
            if(num[j]==0) continue;
            while(i<n)
            {
                if(num[i]==1) break;
                i++;
            }

            if(i==n) break;
            else
            {
                if(i-j+1>max)
                {
                    start = j+1;
                    end = i;
                    max = i-j;
                }
                i++;
            }
        }
        return max;
    }
}