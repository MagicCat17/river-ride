package happyclock;

public interface Displayer {
	
	void setButton(String label);
	void showMessage(String msg);
	void showStatus(String status);
	void showTime(int seconds, long suicide);

}
