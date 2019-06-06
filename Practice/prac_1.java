package Practice;

import java.util.Random;

public class prac_1 {
	public static void main(String[] args) {
		
		//Arrays, for while loop
		Random random = new Random();
		int money = 10000;
		
		while(money > 0) {
			//buy lotto 
			money -= 500;
			
			//0~10
			int number = random.nextInt(100);
			int lottoMoney = buyLotto(number);
			System.out.println("My number is " + number + " / Lotto : " + lottoMoney);
			money += lottoMoney;
		}
		System.out.println("You don't have money");
	}
	
	static int buyLotto(int number) {
		
		int[] lotto = new int[100];
		
		for (int i = 0; i < lotto.length; i++) {
			
			lotto[i] = 0;
		}
		
		lotto[2] = 100;
		lotto[77] = 1000;
		lotto[99] = 10000;

		return lotto[number];
	}
}
