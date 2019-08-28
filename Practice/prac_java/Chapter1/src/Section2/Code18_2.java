package Section2;

import java.util.Scanner;

public class Code18_2 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int[] data = new int[n];
		for (int i = 0; i < n; i++) 
		{
			data[i] = scan.nextInt();
		}
		scan.close();

		bubbleSort( n, data );
		
		System.out.println("Sorted Data : ");

		for (int i = 0; i < n; i++)
		{
			System.out.println(data[i]);
		}
	}

	static void bubbleSort( int n, int[] data )
	{
		for (int i = n -1 ; i > 0; i--)
		{
			for (int j = 0; j < i; j++)
			{
				if (data[j] > data[j+1])
				{
					swap(data[j] , data[j+1]);
				}
			}
		}
	}
	
	/*
	swap 안됨
	Call by value
	Array는 프리미티브 타입이 아니라 됨
	*/
	static void swap(int a, int b)
	{
		int tmp = a;
		a = b;
		b = tmp;
	}
}
