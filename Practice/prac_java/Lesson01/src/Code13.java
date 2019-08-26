import java.util.Scanner;

public class Code13 {

	public static void main(String[] args) {

		/*
		n���� ���� �ƴ� �� �ڸ� ������ �Է¹޾� �迭�� �����Ѵ�.
		�̵��߿��� 1�� �̻��� ���ӵ� �������� ���Ͽ�(?) ex) 7, 1, 3 -> 713
		 ���� �� �ִ� �Ҽ��� �߿���
		�ִ밪�� ���Ͽ� ����Ͽ���
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
