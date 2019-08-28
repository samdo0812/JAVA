package Section2;

import java.util.Scanner;

public class Code16 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int a = scan.nextInt();
		int b = scan.nextInt();
		
		int result = power(a, b);
		
		System.out.println(result);
		scan.close();
	}
	
	static int power(int n, int m)
	{
		int prod = 1;
		for (int i = 0; i < n; i++)
		{
			prod = prod * n;
		}
		return prod;
	}

}
