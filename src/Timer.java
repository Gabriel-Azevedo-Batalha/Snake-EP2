import java.util.concurrent.TimeUnit;

public class Timer implements Runnable{

	private Thread thread;
	private boolean running, sumir, delay;
	
	public boolean isSumir() {
		return sumir;
	}
	public void setSumir(boolean sumir) {
		this.sumir = sumir;
	}
	public boolean isDelay() {
		return delay;
	}
	public void setDelay(boolean delay) {
		this.delay = delay;
	}
	
	//Construtor
	public Timer() {
		sumir = false;
		delay = true;
		start();
		
	}
	//Thread start
	public void start() {
		
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	//Thread stop
	public void stop() {
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//Thread run
	@Override
	public void run() {
		while(running) {
			
			
			try {
				TimeUnit.MILLISECONDS.sleep(70);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(sumir == true) Sumir();
			if(delay == true) Delay();
		}
	}
	
	//Timer para fruta especial sumir
	public void Sumir() {
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sumir = false;
	}
	
	//Delay no spawn de frutas especiais
	public void Delay() {
		try {
			TimeUnit.SECONDS.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		delay = false;
	}
}
