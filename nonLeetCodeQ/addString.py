import sys, copy, Queue, math, time
from ojClass import *

class Solution:

    def addString(self, str1, str2):
        print str1,str2,
        m,n=len(str1.strip()),len(str2.strip())
        if m==0:
            return str2
        if n==0:
            return str1
        f1,f2=False, False
        if str1[0] in ['+','-']:
            f1=str1[0]=='-'
            str1=str1[1::]
        if str2[0] in ['+','-']:
            f2=str2[0]=='-'
            str2=str2[1::]
        re=""
        m,n=len(str1.strip()),len(str2.strip())
        if (f1 and f2) or (not f1 and not f2):
            re=helper(str1,str2,True)
            if f1 and re!='0':
                re='-'+re
        else:
            if check(str1,str2):
                re=helper(str1,str2, False)
                if f1 and re!='0':
                    re='-'+re
            else:
                re=helper(str2,str1,False)
                if f2 and re!='0':
                    re='-'+re

        return re
def check(str1, str2):
    m,n=len(str1),len(str2)
    if m>n:
        return True
    if m<n:
        return False
    i,j=0,0
    while i<m:
        if int(str1[i])>int(str2[j]):
            return True
        if int(str1[i])<int(str2[j]):
            return False
        i+=1
        j+=1
    return True

def helper(str1, str2, add):
    re=""
    carry=0

    i,j=len(str1)-1,len(str2)-1
    while i>=0 or j>=0:
        val=carry
        if i>=0:
            val+=int(str1[i])
            i-=1
        if j>=0:
            if not add:
                val-=int(str2[j])
                j-=1
            else:
                val+=int(str2[j])
                j-=1
        if val>=0:
            re=str(val%10)+re
            carry=val/10
        else:
            carry=0
            while val<0:
                carry-=1
                val+=10
            re=str(val%10)+re
    i=0
    while i<len(re) and re[i]=='0':
        i+=1
    if i==len(re):
        re="0"
    else:
        re=re[i::]

    if carry>0:
        re=str(carry)+re
    if carry<0:
        re='-'+str(-carry)+re
    return re


if __name__=="__main__":

    print Solution().addString("100","-1000")
    print Solution().addString("","-1000")
    print Solution().addString("","1000")
    print Solution().addString("100","1000")
    print Solution().addString("-100","-1000")
    print Solution().addString("-1000","100")
    print Solution().addString("-1000","-100")
    print Solution().addString("-1000","1000")
