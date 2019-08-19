package Practice;

public class prac_2 {
	
	//class 
	
	static class Player{
		
		String name;
		int hp;
		int atk;
		
		public Player(String name, int hp, int atk) {
			this.name = name;
			this.hp = hp;
			this.atk = atk;
		}
		
		public void attack(Enemy enemy) {
			System.out.println("Player's attack!");
			enemy.hp -= this.atk;
			System.out.println("enemy hp : " + enemy.hp);
		}
		
		public boolean isLive() {
			if (hp <= 0) 
			{
				return false;
			}
			else 
			{
				return true;
			}
		}
	}
	static class Enemy{
		
		String name;
		int hp;
		int atk;
		
		public Enemy(String name, int hp, int atk) {
			this.name = name;
			this.hp = hp;
			this.atk = atk;
		}
		
		public void attack(Player player) {
			System.out.println("Enemy's attack!");
			player.hp -= this.atk;
			System.out.println("player hp : " + player.hp);
		}
		
		public boolean isLive() {
			if (hp <= 0) 
			{
				return false;
			}
			else 
			{
				return true;
			}
		}
	}
	
	public static void main(String[] args) {
		
		Player player = new Player("player", 100, 12);
		Enemy enemy = new Enemy("boss", 80, 5);
		
		while(player.isLive() && enemy.isLive()) {
			player.attack(enemy);
			if (!enemy.isLive()) break;
				enemy.attack(player);
		}
		
		if(player.isLive()) {
			System.out.println("player win");
		}
		else {
			System.out.println("enemy win");
		}
		
	}
}
	
	