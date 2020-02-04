package section2;

public class OneDayEvent extends Event {
	public String title;
	public MyDate date;
	
	public OneDayEvent(String title, MyDate date) {
		super(title);	/*
						부모 클래스의 생성자를 호출 해야 하는데 기본 생성자가 없다.
						부모 클래스에 기본생성자가 없기 때문에 명시적으로 존재하는 생성자 호출
						*/
		this.title = title;
		this.date = date;
	}
	
	public String toString() {
		return title + ", " + date.toString();
	}
}
