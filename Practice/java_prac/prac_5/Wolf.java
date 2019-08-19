package Practice;

public class Wolf extends Animal {
	
	public Wolf(String name) {
		super(name);
	}
	
	@Override
	public void Cry() {
		System.out.println(name + "~~~");
	}
}
