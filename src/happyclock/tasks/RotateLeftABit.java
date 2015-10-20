package happyclock.tasks;

import happyclock.Robo;

import java.awt.event.KeyEvent;

public class RotateLeftABit implements ToonTask {
	
	@Override
	public void perform() {
		Robo.press(KeyEvent.VK_LEFT, 50);
		Robo.wait(50);
		Robo.press(KeyEvent.VK_LEFT, 50);
		Robo.wait(50);
	}
	
}