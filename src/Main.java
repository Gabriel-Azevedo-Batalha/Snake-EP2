import javax.swing.JFrame;

public class Main {
	public Main() {
		
		boolean exited = false;
		
		//Loop para restart
		while (!exited) {
			
			//Menu loop
			Menu menu = new Menu();
			while(!menu.isStarted()) {
				//Atribuição sem efeito para o while funcionar
				menu = menu;
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
				//Atribuição sem efeito para o while funcionar
				screen = screen;
			}
			exited = screen.isExited();
			screen.getFrame().dispose();
		}
		
	}
	public static void main(String[] args) {
		new Main();
	}

}
