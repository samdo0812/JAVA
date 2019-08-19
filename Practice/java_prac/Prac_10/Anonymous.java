package Practice;

public class Anonymous {
	RemoteControl field = new RemoteControl() {
		@Override
		public void turnOn() {
			System.out.println("Tv on");
		}
		
		@Override
		public void turnOff() {
			System.out.println("Tv off");
		}
	};
	
	void method1() {
		
		RemoteControl localVar = new RemoteControl() {
			
			@Override
			public void turnOn() {
				System.out.println("Audio on");
			}
			
			@Override
			public void turnOff() {
				System.out.println("Audio off");
			}
		};
		
		localVar.turnOn();
	}
	
	void method2(RemoteControl rc) {
		rc.turnOn();
	}
}
