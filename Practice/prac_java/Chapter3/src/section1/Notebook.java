package section1;

public class Notebook extends Computer {
	
	public double screenSize;
	public double weight;
	
	
	public Notebook(String man, String proc, int ram, int disk, double speed, double screen,
			double weight)
	{
		/*
		computer클래스(부모) 생성자를 먼저 호출한다.
		super를 통해 명시적으로 호출해주거나
		그렇지 않을 경우에는 자동으로 기본 생성자를 호출한다.
		*/
		super(man, proc, ram, disk, speed);
		manufacturer = man;
		processor = proc;
		ramSize = ram;
		diskSize = disk;
		processorSpeed = speed;
		screenSize = screen;
		this.weight = weight;
	}
	
	public static void main(String[] args) {
		Notebook test = new Notebook("LG", "i7", 16, 1000, 3.2, 15, 1);

		System.out.println(test.computePower());
		System.out.println(test.toString());
	}
}
