/*
2. we have a list of schedules, defined by start time and end time. e.g. (9:00 - 10:00) (10:00 - 11:00). Find all the conflicts and print out the pairs.

*/
import java.util.*;
import java.lang.*;
class event
{
	int id;
	int start, end;
	event(int _id, int _start, int _end)
	{
		id=_id;
		start = _start;
		end=_end;
	}
}

class time
{
	int id;
	int val;
	char prop;
	time(int _id, int _val, char _p)
	{
		id = _id;
		val = _val;
		prop = _p;
	}
	public String toString()
	{
		String re = String.format("{id:%d, val:%d, prop:%c}", id, val, prop);
		return re;
	}
}


class Solution
{
	static class eventComp implements Comparator<time>
	{
		public int compare(time a, time b)
		{
			if(a==null) return -1;
			if(b==null) return 1;
			int diff = a.val-b.val;
			if(diff!=0) return diff;
			if(a.prop=='e') return -1;
			if(b.prop=='e') return 1;
			return 0;
		}
	}

	void conflictSchedule(event[] e)
	{
		int n=e.length;
		if(n<=1) return;

		time[] T = new time[2*n];
		int ind = 0;
		for(event ee:e)
		{
			T[ind++] = new time(ee.id, ee.start, 's');
			T[ind++] = new time(ee.id, ee.end, 'e');
		}

		Arrays.sort(T, new eventComp());

		HashSet<Integer> ids = new HashSet<>();

		for(time t:T)
		{
			if(t.prop=='e') ids.remove(t.id);
			if(t.prop=='s')
			{
				for(int tt:ids)
				{
					System.out.println("comflict: "+t.id+" -- "+tt);
				}
				ids.add(t.id);
			}
		}
	}

	public static void main(String[] args) {
		event[] a = {
			new event(1,0,1), 
			new event(2,0,2), 
			new event(3,2,3), 
			new event(4,2,4),
			new event(5,1,3)
		};
		new Solution().conflictSchedule(a);
	}

}