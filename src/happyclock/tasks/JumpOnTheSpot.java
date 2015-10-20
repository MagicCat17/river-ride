package happyclock.tasks;

import java.awt.event.KeyEvent;

import happyclock.Robo;

public class JumpOnTheSpot implements ToonTask {

	@Override
	public void perform() {
		Robo.press(KeyEvent.VK_CONTROL, 50);
		Robo.wait(2000);
		Robo.press(KeyEvent.VK_CONTROL, 50);
	}

}
