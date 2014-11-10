
/*就是如何实现find, insert, delete, getRandom 都是O(1)
*/



class Solution
{
    int cap;
    int size;
    int[] arr;
    HashMap<Integer, Integer> map;

    Solution(int _cap)
    {
        cap = _cap;
        size = 0;
        arr = new int[cap];
        map = new HashMap<Integer, Integer>();
    }

    boolean find(int elem)
    {
        return map.containsKey(elem);
    }

    void insert(int elem)
    {
        if(map.containsKey(elem)) return;
        if(size<cap)
        {
            arr[size] = elem;
            map.put(elem, size);
            size++;
        }
        else
        {
            int[] newArr = new int[2*cap];
            for(int i=0;i<cap;i++)
            {
                newArr[i] = arr[i];
            }
            cap = 2*cap;
            insert(elem);
        }
    }
    
    void delete(int elem)
    {
        if(!map.containsKey(elem)) return;

        int ind = map.get(elem);
        int last = arr[size-1];
        map.remove(elem);
        map.remove(last);
        arr[ind] = last;
        map.put(last,ind);
        size--;
    }

    int getRandom()
    {
        if(size!=0)
        {
            int k = random(0,size);
            return arr[k];
        }
        System.exit(-11);
    }
}
