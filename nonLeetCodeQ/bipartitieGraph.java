/*
Given a graph as input, write a java method returning boolean true if the
 graph is bipartitie, else false

 */


Note: this is undirected graph. So define:

graphNode
{
	int val;
	HashSet<Integer> adj;
}


    boolean checkBipartitie(node[] G)
    {
        int n = G.length;
        if(n<=1) return false;
        if(n==2) return true;

		int[] color = new int[n];
		boolean[] visit = new boolean[n];

        LinkedList<node> q = new LinkedList<node>();

        for(int k=0;k<n;k++)
        {
        	if(visit[k]) continue;

        	color[k] = -1;
        	visit[k] = true;
        	if(!dfs(k, G[k], visit, color)) return false;
        }
        return true;
    }

    boolean dfs(int k, node[] G, boolean[] visit, int[] color)
    {
    	for(int i:G[k].adj)
    	{
    		if(visit[i])
    			if(color[i]+color[k]!=0) return false;
    		else
    		{
    			visit[i]=true;
    			color[i] = (color[k]==-1)?1:-1;
    			if(!dfs(i,G,visit,color)) return false;
    		}
    	}
    	return true;
    }
}