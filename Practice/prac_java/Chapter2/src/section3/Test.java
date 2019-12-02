package section3;

public class Test {
	
	static int s = 0;
	int t = 0;
	
	public static void print1() {
		System.out.println("s = " + s);
	  //System.out.println("t = " + t);
	}
	
	public void print2() {
		System.out.println("s = " + s);
	  //System.out.println("t = " + t);
	}
	
	public static void main(String[] args) {
		s = 100;
		//t = 100;
		print1();
		//print2();
		
		Test t1 = new Test();
		t1.t = 100;
		t1.print2();
	}
}
