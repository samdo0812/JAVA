package section2;

//���׽��� �����ϴ� �ϳ��� ���� ǥ���ϱ� ���� Ŭ����
//��� coef, ���� exop�� ������ 
// 2x^3 
public class Term2 {
	public int coef;
	public int expo;
	
	public int calcTerm(int x) {
		return (int)(coef * Math.pow(x, expo));
	}
	
	public void printTerm() {
		System.out.print(coef + "x^" + expo);
		
	}
}
