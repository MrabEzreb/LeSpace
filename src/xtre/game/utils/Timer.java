package xtre.game.utils;

public class Timer {
	private long time = 0;
	public final boolean timer(long timeIntervals) {
		if(System.currentTimeMillis() > time){
			time = System.currentTimeMillis() + timeIntervals;
			return true;
		}
		else
			return false;
	}
	
	public final String timedMessage(String message, long timeIntervals){
		if(timer(timeIntervals))
			return message;
		else
			return null;
	}
}
