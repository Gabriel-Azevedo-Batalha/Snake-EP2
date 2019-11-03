import javax.swing.JFrame;

public class Main {
	public Main() {
		
		
		Menu menu = new Menu();
		while(!menu.isStarted()) {
			//Atribuição sem efeito para o while funcionar
			menu = menu;
		}
		System.out.println("Start");
		menu.getFrame().dispose();
		JFrame frame = new JFrame();
		Painel painel = new Painel();
		//JButton button = new JButton("Start");
		
		frame.add(painel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Snake EP2");
		frame.setLocationRelativeTo(null);
		
		frame.pack();
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
