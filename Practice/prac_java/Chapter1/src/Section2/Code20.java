package Section2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Code20 {
	
	static String[] name = new String[1000];
	static String[] number = new String[1000];
	static int n = 0;	//사람 수

	public static void main(String[] args) {
		try {
			Scanner inFile = new Scanner( new File("input.txt") );

			while(inFile.hasNext()) {	// detect End of file
				name[n] = inFile.next();
				number[n] = inFile.next();
				n++;
			}

			inFile.close();
		} catch (FileNotFoundException e) {
			System.out.println("No data File");
			return;
		}

		bubbleSort();
		
		for (int i = 0; i < n; i++) {
			System.out.println("Name : " + name[i] + ",  Phone : " + number[i]);
		}
			
	}
	static void bubbleSort()
	{
		for (int i = n-1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if(name[j].compareTo(name[j+1]) > 0) {	//compareTo의 리턴값은 1, 0, -1 A가 클때  양수 반환
					String tmp = name[j];
					name[j] = name[j+1];
					name[j+1] = tmp;
					
					tmp = number[j];
					number[j] = number[j+1];
					number[j+1] = tmp;
				}
			}
		}
	}
}
