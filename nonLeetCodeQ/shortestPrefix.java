/*
Use the shorest unique prefix to represent each word in the array 
input: ["zebra", "dog", "duck",”dot”] 
output: {zebra: z, dog: do, duck: du} 

[zebra, dog, duck, dove] 
{zebra:z, dog: dog, duck: du, dove: dov} 

[bearcat, bear] 
{bearcat: bearc, bear: ""}

*/


  boolean compare(String s, String p, HashMap<String, String> map)
  {
    int m = s.length(), n=p.length();
    if(s.equals(p)) return true;
    System.out.println("Compare "+s+", "+p);

    int i=0;
    for(;i<m && i<n;i++)
    {
        if(s.charAt(i) < p.charAt(i))
        {
            String ss = map.get(s);
            String pp = map.get(p);
            if(ss.length()-1<i)
            {
                map.put(s,s.substring(0,i+1));
            }
            if(pp.length()-1<i)
            {
                map.put(p,p.substring(0,i+1));
            }
            return true;
        }
        else if(s.charAt(i) > p.charAt(i))
        {
            String ss = map.get(s);
            String pp = map.get(p);
            if(ss.length()-1<i)
            {
                map.put(s,s.substring(0,i+1));
            }
            if(pp.length()-1<i)
            {
                map.put(p,p.substring(0,i+1));
            }
            return false;
        }
    }

    if(s.length()-1<i)
    {
        map.put(s,s);
    }
    if(p.length()-1<i)
    {
        map.put(p,p);
    }
    return m<n;
  }

  int partition(String[] s, int l, int r, HashMap<String, String> map)
  {
    if(l>r) return -1;
    String val = s[l];
    int i=l-1, j=r+1;
    while(i<j)
    {
        while((++i)<j && compare(s[i], val, map));
        while((--j)>=i && !compare(s[j], val, map));
        if(i>j) break;
        String t = s[i];
        s[i] = s[j];
        s[j] = t;
    }
    String t = s[l];
    s[l] = s[j];
    s[j] = t;
    return j;
  }

  void sort(String[] s, int l, int r,HashMap<String, String> map)
  {
    if(l>r) return;
    
    int mid = partition(s,l,r, map);
    System.out.println("sort "+l+" "+r+", mid="+mid);
    sort(s,l,mid-1, map);
    sort(s,mid+1, r, map);
  }

  HashMap<String, String> func(String[] s)
  {
    HashMap<String, String> map = new HashMap<String, String>();
    int n = s.length;
    if(n==0) return map;

    for(String i:s)
    {
        map.put(i, "");
    }
    sort(s, 0, n-1, map);
    return map;
  }
  public static void main(String[] args) {
      String[] s ={"zero","dog","duck","dove"};
      System.out.println(new Solution().func(s));
  }
}