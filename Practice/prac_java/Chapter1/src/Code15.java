import java.util.Scanner;

public class Code15 {

	public static void main(String[] args) {
		
		/*
		n개의 정수를 입력 받는다. 정수가 하나씩 입력 될 때 마다 현재까지
		입력된 정수들을 오름차순으로 정렬하여 출력하라
		*/
		
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int[] data = new int[n];
		
		for (int i = 0; i < n; i++)
		{
			int tmp = scan.nextInt();
			
			int j = i-1;
			while(j >= 0 && data[j] > tmp)
			{
				data[j+1] = data[j];
				j--;
			}
			data[j+1] = tmp;
		
		for(int k = 0; k <= i; k++)
		{
			System.out.print(data[k]+" ");
		}
		System.out.println();
		}
		scan.close();
	}
}
