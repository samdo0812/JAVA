package section2;

public class DeadlineEvent {
	public String title;
	public MyDate deadline;
	
	public DeadlineEvent(String title, MyDate date) {
		this.title = title;
		this.deadline = date;
	}
	
	public String toString() {
		return title + ", " + deadline.toString();
	}
}
