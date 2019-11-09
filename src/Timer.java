import java.util.concurrent.TimeUnit;

public class Timer implements Runnable{

	private Thread thread;
	private boolean running, sumir, delay, update;
	
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
	public Timer() {
		sumir = false;
		delay = true;
		start();
		
	}
	public void start() {
		
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	public void stop() {
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void run() {
		System.out.println("Running");
		while(running) {
			
			
			try {
				TimeUnit.MILLISECONDS.sleep(70);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println(sumir);
			
			
			
			if(sumir == true) Sumir();
			if(delay == true) Delay();
		}
	}
	public void Sumir() {
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sumir = false;
	}
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
