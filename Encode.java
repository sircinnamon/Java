public class Encode
{
	//z = 2^x(2y+1)-1
	public static void main(String args[])
	{
		//2 arguments, encoded int and number of ints
		int length = Integer.parseInt(args[1]);
		int[] array = new int[length];
		array = decode(Integer.parseInt(args[0]), length, new int[length]);
		arrayPrint(array);
	}
	
	public static int[] decode(int encoding, int layer, int[] list)
	{
		int lhs = bigPow(encoding);
		int rhs = (((encoding + 1)/pow(2,lhs))-1)/2;
		list[layer-1] = rhs;
		System.out.println("list[" + (layer-1) + "]= " + rhs);
		System.out.println("r: " + rhs + " l: " + lhs);
		if(layer-1 == 0)
		{
			list[0] = encoding;
			return list;
		}
		else
		{
			return decode(lhs, layer-1, list);
		}
	}

	public static int bigPow(int z)
	{
		//return the largest power of 2 that divides z+1
		int i = 0;
		while(pow(2, i+1)<z)
		{
			i++;
		}
		while((z+1) % pow(2, i) != 0)
		{

			System.out.println(i + " : " + pow(2,i) + " : " + ((z+1) % pow(2, i)));
			i--;
			
		}
		System.out.println("return "+ i);
		return i;
	}

	public static int pow(int x, int y)
	{
		//return x^y
		int result = 1;
		if(y == 0)
		{
			return 1;
		}
		while(y>0)
		{
			result = result * x;
			y--;
		}
		return result;
	}

	public static void arrayPrint(int[] array)
	{
		System.out.print("[");
		for(int i = 0; i<array.length;i++)
		{
			System.out.print(array[i]);
			if(i!=array.length-1)
			{
				System.out.print(", ");
			}
		}
		System.out.print("]\n");
	}
}