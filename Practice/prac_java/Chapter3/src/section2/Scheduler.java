package section2;

import java.util.Scanner;

public class Scheduler {
	
	private int capacity = 2;
	public Event[] events = new Event[capacity];
	public int n = 0;
	private Scanner scan;
	
	public void processCommand() {
		scan = new Scanner(System.in);
		while(true) {
			System.out.print("$ ");
			String command = scan.next();
			if (command.equals("addevent")) {
				String type = scan.next();
				if(type.equalsIgnoreCase("oneday")) {
					handleAddOneDayEvent();
				}
				else if(type.equalsIgnoreCase("duration")) {
					handleAddDurationEvent();
				}
				else if(type.equalsIgnoreCase("deadline")) {
					handleAddDeadlineEvent();
				}
			}
			else if(command.equals("list")) {
				handleList();
			}
			else if(command.equals("show")) {

			}
			else if(command.equals("exit")) {
				break;
			}
		}
		scan.close();
	}

	private void handleList() {
		for (int i = 0; i < n; i++) {
			System.out.println("  " + events[i].toString());	//dynamic binding
		}
		
	}

	private void handleAddDeadlineEvent() {

		
	}

	private void handleAddDurationEvent() {

	}

	private void handleAddOneDayEvent() {
		System.out.print(" when : ");
		String dateString = scan.next();	//dateString = "2020/02/09"
		System.out.print(" title : ");
		String title = scan.next();
		
		
		MyDate date = parseDateString(dateString);	//dateString의 값을 숫자로만 -> OneDayEvent생성자에서 Mydate타입으로 받음
		OneDayEvent ev = new OneDayEvent(title, date); 
		//System.out.println(ev.toString());
		
		addEvent(ev);
		
	}

	
	private void addEvent(OneDayEvent ev) {
		if(n>=capacity)
		{
			reallocate();
		}
		events[n++] = ev; //다형성 확인
	}

	private void reallocate() {
		Event [] tmp = new Event[capacity * 2];
		for (int i = 0; i < n; i++) {
			tmp[i]=events[i];
		}
		events = tmp;
		capacity *= 2;
	}

	private MyDate parseDateString(String dateString) {
		String []tokens = dateString.split("/");
		//tokens[]는 문자열을 받음, Mydate클래스에서는 정수 값으로 받음
		int year = Integer.parseInt(tokens[0]);
		int month = Integer.parseInt(tokens[1]);
		int day = Integer.parseInt(tokens[2]);
		
		MyDate d = new MyDate(year, month, day);
		return d;
	}

	
	
	
	public static void main(String[] args) {
		
		Scheduler app = new Scheduler();
		app.processCommand();

	}

}
