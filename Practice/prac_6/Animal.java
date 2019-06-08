package Practice;

public abstract class Animal implements Pet {
	
	public String name;
	public Animal(String name) {
		this.name = name;
	}
	public abstract void Cry();
	
	@Override
	public void FoodCall() {
		// TODO Auto-generated method stub
		
	}
}
