import javax.swing.JFrame;

public class Main {
	public Main() {
		
		JFrame frame = new JFrame();
		Painel painel = new Painel();
		
		frame.add(painel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Snake EP2");
		frame.setLocationRelativeTo(null);
		
		frame.pack();
		frame.setVisible(true);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}
