import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

public class Main {
	public Main() {
		
		boolean exited = false;
		
		//Loop para restart
		while (!exited) {
			
			//Menu loop
			Menu menu = new Menu();
			while(!menu.isStarted()) {
				//Sleep para o while funcionar
				try {
					TimeUnit.MILLISECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			String tipoSnake = menu.getSnake();
			menu.getFrame().dispose();
			JFrame frame = new JFrame();
			Painel painel = new Painel(tipoSnake);
			frame.add(painel);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setTitle("Snake EP2");
			frame.pack();
			frame.setLocationRelativeTo(null);
		
		
			frame.setVisible(true);
		
			//Game loop
			while(true) {
				if(!painel.tick()) {
					break;
				}
			}
			int points = painel.getPoints();
			frame.dispose();
			
			//Gameover loop
			GameoverScreen screen = new GameoverScreen(points);
			screen.setRestarted(false);
			while(!screen.isRestarted()) {
				//Sleep para o while funcionar
				try {
					TimeUnit.MILLISECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			exited = screen.isExited();
			screen.getFrame().dispose();
		}
		
	}
	public static void main(String[] args) {
		new Main();
	}

}
