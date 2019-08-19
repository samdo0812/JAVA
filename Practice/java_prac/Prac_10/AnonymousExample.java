package Practice;

public class AnonymousExample {

	public static void main(String[] args) {
		Anonymous anony = new Anonymous();
		
		anony.field.turnOn();
		anony.method1();
		anony.method2(new RemoteControl() {
			
			@Override
			public void turnOn() {
				System.out.println("smart Tv on");
			}
			
			@Override
			public void turnOff() {
				System.out.println("Smart tv off");
			}
		});
	}
}
