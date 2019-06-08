package Practice;

public class prac_6 {
	
	// interface
	
	public static void main(String[] args) { 
		
		Animal dog = new Dog("baduk");
		Animal cat = new Cat("Nabi");
		Animal wolf = new Wolf("waoooo");
		
		Pet pet = new Cat("nabi");
		Pet pet2 = new Dog("baduk");
		
		pet.FoodCall();
		pet2.FoodCall();
	}
}
	
	