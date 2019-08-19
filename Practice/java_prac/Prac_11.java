public class ClassCastException {

	public static void main(String[] args) {
		
		Dog dog = new Dog();
		changeDog(dog);
		
		Cat cat = new Cat();
		changeDog(cat);
	}
	
	public static void changeDog(Animal animal) {
		if(animal instanceof Dog) {
			Dog dog = (Dog)animal;	//ClassCastException 발생가능 15Line으로 check
									//11Line에서 Cat 객체를 매개값으로 주었기 떄문에 Dog타입으로 변환 할 수 없다.
		}
	}

}

class Animal{}
class Dog extends Animal{}
class Cat extends Animal{}
