package happyclock;

import happyclock.tasks.JumpOnTheSpot;
import happyclock.tasks.LeftAndRight;
import happyclock.tasks.ReadBook;
import happyclock.tasks.RotateLeftABit;
import happyclock.tasks.RunAround;
import happyclock.tasks.ToonTask;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Random;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Waker implements RiverListener {
	
	// unclemat17 melodious
	
	private Displayer displayer;
	private boolean running;
	private Timer timer;
	private Random random = new Random();
	
	private static final int INTERVAL = 120;
	private static final int RANGE = 60;
	private int now = 60;
	private long suicide = calculateSuicide();
	
	private TryForRiver riverTrier = new TryForRiver();
	
	private ToonTask[] tasks = {
			new JumpOnTheSpot(),
			new LeftAndRight(),
			new LeftAndRight(),
			new RotateLeftABit(),
			new RotateLeftABit(),
			new ReadBook(),
			new RunAround()
	};
	private boolean riverMode;
	private RiverListener listener;
	private ColourWatcher watcher;
	
	public Waker(Displayer displayer,ColourWatcher watcher) {
		this.displayer = displayer;
		this.watcher = watcher;
	}
	
	private long calculateSuicide() {
		Date dt = new Date();
		int h = dt.getHours();
		int hoursTillElevenIsh = 22 - h;
		if (hoursTillElevenIsh <= 0) return 60*60;
		return 60 * 60 * hoursTillElevenIsh;
	}

	public void start(RiverListener listener) {
		this.listener = listener;
		timer = new Timer(1000,new ActionListener() { @Override
			public void actionPerformed(ActionEvent e) {
				timerTick();
			}});
		timer.start();
		showStatus();
	}

	protected void timerTick() {
		suicide--;
		if (suicide <= 0) {
			running = false;
			showStatus();
		}
		if (!running) return;
		displayer.showTime(now,suicide);
		if (riverMode && now == 118) {
			tryForRiver();
		}
		if (now <= 0) {
			performAction();
			recalculateTimeToGo();
			return;
		}
		now--;
	}

	private void tryForRiver() {
		riverTrier.go(watcher,this);
	}

	private void recalculateTimeToGo() {
		int t = random.nextInt(RANGE);
		now = INTERVAL + t;
	}

	private void performAction() {
		int taskIndex = random.nextInt(tasks.length);
		ToonTask t = tasks[taskIndex];
		try {
			t.perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Performing "+t.getClass().getSimpleName());
	}

	private void showStatus() {
		String status = running ? "<<<< RUNNING >>>>" : "";
		String button = running ? "STOP" : "Start";
		displayer.showStatus(status);
		displayer.setButton(button);
	}

	public void clicked() {
		running = !running;
		if (running) {
			calculateSuicide();
		}
		showStatus();
	}

	public void setRiverMode(boolean selected) {
		System.out.println("Goto River Mode "+selected);
		riverMode = selected;
	}
	

	protected void swingStopRiver() {
		listener.onRiverJumped();
	}

	@Override
	public void onRiverJumped() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				swingStopRiver();
			}});
	}

}
