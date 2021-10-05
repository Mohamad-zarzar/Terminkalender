package de.htwsaar.pib.zms.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import de.htwsaar.pib.zms.server.auth.PasswordGenerator;
import de.htwsaar.pib.zms.server.model.Event;
import de.htwsaar.pib.zms.server.model.Notification;
import de.htwsaar.pib.zms.server.model.Notificationtype;
import de.htwsaar.pib.zms.server.model.Repetitiontype;
import de.htwsaar.pib.zms.server.model.User;
import de.htwsaar.pib.zms.server.model.UserRole;
import de.htwsaar.pib.zms.server.repository.EventRepository;
import de.htwsaar.pib.zms.server.repository.NotificationRepository;
import de.htwsaar.pib.zms.server.repository.UserRepository;
import de.htwsaar.pib.zms.server.service.EventReminder;
import de.htwsaar.pib.zms.server.service.EventService;
import de.htwsaar.pib.zms.server.service.UserService;

//
//@ComponentScan(basePackages = {"de.htwsaar.pib.zms.server.repository"})
//	@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@SpringBootApplication()
public class Start {

	@Autowired
	UserService userService;
	@Autowired
	EventService eventService;
	@Autowired
	EventReminder reminder;

	public static void main(String[] args) {
		SpringApplication.run(Start.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		User user1FromDb = userService.getUserByUsername("root");
		if (user1FromDb == null) {
			User user = new User("g", "root", "root", UserRole.SUPERUSER, "user1@gmail.com");
			String pw1 = "rootpw";
			user.setEnabled(true);
			userService.addUser(user, pw1);
		}
		System.out.println("Initialization successed !");
		
		// test EventReminder
//		String title4 = "Event view 4";
//		String note4 = "No note 4";
//		int duration4 = 60;
//		Date date4 = createDate(2021,3,5,11,55);
//		Repetitiontype repetitiontype4 = Repetitiontype.NEVER;
//		Date remind3 = createDate(2021,3,21,19,50);
//		List<Date> remindL3 = new ArrayList<Date>();
//		remindL3.add(remind3);
//		
//		Event testEvent = new Event(title4, note4, date4, duration4,repetitiontype4, userService.getUserByUsername("user2"));
//		testEvent.setReminderDates(remindL3);
//		User userit = userService.getUserByUsername("user2");
//		testEvent.addParticipant(userit);
//		eventService.save(testEvent);
//		StartThreadForReminder start = new StartThreadForReminder(reminder);
//		start.run();
	}

	private Date createDate(int year, int month, int day, int hour, int min ) {
		Calendar calender = Calendar.getInstance();
		calender.set(Calendar.YEAR, year);
		calender.set(Calendar.MONTH, month);
		calender.set(Calendar.DAY_OF_MONTH, day);
		calender.set(Calendar.HOUR_OF_DAY, hour);
		calender.set(Calendar.MINUTE, min);
		calender.set(Calendar.SECOND, 0);
		calender.set(Calendar.MILLISECOND, 0);
		Date date = calender.getTime();

		return date;
	}

}