package Section1;

public class Code01_2 {

	public static void main(String[] args) {
		
		Person1 first;
		first = new Person1(); //object
		
		first.name = "John";
		first.number = "01012345678";
		
		System.out.println("Name : " + first.name + ", Number : " + first.number);
		
		Person1 second = first;
		second.name = "Tom";
		System.out.println("Name : " + second.name + ", Number : " + second.number);
		
		
		
		Person1[] members = new Person1[100];
		members[0] = first;
		members[1] = second;
		
		System.out.println("Name : " + members[0].name + ", Number : " + members[0].number);
		System.out.println("Name : " + members[1].name + ", Number : " + members[1].number);
		
		/*
		mebers는 배열의 이름이므로 당연히 참조 변수이다.
		배열의 각 칸은 Person1 타입이다. 그런데 Person1은 프리미티브 타입이 아니다.
		따라서 배열의 각 칸도 참조변수이다.
		즉 이상ㅌ에서 배열의 가 칸에는 다음가 같이 바로 이름과 번호를 저장 할 수는 없다
		members[2].name = "David";
		members[2].number ="12312124";
		*/
		
		members[2] = new Person1();
		members[2].name = "David";
		members[2].number ="12312124";
		System.out.println("Name : " + members[2].name + ", Number : " + members[2].number);
		
		
	}
}
