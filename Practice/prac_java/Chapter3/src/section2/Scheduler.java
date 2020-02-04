package section2;

import java.util.Scanner;

public class Scheduler {
	
	public Event[] events = new Event[100];
	public int n = 0;
	
	public void processCommand() {
		Scanner scan = new Scanner(System.in);
		while(true) {
			System.out.println("$ ");
			String command = scan.next();
			if (command.equals("addevent")) {
				
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

	public static void main(String[] args) {
		
		Scheduler app = new Scheduler();
		app.processCommand();

	}

}
