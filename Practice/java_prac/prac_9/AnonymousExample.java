package Practice;

public class AnonymousExample {

	public static void main(String[] args) {
		Anonymous anony = new Anonymous();
		
		anony.field.wake();
		anony.method1();
		anony.method2(new Person() {
			@Override
			void wake() {
				System.out.println("8�ÿ� �Ͼ�ϴ�.");
				study();
			}
			void study() {
				System.out.println("�����մϴ�.");
			}
		});
	}

}
