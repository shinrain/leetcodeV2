/* 1. printing a tree structure with giving collection of pairs of <parent, 
child> relation. Need to first find the root, and validate wether the given 
relations is a valid tree, and then printing. 
*/

===
    static class pair
    {
        int left, right;
        pair(int _left, int _right)
        {
            left = _left; right = _right;
        }
    }

    static class node
    {
        int val;
        int indegree;
        HashSet<node> outgoing;
        node(int _val)
        {
            val = _val; indegree = 0; outgoing = new HashSet<node>();
        }
    }

    node buildUp(pair[] a)
    {
        int n = a.length; 
        if(n==0) return null;

        HashMap<Integer, node> map = new HashMap<Integer, node>();
        for(pair p:a)
        {
            int par = p.left, kid = p.right;
            if(!map.containsKey(par)) map.put(par, new node(par));
            if(!map.containsKey(kid)) map.put(kid, new node(kid));
            node parNode = map.get(par);
            node kidNode = map.get(kid);

            if(!parNode.outgoing.contains(kidNode)) 
            {
                parNode.outgoing.add(kidNode);
                kidNode.indegree++;
            }
        }

        node rootNode = null;
        for(int i:map.keySet())
        {
            if(map.containsKey(i) && map.get(i).indegree==0)
            {
                if(rootNode!=null)
                {
                    rootNode=null; break;
                }
                rootNode = map.get(i);
            }
        }
        if(rootNode==null) return null;

        HashSet<node> set = new HashSet<node>();
        LinkedList<node> q = new LinkedList<node>();

        q.addLast(rootNode);
        set.add(rootNode);
        while(!q.isEmpty())
        {
            node cur = q.removeFirst();
            for(node k : cur.outgoing)
            {
                if(set.contains(k))
                {
                    rootNode = null; break;
                }
                set.add(k); q.addLast(k);
            }
        }

        if(rootNode==null) return null;
        return (set.size()==map.size())?rootNode:null;
    }