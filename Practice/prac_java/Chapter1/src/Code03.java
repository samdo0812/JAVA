import java.util.Scanner;

public class Code03 {

	public static void main(String[] args) {
		
		String str = "Hello";
		String input = null;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Please type a string");
		input = scan.next();
		
		if(str.equals(input))
		{
			System.out.println("Strings match!");
		}
		else
		{
			System.out.println("Strings do not match");
		}
		scan.close();
	}
}
