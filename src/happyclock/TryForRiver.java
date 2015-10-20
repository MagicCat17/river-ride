package happyclock;

import java.awt.event.KeyEvent;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TryForRiver {
	
	private static Executor runner = Executors.newSingleThreadExecutor();
	private ColourWatcher watcher;
	private RiverListener listener;
	
	public void go(ColourWatcher watcher,RiverListener listener) {
		this.watcher = watcher;
		this.listener = listener;
		Runnable r = new Runnable() { @Override
		public void run() {
			try {
				runTask();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}};
		runner.execute(r);
	}

	protected void runTask() throws InterruptedException {
		for (int i=0; i<7; i++) {
			if (tryForRiver()) {
				return;
			}
			Thread.sleep(2000);
		}
	}
	
	protected boolean tryForRiver() throws InterruptedException {
		Robo.press(KeyEvent.VK_F8, 50);
		Thread.sleep(1000);
		for (int i=0; i<100; i++) {
			Thread.sleep(100);
			//System.out.println(watcher.getColour());
			if (watcher.getColour().equals("GREEN")) {
				System.out.println("Green!!");
				Robo.clickMouse();
				listener.onRiverJumped();
				System.out.println("River...");
				closeBook();
				return true;
			}
		}
		closeBook();
		return false;
	}

	private void closeBook() throws InterruptedException {
		Thread.sleep(2000);		
		Robo.press(KeyEvent.VK_F8, 50);
	}

}
