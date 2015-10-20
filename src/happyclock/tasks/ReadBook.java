package happyclock.tasks;

import happyclock.Robo;

import java.awt.event.KeyEvent;

public class ReadBook  implements ToonTask {

	@Override
	public void perform() {
		Robo.press(KeyEvent.VK_F8, 50);
		Robo.wait(28000);
		Robo.press(KeyEvent.VK_F8, 50);
	}

}
