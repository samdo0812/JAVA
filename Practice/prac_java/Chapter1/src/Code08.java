import java.util.Scanner;

public class Code08 {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int [] data = new int[n];
		
		for(int i = 0; i < n; i++)
		{
			data[i] = scan.nextInt();
		}
		scan.close();
		
		int sum = 0;
		int max = data[0];
		
		for(int i = 0; i<n; i++)
		{
			sum += data[i];
			if(data[i] > max) { max = data[i]; }
		}
		
		System.out.println("The sum is " + sum);
		System.out.println("The max is " + max);
	}

}
