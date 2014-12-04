/*
Boxes
-----
有若干个盒子，每个盒子有length和width，不考虑高度。只要尺寸fit，大盒子就可以放小盒子，但是一层只能套一个，即便还有空余；但可以多层嵌套。  
求最小的面积放所有的盒子  
比如 ``7*7`` , ``5*5``, ``4*6``, ``3*3``, 答案是 ``7*7 + 4*6``.
*/

	static class box
	{
		int l,w;
		box(int _l, int _w)
		{
			l=_l; w=_w;
		}
		int getArea()
		{
			return l*w;
		}

		public String toString()
		{
			return (String.format("[%d,%d]", l, w));
		}
	}

	static class boxComp implements Comparator<box>
	{
		public int compare(box b1, box b2)
		{
			return b2.getArea()-b1.getArea();
		}
	}

	int minAreaBoxes(box[] b)
	{
		int n=b.length;
		if(n==0) return 0;
		if(n==1) return b[0].getArea();

		Arrays.sort(b,new boxComp());
		boolean[] onFloor = new boolean[n];
		for(int i=0;i<n;i++)
			System.out.print(b[i]+" ");
		System.out.println();
		int max = 0;
		for(int i=0;i<n;i++)
		{
			if(!onFloor[i])
			{
				max+=b[i].getArea();
				for(int j=i+1;j<n;j++)
				{
					if(!onFloor[j] && (b[j].l<=b[i].l && b[j].w<=b[i].w))
					{
						onFloor[j]  =true; break;
					}
				}
			}
		}
		for(int i=0;i<n;i++)
			if(!onFloor[i]) System.out.print(b[i]+" ");
		System.out.println();
		return max;
	}