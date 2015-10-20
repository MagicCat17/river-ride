package happyclock.tasks;

import happyclock.Robo;

import java.awt.event.KeyEvent;

public class RunAround implements ToonTask {
	
	@Override
	public void perform() {
		Robo.press(KeyEvent.VK_UP, 2000);
		Robo.wait(4000);
		Robo.press(KeyEvent.VK_RIGHT, 5000);
		Robo.wait(200);
		Robo.press(KeyEvent.VK_LEFT, 4000);
		Robo.wait(50);
		Robo.press(KeyEvent.VK_DOWN, 2000);
	}
	
}
