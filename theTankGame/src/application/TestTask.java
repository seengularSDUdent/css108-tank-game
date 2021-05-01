package application;

public class TestTask implements Runnable{
	public synchronized void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i < 100; i++) {
			System.out.println(i);
		}
	}
}
