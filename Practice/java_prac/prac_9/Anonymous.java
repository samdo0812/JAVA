package Practice;

public class Anonymous {
	Person field = new Person() {
		@Override
		void wake() {
			System.out.println("6시에 일어납니다");
			work();
		}

		void work() {
			System.out.println("출근합니다.");
		}
	};

	void method1(){
		Person localVar = new Person() {
			@Override
			void wake() {
				System.out.println("7시에 일어납니다.");
				walk();
			}
			
			void walk() {
				System.out.println("산책합니다");
			}
		};
		localVar.wake();
	}
	
	void method2(Person person) {
		person.wake();
	}
}
