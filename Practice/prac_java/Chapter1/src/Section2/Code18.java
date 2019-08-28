package Section2;

import java.util.Scanner;

public class Code18 {

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
					int tmp = data[j];
					data[j] = data[j+1];
					data[j+1] = tmp;
				}
			}
		}
		return; //리턴 타입이 void니까 있어도 되고 없어도 되고, 함수의 실행을 중단한다는 의미
	}

}
