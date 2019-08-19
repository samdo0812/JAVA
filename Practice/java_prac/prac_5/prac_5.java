package Practice;

public class prac_5 {
	
	// abstract class
	
	public static void main(String[] args) { 
		
		Animal dog = new Dog("baduk");
		Animal cat = new Cat("Nabi");
		Animal wolf = new Wolf("waoooo");
		
		dog.Cry();
		cat.Cry();
		wolf.Cry();
	}
}
	
	