package Practice;

public class Anonymous {
	Person field = new Person() {
		@Override
		void wake() {
			System.out.println("6�ÿ� �Ͼ�ϴ�");
			work();
		}

		void work() {
			System.out.println("����մϴ�.");
		}
	};

	void method1(){
		Person localVar = new Person() {
			@Override
			void wake() {
				System.out.println("7�ÿ� �Ͼ�ϴ�.");
				walk();
			}
			
			void walk() {
				System.out.println("��å�մϴ�");
			}
		};
		localVar.wake();
	}
	
	void method2(Person person) {
		person.wake();
	}
}
