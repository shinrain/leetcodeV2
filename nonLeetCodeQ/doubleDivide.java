/*Given integer a and b, find out the string representation of a/b. For 
example, if a = 1, b = 3, the output should be 0.(3).
*/


    String divide(int a, int b)
    {
        if(a<b) return "0."+helper(a,b);
        if(a==b) return "1";
        if(a==0) return "0";
        if(b==0) return "NaN";
        if(b==1) return Integer.toString(a);

        return Integer.toString(a/b)+"."+helper(a%b,b);
    }

    String helper(int a, int b)
    {
        if(a>=b) return Integer.toString(a%b);

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        LinkedList<Integer> re = new LinkedList<Integer>();


        while(a!=0)
        {
            a*=10;
            while(a<b)
            {
                a*=10;
                re.add(0);
            }
            int val = a/b;
            map.put(a/10,val);
            re.add(val);
            a = a%b;
            if(map.containsKey(a)) break;
        }
        String result = "";
        if(a==0)
            for(int i:re) result+=Integer.toString(i);
        else
        {
            int bug = map.get(a);
            for(int i:re)
            {
                if(i==bug) result+=("(");
                result+=Integer.toString(i);
            }
            result+=")";            
        }
        return result;
    }