package section2;

public class DurationEvent {
	public String title;
	public MyDate begin;
	public MyDate end;
	
	public DurationEvent(String title, MyDate b, MyDate e) {
		this.title = title;
		begin = b;
		end = e;
	}
	public String toString() {
		return title + ", " + begin.toString() + "~" + end.toString();
	}
}
