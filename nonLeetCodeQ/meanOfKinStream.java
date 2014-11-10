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