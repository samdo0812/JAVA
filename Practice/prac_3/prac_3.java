package Practice;

public class prac_3 {
	
	//class extends
	
	public static void main(String[] args) {
		
		PlayerCha player = new PlayerCha("player", 70, 12);
		EnemyCha enemy = new EnemyCha("boss", 80, 5);
		
		while(player.isLive() && enemy.isLive()) {
			player.attack(enemy);
			if (!enemy.isLive()) break;
				enemy.attack(player);
				System.out.println("---------------------------------");
		}
		
		if(player.isLive()) {
			System.out.println("player win");
		}
		else {
			System.out.println("enemy win");
		}
		
	}
}
	
	