package de.htwsaar.pib.zms.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.htwsaar.pib.zms.server.model.Event;
import de.htwsaar.pib.zms.server.model.Notification;
import de.htwsaar.pib.zms.server.model.Notificationtype;
import de.htwsaar.pib.zms.server.model.User;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

	
//public List<Notification> addByNotifiTitle(String title);
public Notification findById(long id);


}
