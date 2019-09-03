package Section1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Code03 {
	
	static Person1[] members = new Person1[100];
	static int n = 0;
	
	public static void main(String[] args) {
		try {
			Scanner in = new Scanner(new File("input.txt"));
			while(in.hasNext())
			{
				String name = in.next();
				String number = in.next();
				members[n] = new Person1();
				
				members[n].name = name;
				members[n].number = number;
				n++;
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("No data");
		}
		
		bubbleSort();
		
		for (int i = 0; i < n; i++) {
			System.out.println(members[i].name +" " + members[i].number);
		}
	}
	static void bubbleSort()
	{
		for (int i = n-1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (members[j].name .compareTo(members[j+1].name) > 0){		//compare name of j-th and (j+1)-th person
					//swap two person
					Person1 tmp = members[j];
					members[j] = members[j+1];
					members[j+1] = tmp;
				}
			}
		}
	}
}
