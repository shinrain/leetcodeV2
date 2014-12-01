Design a class where you can add elements, and return the mean of the latest N elements.



class Kmean
{
	int cap;
	int siz;
	int sum;
	int cur;
	int[] arr;
	Kmean(int _cap)
	{
		cap = _cap;
		cur = siz = sum = 0;
		arr = new int[cap];
	}

	void put(int val)
	{
		if(siz<cap)
		{
			arr[cur] = val;
			sum+=val;
			size++;
			cur = (cur+1)%cap;
		}
		else
		{
			sum -=arr[cur];
			arr[cur] = val;
			sum+=arr[cur];
			cur = (cur+1)%cap;
		}
	}

	int getMean()
	{
		return sum/siz;
	}
}

====

This is cleaner

class Kmean
{

	int[] a;
	int cap = 10;
	int ind;
	int siz;
	int sum;

	void put(int val)
	{
		sum-=a[ind%cap];
		sum+=val;
		a[ind%cap] = val;
		ind++;
		if(siz<cap) siz++;
	}

	int getMean()
	{
		if(siz==0) System.exit(0);
		return sum/siz;
	}

}
