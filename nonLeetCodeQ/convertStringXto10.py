# give an input stream like 1010x0x1, give all the result that transfer x into 0 or 1

def convertStringXto10(s):
    if not s:
        return ""
    re=[]

    helper(s,0,['.']*len(s),re)

    return re

def helper(s,k,r,re):
    if k>=len(s):
        re.append("".join(r))
        return

    if s[k]!='x':
        r[k]=s[k]
        helper(s,k+1,r,re)
    else:
        r[k]='0'
        helper(s,k+1,r,re)
        r[k]='1'
        helper(s,k+1,r,re)

if __name__=='__main__':
    print convertStringXto10("1010x0x1")
