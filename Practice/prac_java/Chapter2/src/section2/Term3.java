package section2;

//다항식을 구성하는 하나의 항을 표현하기 위한 클래스
//계수 coef, 차수 exop를 가진다 
// 2x^3 
public class Term3 {
	public int coef;
	public int expo;
	
	public Term3(int c, int e) {
		
		coef = c;
		expo = e;
	}
	
	public int calcTerm(int x) {
		return (int)(coef * Math.pow(x, expo));
	}
	
	public void printTerm() {
		System.out.print(coef + "x^" + expo);
		
	}
}
