import java.math.BigInteger;
public class Pairing
{
	//z = 2^x(2y+1)-1
	public static void main(String args[])
	{
		//2 arguments, encoded int and number of ints
		//Arguments: Java Pairing e [number of ints to encode] [int1] [int2] ...
		//Or to decode: Java Pairing d [Num of Ints] [encoded int]
		if(args.length < 2)
		{
			System.out.println("Not a valid run mode. Try: \nJava Pairing e [number of ints to encode] [int1] [int2] ...\nJava Pairing d [Num of Ints] [encoded int]");
			System.exit(0);
		}
		if(args[0].equals("d"))
		{
			int length = Integer.parseInt(args[1]);
			int[] array = new int[length];
			array = decode(new BigInteger(args[2]), length, new int[length]);
			arrayPrint(array);
		}
		else if(args[0].equals("e"))
		{
			int length = Integer.parseInt(args[1]);
			if(args.length != (length + 2))
			{
				System.out.println("Not enough ints to encode.");
				System.exit(0);
			}
			BigInteger encoding;
			int[] ints = getInts(args);
			encoding = encode(length, ints);
			System.out.println("");
		}
		else
		{
			System.out.println("Not a valid run mode. Try: \nJava Pairing e [number of ints to encode] [int1] [int2] ...\nJava Pairing d [Num of Ints] [encoded int]");
			System.exit(0);
		}
	}
	
	public static int[] decode(BigInteger encoding, int layer, int[] list)
	{
		BigInteger two = new BigInteger("2");
		int lhs = bigPow(encoding);
		BigInteger rhs = (((encoding.add(BigInteger.ONE)).divide(two.pow(lhs))).subtract(BigInteger.ONE)).divide(two);
		list[layer-1] = lhs;
		System.out.println("list[" + (layer-1) + "]= " + rhs);
		System.out.println("r: " + rhs + " l: " + lhs);
		if(layer-1 == 0)
		{
			list[0] = Integer.parseInt(encoding.toString());
			return list;
		}
		else
		{
			return decode(rhs, layer-1, list);
		}
		/*int lhs = bigPow(encoding);
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
		}*/
	}

	public static BigInteger encode(int length, int[] ints)
	{
		//z = 2^x(2y+1)-1
		BigInteger two = new BigInteger("2");
		BigInteger encoding = new BigInteger(Integer.toString(ints[ints.length-1]));
		for(int i = ints.length-1; i > 0; i--)
		{
			encoding = (encoding.multiply(two)).add(BigInteger.ONE);
			encoding = encoding.multiply(two.pow(i-1));
		}
		return encoding;
	}

	public static int bigPow(BigInteger z)
	{
		//return the largest power of 2 that divides z+1
		int i = 1;
		BigInteger zplus = z.add(BigInteger.ONE);
		BigInteger two = new BigInteger("2");
		while(two.pow(i).compareTo(zplus) == -1)
		{
			i++;
		}
		while((zplus.mod(two.pow(i))).compareTo(BigInteger.ZERO) != 0)
		{
			//System.out.println(i + " : " + pow(2,i) + " : " + ((z+1) % pow(2, i)));
			i--;
			
		}
		System.out.println("return "+ i);
		return i;
	}

	/*public static int pow(int x, int y)
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
	}*/

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

	public static int[] getInts(String[] str)
	{
		//arg 1 = e
		//arg 2 = # of ints
		//args 3+ = ints
		int[] array = new int[Integer.parseInt(str[1])];
		for(int i = 0; i<array.length-1; i++)
		{
			array[i] = Integer.parseInt(str[i+2]);
		}
		return array;
	}
}