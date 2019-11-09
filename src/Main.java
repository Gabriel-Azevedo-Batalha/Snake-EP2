import javax.swing.JFrame;

public class Main {
	public Main() {
		
		
		Menu menu = new Menu();
		while(!menu.isStarted()) {
			//Atribuição sem efeito para o while funcionar
			menu = menu;
		}
		String tipoSnake = menu.getSnake();
		menu.getFrame().dispose();
		JFrame frame = new JFrame();
		Painel painel = new Painel(tipoSnake);
		//JButton button = new JButton("Start");
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
		
	}
	public static void main(String[] args) {
		new Main();
	}

}
