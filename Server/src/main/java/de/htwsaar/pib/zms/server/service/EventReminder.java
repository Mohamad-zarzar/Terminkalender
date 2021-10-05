package de.htwsaar.pib.zms.server.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.htwsaar.pib.zms.server.model.Event;
import de.htwsaar.pib.zms.server.model.Notification;
import de.htwsaar.pib.zms.server.repository.EventRepository;

/**
 * @author Kamikaze Stefan 
 * 
 * Service für Erinnerungsbenachrichtungen
 *
 */

@Service
public class EventReminder{

	
	private EventService eventS;
	
	@Autowired
	public EventReminder (EventService eventS) {
		this.eventS =eventS;
	}
	
	
	final static long  MILLISECONDPERDAY = 1000*60*60*24;
	final static long  MILLISECONDPERMINUTE = 1000*60;
	
	/** findet alle Events, dessen Erinnerungszeitpunkt eingetroffen ist 
	 * 
	 * 
	 * @return Liste mit allen Events dessen Erinnerungszeitpunkt jetzt ist
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
	public List<Event> getReminders(){
		List<Event> reminder = new ArrayList<Event>();
		//Test ob Events vorhanden sind
		if(eventS.count() != 0) {
			
			List<Event> all = eventS.getAllEvents();
			// geht durch alle Events durch
			all.forEach(e -> {
				List<Date> remind = e.getReminderDates();
				// geht durch die ReminderDates der Events durch
				remind.forEach(d -> {
					//test ob:  vor fast einer Minute < Erinnerungszeitpunkt <= jetztige Zeit
					if(d.getTime() <= System.currentTimeMillis() && d.getTime() > System.currentTimeMillis() - 59999) {
						reminder.add(e);
						}
								});  
							});
		}
		return reminder;
	}
	
	

	/** Erstellt eine Nachricht in Form eines Strings, an welchem Wochentag das Event liegt
	 *  und wie lange es noch dauert bis das event eintritt.
	 *  Form: (Wochentag) in einem/x Tag/en, einer/x Stunde/n und einer/x Minute/n
	 *  (falls das Event in weniger als einem Tag oder einer Stunde eintritt, wird der Teil im
	 *  Text entfernt)
	 * @param event = event zu dem der NotificationString erstellt wird
	 * @return String mit Zeitangabe bis zum Event
	 */
	public static String getToStringForNotification(Event event) {
		String result = null;
		//
		long millisecondsofDate = event.getDate().getTime();
		
		result = EventReminder.getWeekdayofDate(millisecondsofDate);
		result+= " in ";
		
		String sMin= "";
		String sHours = "";
		String sDays = "";
		Integer min = EventReminder.getHowManyMinutes(millisecondsofDate);
		Integer hours = 0;
		Integer days = 0;
		
		//Zeit in Tagen, Stunden und Minuten bestimmen
		if(min < 60) 
			hours = min / 60;
			if(hours < 24) 
				days = hours / 24;
				
			
			//Alles Muss ja grammatikalisch korrekt sein
		if(min == 1)
			sMin = " und einer Minute.";
		
		if (min > 1)
			sMin =" und " + min.toString() + "Minuten.";
		
		if(days == 1)
			sDays = "einem Tag, ";
		
		if (days > 1)
			sDays = days.toString() + " Tagen, ";
		
		if (hours == 1)
			sHours = "einer Stunde";
		
		if (hours > 1)
			sHours = hours.toString() +" Stunden";
		
		return result + sHours + sDays + sMin; 	 
		
	}
	
	
	/** Gibt an in wievielen Minuten der eingegebene Zeitpunkt(In Millsekunden) eintritt
	 * @param TimeInMillisecond = gegebener Zeitpunkt
	 * @return Minuten bis dahin in int
	 */
	public static int getHowManyMinutes(long TimeInMillisecond) {
		long dateMin = TimeInMillisecond/MILLISECONDPERMINUTE;
		long currentMin = System.currentTimeMillis()/MILLISECONDPERMINUTE;
		return (int) (dateMin - currentMin);
		
	}
	
	/** Bestimmt zu einem Datum den Wochentag
	 * Wurde nicht mit Date implementiert um redundate Funktionsaufrufe zu vermeiden
	 * @param Long TimeInMillsecond= date.getTime des Datums
	 * @return Wochentag als String
	 */
	public static String getWeekdayofDate(Long TimeInMillisecond) {
		String result = null;
		//Wieviele Tage seit dem 1. Januar 1970
		long longdateDay = TimeInMillisecond/MILLISECONDPERDAY;
		int dateDay=(int)longdateDay;
		//modulo 7 zur Bestimmung welcher Wochentag 
		int dayAsInt =dateDay % 7;
		switch(dayAsInt) {
		case 0:
			// 1. Januar 1970 war ein Donnerstag
			result = "Donnerstag";
		case 1:
			result = "Freitag";
		case 2:
			result = "Samstag";
		case 3:
			result = "Sonntag";
		case 4:
			result = "Montag";
		case 5:
			result = "Dienstag";
		case 6:
			result = "Mittwoch";
		
		}
		return result;
		}
		
	
		}
	

