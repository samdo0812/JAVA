package Practice;

public class EnemyCha extends Character{
	public EnemyCha(String name, int hp, int atk) {
		super(name, hp, atk);
	}
	
	@Override
	public void attack(Character enemy) {
		// TODO Auto-generated method stub
		if(hp <= 20 ) {
			System.out.println("Boss is Angry!!");
			this.atk += 15;
		}
		
		super.attack(enemy);
		
		
		PlayerCha player = (PlayerCha)enemy;
		if(player.hp <= 30) {
			player.Heal();
		}
	}
}
