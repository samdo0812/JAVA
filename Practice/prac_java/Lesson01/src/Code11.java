import java.util.Scanner;

public class Code11 {

	public static void main(String[] args) {
		
		/*
		 ����ڷκ��� ���� ������ ���� n�� �Է� �޴´�.
		 �̾ n���� ������ �Է� �޾� ������� �迭�� �����Ѵ�.�׷� ���� �ߺ��� �����ǽ��� ������ ī��Ʈ�Ͽ� ����϶�.
		 ���� ��� n=6�̰� �Էµ� �������� 2, 4, 2, 4, 5, 2�̸� �ߺ��� ��������
		 (2,2), (4, 4)�� ��� 4���̴�.
		*/
		
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int[] data = new int[n];
		for (int i = 0; i < n; i++)
		{
			data[i] = scan.nextInt();
		}
		scan.close();
		int count = 0;
		
		for (int i = 0; i < n-1; i++)
		{
			for (int j = i+1; j < n; j++)
			{
				if (data[i] == data[j])
				{
					count++;
				}
			}
		}
		System.out.println(count);
	}
}
