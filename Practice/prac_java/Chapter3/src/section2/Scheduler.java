package section2;

import java.util.Scanner;

public class Scheduler {
	
	public Event[] events = new Event[100];
	public int n = 0;
	private Scanner scan;
	
	public void processCommand() {
		Scanner scan = new Scanner(System.in);
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

			}
			else if(command.equals("show")) {

			}
			else if(command.equals("exit")) {
				break;
			}
		}
		scan.close();
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
		System.out.println(ev.toString());
		events[n++] = ev; //다형성 확인
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
