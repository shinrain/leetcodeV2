// Check if a number power of 3.



	static boolean threePow(int x)
	{
	    if(x<=0) return false;
	    if(x==1) return true;
	    int div = 3;
	    while(x!=0)
	    {
	        if(x==1) return true;
	        if(x%div==0)
	        {
	            if(x/div<div*3)
	            {
	            	x/=3;
	            	div=3;
	            	continue;
	            }
	            x/=div;
	            div*=3;
	        }
	        else return false;
	    }
	    return false;
	}
