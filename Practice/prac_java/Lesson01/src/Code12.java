import java.util.Scanner;

public class Code12 {

	public static void main(String[] args) {
		
		/*
			n���� ������ �Է¹޾� �迭�� �����Ѵ�.
			�̵��߿��� 0�� �̻��� ���ӵ� �������� ���Ͽ� ���� �� �ִ� �ִ밪�� ���Ͽ���
		*/
		
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int []data = new int[n];
		
		for (int i = 0; i < n; i++)
		{
			data[i] = scan.nextInt();
		}
		scan.close();
		
		int maxSum = 0;
		for (int i = 0; i < n; i++)
		{
			int sum = 0;
			for (int j = 0; j < n; j++)
			{		
				sum += data[j];
					if(sum > maxSum)
					{
						maxSum = sum;
					}
				}
			}
		System.out.println("The max sum is " + maxSum);
	}
}
