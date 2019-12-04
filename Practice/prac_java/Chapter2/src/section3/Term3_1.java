package section3;

public class Term3_1 {
	private int coef;	//계수
	private int expo;	//차수
	
	public Term3_1(int c, int e) {
		
		coef = c;
		expo = e;
	}
	
	public int getExpo() {
		return expo;
	}
	
	public int calcTerm(int x) {
		return (int)(coef * Math.pow(x, expo));
	}
	
	public void printTerm() {
		System.out.print(coef + "x^" + expo);	//-x^2 ==> -1x^2
		
	}
	public int getCoef() {
		return coef;
	}
	public void setCoef(int coef) {
		this.coef = coef;
	}
}
