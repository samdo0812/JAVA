package Practice; 

public class Character {
	String name;
	int hp;
	int atk;
	
	public Character(String name, int hp, int atk) {
		this.name = name;
		this.hp = hp;
		this.atk = atk;
	}
	
	public void attack(Character enemy) {
		System.out.println(this.name + "'s attack !");
		enemy.hp -= this.atk;
		System.out.println(enemy.name + " HP : " + enemy.hp);
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
