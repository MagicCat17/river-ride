package happyclock;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

public class LabelChanger {
	
	private JLabel label;
	private Timer timer;
	
	private String[] titles = {
			"Wakeup Toony",
			"By",
			"Daddy Cool Software",
			"",
			"Hes crazy like a fool",
			"howabout Daddy Cool",
			"Daddy",
			"..",
			"Daddy Cool",
			"Daddy",
			"..",
			"Daddy Cool",
			"da da da da da",
			"Daddy Cool",
			"LOL :-)",
			""
	};
	private int index = 0;
	
	public LabelChanger(JLabel label) {
		this.label = label;
	}
	
	public void start() { 
		timer = new Timer(2000,new ActionListener() { @Override
		public void actionPerformed(ActionEvent e) {
			changeLabel();
		}});
		timer.start();
	}

	protected void changeLabel() {
		String title = titles[index];
		label.setText(title);
		index++;
		if (index == titles.length) {
			index = 0;
		}
	}

}
