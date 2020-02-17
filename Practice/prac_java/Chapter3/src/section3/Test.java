package section3;

public class Test { //subclass of Object
	public int a = 10;
	public double x = 1.23;
	
	public String toString() {
		return a + " " + x;
	}
	
	public boolean equals(Object other) { //overriding
		Test other2 = (Test)other;	//type casting
		return a == other2.a && x == other2.x; 
	}
	public static void main(String[] args) {
		Test test1 = new Test();
		Test test2 = new Test();
	
		//test2.a = 5;
		//test2.x = 1.25646;
		
		System.out.println(test1.toString());
		
		if(test2.equals(test1)) {
			System.out.println("yes");
		}
		else {
			System.out.println("no");
		}
		
		//wrapper
		//1
		Object [] array = new Object[100];
		
		int a = 20;
		//Integer age = new Integer(a);
		//array[0] = age;
		//int b = age.intValue();
		
		
		array[0] = a; //auto boxing
		int b = (Integer)array[0]; //auto unboxing
		
		System.out.println(b);
		
		//2
		String str = "1234";
		int c = Integer.parseInt(str);
		System.out.println(c);
	}
	
}
