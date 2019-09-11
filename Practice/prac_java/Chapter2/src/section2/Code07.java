package section2;

import java.util.Scanner;

public class Code07 {
	
	static Polynomial2[] polys = new Polynomial2[100];	//다항식들을 저장할 배열
	static int n = 0;	//전체 다항식들의 개수
	
	public static void main(String[] args) {
	
		Scanner kb = new Scanner(System.in);
		
		while(true)
		{
			System.out.println("$ ");
			String command = kb.next();
			if(command.equals("create"))
			{
				char name = kb.next().charAt(0);
				polys[n] = new Polynomial2();
				polys[n].name = name;
				polys[n].terms = new Term2[100];
				polys[n].nTerms = 0;
				n++;
			}
			else if(command.equals("add"))
			{
				char name = kb.next().charAt(0);
				int index = find(name);
				if(index == -1)
				{
					System.out.println("No such polynomial exists.");
				}
				else
				{
					int c = kb.nextInt();
					int e = kb.nextInt();
					polys[n].addTerm(c, e);
				}
			}
			else if(command.equals("calc"))
			{

				char name = kb.next().charAt(0);
				int index = find(name);
				if(index == -1)
				{
					System.out.println("No such polynomial exists.");
				}
				else
				{
					int x = kb.nextInt();
					int result = polys[index].clacPolynomial(x);
					System.out.println(result);
				}
			
			}
			else if(command.equals("print"))
			{
				char name = kb.next().charAt(0);
				int index = find(name);
				if(index == -1)
				{
					System.out.println("No such polynomial exists.");
				}
				else
				{
					polys[index].printPolynomial();
				}
			}
			else if(command.equals("exit"))
			{
				break;
			}
		}
		kb.close();
		
	}
		

	private static int find(char name) {
		for(int i = 0; i<n; i++)
		{
			if(polys[i].name == name)
				return i;
		}
		return -1;
	}

}
