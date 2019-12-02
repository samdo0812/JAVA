package section3;

public class Term3 {
	public int coef;	//계수
	public int expo;	//차수
	
	public Term3(int c, int e) {
		
		coef = c;
		expo = e;
	}
	
	public int calcTerm(int x) {
		return (int)(coef * Math.pow(x, expo));
	}
	
	public void printTerm() {
		System.out.print(coef + "x^" + expo);	//-x^2 ==> -1x^2
		
	}
}
