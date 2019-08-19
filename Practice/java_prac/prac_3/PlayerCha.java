package Practice;

public class PlayerCha extends Character {
	public PlayerCha(String name, int hp, int atk) {
		super(name, hp, atk);	//»ý¼ºÀÚ
	}
	
	public void Heal() {
		hp += 20;
		System.out.println(name + " HEAL !");
		System.out.println(name + " HP : " + hp);
	}
}
