package section2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Code09 {

	static MyRectangle2[] rects;
	static int n = 0;

	public static void main(String[] args) {
		rects = new MyRectangle2[100];
		try {
			Scanner in = new Scanner(new File("data.txt"));
			while(in.hasNext()) {
				
/*				int x = in.nextInt();
				int y = in.nextInt();
				int w = in.nextInt();
				int h = in.nextInt();
				rects[n] = new MyRectangle2(x,y,w,h);
				n++
*/		
				rects[n++] = new MyRectangle2(in.nextInt(),in.nextInt(),in.nextInt(),in.nextInt());			
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("No data file.");
			System.exit(1);
		}

		bubbleSort();

		for(int i=0; i<n; i++)
		{
			System.out.println(rects[i].toString());
		}
	}
	
	static void bubbleSort()
	{
		for(int i=n-1; i>0; i--) {
			for(int j=0; j<i; j++) {
				if( rects[j].calcArea() > rects[j+1].calcArea()) 
				{
					MyRectangle2 tmp = rects[j];
					rects[j] = rects[j+1];
					rects[j+1] = tmp;
				}
			}
		}
	}
	
	
}
