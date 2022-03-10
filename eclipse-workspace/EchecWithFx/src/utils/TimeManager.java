package utils;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TimeManager implements Runnable{
	private long[] timers;
	private final long DEFAULT_TIME = 2400000;
	private Jeu jeu;
	private Text timersText[];
	//private 
	public TimeManager(Jeu _jeu) {
		timers = new long[2];
		timersText = new Text[2];
		timers[0] = timers[1] = DEFAULT_TIME;
		jeu = _jeu;
		timersText[0] =  new Text();
		timersText[1] = new Text();
		timersText[0].setFont(new Font(32));
		timersText[1].setFont(new Font(32));
		timersText[0].setText(this.getTimeToString(false));
		timersText[1].setText(this.getTimeToString(true));

	}
	
	public TimeManager(Jeu _jeu, long _time) {
		timers = new long[2];
		timers[0] = timers[1]= _time;
	}
	
	public TimeManager(Jeu _jeu, long _timeWhite, long _timeBlack) {
		timers = new long[2];
		timers[0] = _timeWhite;
		timers[1] = _timeBlack;
		jeu = _jeu;
	}

	
	public TimeManager(long _timers[], Jeu _jeu) {
		
		timers = _timers;
		jeu = _jeu;
	}
	
	public TimeManager(TimeManager from) {
		this(from.timers,from.jeu);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		long bc = Utils.getEllapsedTimeInMs();
		int id;
		while (timers[0] > 0 &&
			timers[1] > 0 &&
			jeu != null) {
			
			try {
				Thread.sleep((long)50);
			} catch (InterruptedException e) {
			}
			id =  (jeu.getJoueurActuel().estNoir() ? 1:0);
			timers[id] -= Utils.getEllapsedTimeInMs()-bc;
			timers[id] = (timers[id] < 0 ? 0:timers[id]);
			bc = Utils.getEllapsedTimeInMs();
			dessiner();
			
		}
		if (timers[0] == 0) {
			jeu.setOnEchec(GAMESTATUS.BLACK_CHECKMATE);
		} else if (timers[1] == 0) {
			jeu.setOnEchec(GAMESTATUS.WHITE_CHECKMATE);
		}
		
	}
	
	public String getTimeToString() {
		long convert = (jeu.getJoueurActuel().estNoir() ? timers[1] : timers[0]);
		long min, sec, centiemes;
		centiemes = (convert/10)%100;
		sec = (convert/1000)%60;
		min = (convert/60000);
		return min + ":" + sec + "." + centiemes;
				
		
	}
	
	public String getTimeToString(boolean estNoir) {
		long convert = (estNoir ? timers[1] : timers[0]);
		long min, sec, centiemes;
		centiemes = (convert/10)%100;
		sec = (convert/1000)%60;
		min = (convert/60000);
		return (min < 10 ? "0"+ min: min) + ":" + (sec < 10 ? "0"+sec : sec ) + "." + (centiemes < 10 ? "0"+centiemes:centiemes);
				
		
	}
	
	private String convertToStr(long convert) {
		long min, sec, centiemes;
		centiemes = (convert/10)%100;
		sec = (convert/1000)%60;
		min = (convert/60000);
		return min + ":" + sec + "." + centiemes;
	}
	
	
	private void dessiner() {
		jeu.dessinerTimer(getTimeToString(jeu.getJoueurActuel().estNoir()));
	}
	
	
	
}
