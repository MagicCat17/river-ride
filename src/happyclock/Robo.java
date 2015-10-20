package happyclock;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Robo {
	
	private static Robot robot = createRobot();
	private static Random random = new Random();
	
	public static void press(int key,int millis) {
		robot.keyPress(key);
		int delay = getApprox(millis);
		robot.delay(delay);
		robot.keyRelease(key);
		//robot.waitForIdle();
	}
	
	public static void wait(int ms) {
		int delay = getApprox(ms);
		robot.delay(delay);
		//robot.waitForIdle();
	}
	
	private static int getApprox(int ms) {
		int n = random.nextInt((int)(ms*.4));
		return ms + n;
	}
	private static Robot createRobot() { 
		try {
			return new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void clickMouse() {
		robot.mousePress(InputEvent.BUTTON1_MASK );
		robot.delay(20);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	
	public static int[] getColourUnderPointer() { 
		final int d = 10;
		Point location = MouseInfo.getPointerInfo().getLocation();
		Rectangle r = new Rectangle(location.x-(d/2),location.y-(d/2),d,d);
		BufferedImage im = robot.createScreenCapture(r);
		int red=0, green=0, blue = 0;
		for (int x=0; x<d; x++) {
			for (int y=0; y<d; y++) {
				int p = im.getRGB(x, y);
				Color c = new Color(p);
				red += c.getRed();
				green += c.getGreen();
				blue += c.getBlue();
			}
		}
		im.flush();
		return new int[] { red, green, blue };
	}

}
