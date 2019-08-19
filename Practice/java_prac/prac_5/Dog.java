package Practice;

public class Dog extends Animal {
	
	public Dog(String name) {
		super(name);
	}
	
	@Override
	public void Cry() {
		System.out.println(name + "~~~");
	}
}
