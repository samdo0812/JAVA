import java.util.Scanner;

public class Code09 {

	public static void main(String[] args) {
		
		/*
		n���� ������ �Է¹޾� ������� �迭�� �����Ѵ�. 
		�׷����� ��� �������� �� ĭ�� ���������� shift�϶�
		������ ������ �迭�� ùĭ���� �̵��϶�
		*/
		
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int [] data = new int[n];
		
		for (int i = 0; i < n; i++)
		{
			data[i] = scan.nextInt();
		}
		scan.close();
		
		int tmp = data[n-1];
		for (int i = n-2; i >= 0; i--)
		{
			data[i+1] = data[i];
			data[0] = tmp;
		}
		
		for (int i = 0; i < n; i++)
		{
			System.out.println(data[i]);
		}
	}

}
