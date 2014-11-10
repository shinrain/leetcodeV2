/*
Question 1: The interviewer was really kind. The asked me to write a function to find all the Ramanujan numbers. He helped
 me when I got stuck at any point and was giving me polite hints.

 1729 = 1^3 + 12^3 = 9^3 + 10^3
*/
    List<List<Integer>> Ramanujan(int N)
    {
        List<List<Integer>> re = new ArrayList<List<Integer>>();
        if(N<9) return re;
        long l = 1, r = N;
        while(l<r)
        {
            long sum = l*l*l+r*r*r;
            if(sum == N)
            {
                List<Integer> rr = new ArrayList<Integer>();
                rr.add((int)l); rr.add((int)r);
                re.add(rr);
                if(re.size()==2) return re;
                else { l++;r--;}
            }
            else if(sum<N) l++;
            else r--;
        }
        re.clear();
        return re;
    }