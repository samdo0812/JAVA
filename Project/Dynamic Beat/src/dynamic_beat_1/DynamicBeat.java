package dynamic_beat_1;

import javax.swing.JFrame;

public class DynamicBeat extends JFrame{
	
	public DynamicBeat() {
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null); //실행 했을 때 컴퓨터의 정중앙으로 뜨도록
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//게임창을 종료 했을 때 프로그램 모두 종료
		setVisible(true); //창이 보이도록, 출력이 되도록
	}
}
