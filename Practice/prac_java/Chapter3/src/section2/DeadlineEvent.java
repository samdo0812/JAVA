package section2;

public class DeadlineEvent extends Event{
	public String title;
	public MyDate deadline;
	
	public DeadlineEvent(String title, MyDate date) {
		super(title);
		this.title = title;
		this.deadline = date;
	}
	
	public String toString() {
		return title + ", " + deadline.toString();
	}
}
