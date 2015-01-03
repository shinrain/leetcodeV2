class node:

    def __init__(self,val):
        self.val=val
        self.adj=set()
    def __str__(self):
        re="[%d, ["%self.val
        for i in self.adj:
            re+=str(i)+" "
        re+='] ]'
        return re


def checkBipartitie(G):
    n=len(G)
    if n<=1:
        return True
    visit=[False]*n
    color=[0]*n
    for i in range(n):
        if visit[i]:
            continue
        color[i]=1
        visit[i]=True
        if not dfs(G,i,color,visit):
            return False
    for i in range(n):
        print str(color[i])+":"
        for j in G[i].adj:
            print "==>"+str(color[j])
    return True

def dfs(G, i, color, visit):
    cur=G[i]

    for nb in G[i].adj:

        if visit[nb]:
            if color[nb]==color[i]:
                return False
        else:
            color[nb]=-color[i]
            visit[nb]=True
            if not dfs(G,nb,color,visit):
                return False
    return True

if __name__=="__main__":
    n=20
    a=[i for i in range(20)]
    G=[node(i) for i in a]
    for i in range(n):
        G[i].adj.add((i+1+n)%n)
        G[i].adj.add((i-1+n)%n)
    G[0].adj.add(2)
    print checkBipartitie(G)
