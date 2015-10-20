package happyclock;

import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

public class ColourWatcher {
	
	private Timer timer;
	private JLabel label;
	private String colour = "";

	public void start(JLabel label) {
		this.label = label;
		timer = new Timer(200,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				onTimerFired();
			}
		});
		timer.start();
	}
	
	public synchronized String getColour() { return colour; }
	
	protected synchronized void onTimerFired() {
		int[] rgb = Robo.getColourUnderPointer();
		String c = getColour(rgb);
		colour = c;
		label.setText(c);
	}

	private String getColour(int[] rgb) {
		if (rgb[0] > rgb[1]) return "RED";
		if (rgb[1] > rgb[0]) return "GREEN";
		return "!";
	}

	public void stop() { 
		timer.stop();
	}

}
