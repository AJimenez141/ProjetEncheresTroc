package fr.eni.projet.test;

import java.util.Date;
import java.util.TimerTask;
import java.util.Timer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TestTimerTask {
	
	private static class MyTimeTask extends TimerTask
	{

	    public void run()
	    {
	       System.out.println("il est 9h49!");
	    }
	}

	public static void main(String[] args) {

		 //the Date and time at which you want to execute
	    DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    
	    Date date = null;
		try {
			date = dateFormatter .parse("2023-05-25 09:49:30");
		} catch (ParseException e) {
			e.printStackTrace();
		}

	    //Now create the time and schedule it
	    Timer timer = new Timer();

	    //Use this if you want to execute it once
	    timer.schedule(new MyTimeTask(), date);

	}

}
