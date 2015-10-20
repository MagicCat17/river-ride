package happyclock;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MainWindow implements Displayer, RiverListener {

	private JFrame frame = new JFrame();
	private JLabel title = new JLabel();
	private JLabel timer = new JLabel();
	private JLabel status = new JLabel();
	private JLabel colour = new JLabel();
	private JCheckBox check = new JCheckBox ("Goto River");
	private JButton button = new JButton();
	private JLabel instructions = new JLabel();
	
	private ColourWatcher colourWatcher = new ColourWatcher();
	private Waker waker = new Waker(this,colourWatcher);
	
	public static void main(String[] args) {
		new MainWindow().open();
	}
	
	public void open() { 
		setupControls();
		buildFrame();
		updateTitles();
		waker.start(this);
	}

	private void updateTitles() {
		LabelChanger changer = new LabelChanger(title);
		changer.start();
	}
	
	private void style(JComponent c) {
		c.setBackground(Color.BLACK);
		c.setForeground(Color.WHITE);
		c.setOpaque(true);
	}

	private void buildFrame() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(7,1));
		frame.add(title);
		frame.add(timer);
		frame.add(status);
		frame.add(check);
		frame.add(colour);
		frame.add(instructions);
		frame.add(button);
		frame.pack();
		frame.setSize(200,200);
		frame.setTitle("DaddyCool");
		frame.setVisible(true);
	}

	private void setupControls() {
		title.setText("Wakeup Toony");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		timer.setText("0 to go");
		timer.setHorizontalAlignment(SwingConstants.CENTER);
		status.setText("Running");
		status.setHorizontalAlignment(SwingConstants.CENTER);
		button.setText("STOP");
		button.addActionListener(new ActionListener() { @Override
			public void actionPerformed(ActionEvent e) {
				waker.clicked();
			}});
		instructions.setText("Click start, then goto TT");
		instructions.setHorizontalAlignment(SwingConstants.CENTER);
		
		check.addActionListener(new ActionListener() { @Override
			public void actionPerformed(ActionEvent e) {
			waker.setRiverMode(check.isSelected());
			if (check.isSelected()) {
				colourWatcher.start(colour);
			} else {
				colourWatcher.stop();
			}
		}});
		
		style(title);
		style(timer);
		style(status);
		style(check);
		style(colour);
		style(instructions);
		
		Font big = new Font("Trebuchet MS",Font.PLAIN,17);
		Font big2 = new Font("Trebuchet MS",Font.PLAIN,19);
		title.setFont(big);
		timer.setFont(big2);
		status.setForeground(Color.GREEN);
		frame.setBackground(Color.BLACK);
	}
	
	@Override
	public void onRiverJumped() {
		check.setSelected(false);
		colour.setText("RIVER RIVER!!!");
		waker.setRiverMode(false);
	}
	

	@Override
	public void showMessage(String msg) {
		timer.setText(msg);
	}

	@Override
	public void showStatus(String msg) {
		status.setText(msg);
	}

	@Override
	public void showTime(int seconds,long suicide) {
		String format = formatTime(suicide);
		timer.setText(seconds + " to go. ("+format+")");
	}

	private String formatTime(long suicide) {
		int hh = (int)(suicide / (60*60));
		long t = suicide - (hh*60*60);
		int mm = (int)(t / 60);
		int sec = (int)(t - (mm*60));
		return String.format("%02d:%02d:%02d",hh,mm,sec);
	}

	@Override
	public void setButton(String label) {
		button.setText(label);
	}


}
