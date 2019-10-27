public class Chrono implements Runnable {
	private Thread chrono;
	private int interval;
	
	public Chrono() {
		chrono = new Thread(this);
		chrono.start();
		interval = 3;
	}
	
	public int getInterval() {
		return interval;
	}
	
	public void setInterval(int interval) {
		this.interval = interval;
	}
	
	public void dormir() {
		interval = 2000;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(30);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(true) {
			if(this.getInterval() > 3) {
				this.setInterval(this.getInterval() - 1);
			} else {
				Main.fenetre.repaint();
			}
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}