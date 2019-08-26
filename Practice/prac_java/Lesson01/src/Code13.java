import java.util.Scanner;

public class Code13 {

	public static void main(String[] args) {

		/*
		n개의 음이 아닌 한 자리 정수를 입력받아 배열에 저장한다.
		이들중에서 1개 이상의 연속된 정수들을 합하여(?) ex) 7, 1, 3 -> 713
		 얻을 수 있는 소수들 중에서
		최대값을 구하여 출력하여라
		 */

		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int[] data = new int[n];

		for (int i = 0; i < n; i++) 
		{
			data[i] = scan.nextInt();
		}
		scan.close();


		int max = 0;
		for (int i = 0; i < n; i++)
		{
			for (int j = i; j < n; j++)
			{
				//convert data[i]... data[j] into an integer
				int val = 0;
				for (int k = i; k <= j; k++)
				{
					val = val * 10 + data[k];
					/*
					ex) 1, 9, 4
					0 * 10 + 1 = 1 
					1 * 10 + 9 = 19
					19 * 10 + 4 = 194
					 */

				}
				//test if it is a prime
				boolean isPrime = true;
				for (int k = 2; k*k <= val && isPrime; k++)
				{
					if(val % k == 0)
					{
						isPrime = false;
					}
				}
				//if yes, compare to the max
				if(isPrime && val > 1 && val > max)
				{
					max = val;
				}
			}
		}
		if(max > 0)
		{
			System.out.println(max);
		}
		else
		{
			System.out.println("No prime Number");
		}

	}
}
