package de.htwsaar.pib.zms.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

// Benötigt zur Bestimmung der Art der Notication
public enum Notificationtype {	
	invitation,
	rejection,
	acceptance,
	reminder;
	
	
}
