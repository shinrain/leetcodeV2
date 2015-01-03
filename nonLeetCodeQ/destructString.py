
def destructString(s):
    if not s:
        return ()
    re=()
    for i in range(1,len(s)):
        if s[i]==s[0] and len(s)%i==0:
            if helper(s,i):
                re=(len(s)/i,s[0:i])
    return re


def helper(s,i):
    cur=s[0:i]
    j=i
    while j<len(s) and j+i<=len(s) and s[0:i]==s[j:j+i]:
        j+=i
    return j==len(s)


if __name__=='__main__':
    print destructString("abcdabcd")
