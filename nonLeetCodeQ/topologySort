/*
fft, fcp, aac, act, acd, atp, tbk, tdf, …
这些都是按照字母排序好的，但是字母顺序改了，比如 f 在 a之前，t在d之前等等，
给定一些这样的rule，问怎么rebuild the alphabet?
*/



class node
{
    char val;
    int indegree;
    HashSet<node> outNode;

    node(char _val)
    {
        val = _val;
        indegree = 0;
        outNode = new HashSet<node>();
    }
}

class Solution{


    String alphabetRebuild(String[] a)
    {
        String re = "";
        int n = a.length;
        if(n==0) return re;

        HashMap<Character, node> map = new HashMap<Character, node>();

        for(String s:a)
        {
            for(int i=0;i<s.length();i++)
            {
                char c = s.charAt(i);
                if(!map.containsKey(c))
                {
                    map.put(c, new node(c));
                }

                if(i+1<s.length())
                {
                    char next = s.charAt(i+1);

                    if(next==c) continue;

                    node nd = map.get(c);

                    if(!map.containsKey(next))
                        map.put(next,new node(next));

                    if(!nd.outNode.contains(map.get(next)))
                    {
                        map.get(next).indegree++;
                        nd.outNode.add(map.get(next));
                    }
                }
            }
        }

        while(map.size()!=0)
        {
            node nd = null;
            for(char c:map.keySet())
            {
                nd = map.get(c);
                if(map.get(c).indegree==0) break;
            }

            if(nd==null) return re;
            re = re+String.valueOf(nd.val);
            for(node N : nd.outNode)
            {
                N.indegree--;
            }
            map.remove(nd.val);
        }
        return re;
    }

    public static void main(String[] args) {
        
        String[] a = {"fft", "fcp", "aac", "act", "acd", "atp", "tbk", "tdf"};

        System.out.println(new Solution().alphabetRebuild(a));
    }

}