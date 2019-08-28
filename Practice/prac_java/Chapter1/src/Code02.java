import java.util.Scanner;

public class Code02 {

	public static void main(String[] args) {
		int number = 123;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Please enter an integer: ");
		int input = scan.nextInt();
		
		if(input == number)
		{
			System.out.println("Numbers match! ");
		}
		else
		{
			System.out.println("Numbers do not match");
		}
		
		scan.close();
	}

}
