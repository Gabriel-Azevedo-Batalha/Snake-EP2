import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu {
	
	
	private JLabel statusLabel;
	private String snake = "Simple";
	private JFrame frame;
	private boolean started = false;
	
	
	public JFrame getFrame() {
		return frame;
	}
	public String getSnake() {
		return snake;
	}
	public boolean isStarted() {
		return started;
	}
	
	public Menu() {
		
		frame = new JFrame();
		JPanel panel = new JPanel();
		JButton startButton = new JButton("Start");
		JButton simpleButton = new JButton("Simple Snake");
		JButton kittyButton = new JButton("Kitty Snake");
		JButton starButton = new JButton("Star Snake");
		
		panel.setLayout(new BorderLayout());
		
		statusLabel = new JLabel("Simple Snake selecionada", JLabel.CENTER);
		statusLabel.setSize(400, 100);
		
		frame.setSize(500,500);
		//frame.setLocation(500, 200);
		frame.setLayout(new GridLayout(2,1));
		
		startButton.setActionCommand("Start");
		startButton.addActionListener(new EventoBotao());
		
		simpleButton.setActionCommand("Simple");
		simpleButton.addActionListener(new EventoBotao());
		
		kittyButton.setActionCommand("Kitty");
		kittyButton.addActionListener(new EventoBotao());
		
		starButton.setActionCommand("Star");
		starButton.addActionListener(new EventoBotao());
		
		panel.setSize(500,600);
		panel.add(startButton,BorderLayout.PAGE_START);
		panel.add(simpleButton,BorderLayout.LINE_START);
		panel.add(kittyButton,BorderLayout.CENTER);
		panel.add(starButton,BorderLayout.LINE_END);
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Snake EP2");
		frame.add(panel);
		frame.add(statusLabel,BorderLayout.PAGE_END);
		frame.pack();
		frame.setSize(500,500);
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
	}
	private class EventoBotao implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String comando = e.getActionCommand();
			if(comando.equals("Start")) {
				started = true;
			}
			else if(comando.equals("Simple")) {
				statusLabel.setText("Simple Snake selecionada");
				snake = "Simple";
			}
			else if(comando.equals("Kitty")) {
				statusLabel.setText("Kitty Snake selecionada");
				snake = "Kitty";
			}
			else if(comando.equals("Star")) {
				statusLabel.setText("Star Snake selecionada");
				snake = "Star";
			}
		}
	}
	

	
}
