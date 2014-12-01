/*
Designed a method to verify utf-8 characters
*/

class Solution
{
	static boolean validate(byte[] a)
	{
		int n = a.length;
		if(n==0) return false;

		byte valid = (byte)0b11000000;

		int i=0;
		for(;i<n;i++)
		{
			int bit = -1;
			if((a[i]&(byte)0b10000000)==(byte)0b00000000) bit = 0;
			else if((a[i]&((byte)0b11100000))==(byte)0b11000000) bit = 1;
			else if((a[i]&((byte)0b11110000))==(byte)0b11100000) bit = 2;
			else if((a[i]&((byte)0b11111000))==(byte)0b11110000) bit = 3;
			else return false;

			for(int j=i+1;j<=i+bit;j++)
			{
				if((a[j]&valid )!= (byte)0b10000000) return false;
			}
			if(bit==-1) return false;
			i+=bit;
		}
		return true;
	}

     public static void main(String[] args) {
     	byte[] bytes1 = {(byte) 0b11001111, (byte) 0b10111111};
		System.out.println(validate(bytes1)); // true

		byte[] bytes2 = {(byte) 0b11101111, (byte) 0b10101010, (byte) 0b10111111};
		System.out.println(validate(bytes2)); // true

		byte[] bytes3 = {(byte) 0b10001111, (byte) 0b10111111};
		System.out.println(validate(bytes3)); // false

		byte[] bytes4 = {(byte) 0b11101111, (byte) 0b10101010, (byte) 0b00111111};
		System.out.println(validate(bytes4)); // false
     }
 
}