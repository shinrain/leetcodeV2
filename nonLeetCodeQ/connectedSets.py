
def connectedSets(graph):
    m=len(graph)
    if m==0:
        return 0
    n=len(graph[0])
    if n==0:
        return 0
    visit=[[False for i in range(n)] for i in range(m)]
    count=0
    for i in range(m):
        for j in range(n):
            if visit[i][j] or graph[i][j]==0:
                continue
            visit[i][j]=True
            count+=1
            dfs(graph, i, j, visit)
    return count

def dfs(graph, i, j, visit):
    st=[(i,j)]
    while st:
        i,j=st.pop()
        if check(graph,i+1,j,visit):
            st.append((i+1,j))
        if check(graph,i-1,j,visit):
            st.append((i-1,j))
        if check(graph,i,j+1,visit):
            st.append((i,j+1))
        if check(graph,i,j-1,visit):
            st.append((i,j-1))
        if check(graph,i-1,j-1,visit):
            st.append((i-1,j-1))
        if check(graph,i-1,j+1,visit):
            st.append((i-1,j+1))
        if check(graph,i+1,j+1,visit):
            st.append((i+1,j+1))
        if check(graph,i+1,j-1,visit):
            st.append((i+1,j-1))
def check(graph, i, j, visit):
    if i not in range(len(graph)) or j not in range(len(graph[0])) or graph[i][j]==0 or  visit[i][j]:
        return False
    visit[i][j]=True
    return True



if __name__=="__main__":

    G=[[0,0,0,0,0,1],
       [0,0,0,0,0,1],
       [1,0,1,1,0,1],
       [0,0,1,1,0,1],
       [0,0,0,0,0,1]]
    print connectedSets(G)
