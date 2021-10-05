# PIB-VS_WS2020_Gruppe6

# ZEITMANAGEMENTSYSTEM - ZMS

Bei diesem Projekt handelt es sich um ein verteiltes Kalendersystem für Unternehmen und Arbeitsgruppen.
Es ermöglicht den Mitarbeitern ihre individuellen Termine zu koordinieren als auch Meetings, Gruppentermine und 
andere Veranstaltungen zu planen.
Andere Benutzer können zu Terminen eingeladen werden.

## Architektur
Dieses Projekt wurde in zwei Maven Projekten (Client, Server) mit gemeinsamen Parent-Projekt erstellt.
Hierbei handelt es sich um eine 3-Schichten-Architektur. Die Trennung zwischen Client und Server erfolgt in der
Serviceschicht. 
Es wird Springboot (Rest API) als Middleware verwendet,
JavaFx wurde für die grafische Oberfläche und MySQL als DB-Umgebung.
Die Anbindung zwischen MySQL und dem Server erfolgt durch Hibernate.

#### Use Cases / User Stories

Zentrale Eigenschaften:

- Gruppentermine / Einladungssystem / Terminverwaltung 

- Erinnerungssystem mit Prioritäten 

- Wiederholende Termine (zb. daily, weekly, etc.) 

- Verschiedene User-Rechte

User Operationen:

- Termin erstellen

- Termin abfragen

- Termin löschen

- andere Nutzer Einladen

- vor Termin erinnert werden

- kann eine Liste von Teilnehmern und Nichtteilnehmern sehen

Hauptaufgaben vom Administrator sind folgende:
- alle Nutzer verwalten, dh
	Benutzer hinzufügen/bearbeiten/löschen
- alle normalen User Operationen

![us](https://i.ibb.co/H26GLsn/Use-Case-Diagram1.jpg)


#### Lösungsstrategie
Um unsere Zeit effizient zu nutzen, verwenden wir die REST-API und Verbindung mit SpringBoot als Middleware.
Eine genormte Schnittstelle ermöglicht Skalierbarkeit und den leichten Austausch einzelner Elemente.
Anders als "Spring", ist "SpringBoot" schneller zu konfigurieren und bietet bereits viele Grundfunktionen bei Initialisierung.
Teil des Spring-Ökosystems ist SpringSecurity, welches für die Authentifizierung und Nutzer-Rollen verwendet wurde.
In Verbindung mit Client-Server Kommunikation nutzen wir ebenfalls zwecks Sicherheit "Jackson" um die Objekte ins JSON-Format umzuwandeln.
Mithilfe von Hibernate-Annotationen können wir die Datenbanktabelle initialisieren und verwenden.
Dependencies werden einfach durch Maven verwaltet.
Lombok wird verwendet um redundanten Code einzusparen.



###### Bausteinsicht
 Es werden folgenden Bausteinsicht verwendet:
 1. Java
 2. JavaFX
 3. Hibernate
 4. MySQL
 5. Springboot
 6. Spring Security
 7. Maven
 8. Lombok
 9. GitHub
10. Jackson


###### Verteilungssicht
Die Verteilungssicht stellt dar, auf welchen physischen Rechnern die einzelnen Komponenten Ihres Systems ausgeführt werden und wie diese Verbunden sind.

###### Klassendiagramme
* [Klassendiagramm](https://i.ibb.co/k4zXBGv/Klassendiagramm.png)

###### API
**Get All Users**
----
  Returns json data about all users.

* **URL**

  /users/

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**
 
   None 

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{ 
 	List of Users 
	 }`
 

* **Sample Call:**

  ```javascript
    $.ajax({
    url: "/users",
    dataType: "json",
    type : "GET",
    success : function(r) {
      console.log(r);
    }
  });
  ```
  
  
  **Get User**
----
  Returns json data about searched users.

* **URL**

  /user/:id

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**
 
  `username=[String]`


* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{ 
 	{
    "id": 1,
    "username": "root",
    "firstName": "g",
    "secondName": "root",
    "email": "user1@gmail.com",
    "role": "ROLE_SUPERUSER",
    "enabled": true,
    "userId": 0
}
	 }`
 

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "/user/root",
      dataType: "json",
      type : "GET",
      success : function(r) {
        console.log(r);
    }
  });
  ```
  
  **Delete User**
----
  Deletes a choosen User

* **URL**

  /user/:username

* **Method:**

  `DELETE`
  
*  **URL Params**

   **Required:**
 
  `username=[String]`


* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{ 
 	None

	 }`

 * **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** `{ {
    "timestamp": "2021-03-22T18:56:01.495+00:00",
    "status": 500,
    "error": "Internal Server Error",
    "message": "",
    "path": "/user/user3"
}
 }`


* **Sample Call:**

  ```javascript
    $.ajax({
      url: "/user/user2",
      dataType: "json",
      type : "DELETE",
      success : function(r) {
        console.log(r);
    }
  });
  ```
  
  **Get Events**
----
  Returns json data about all events

* **URL**

  /event

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**
 
  None


* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{ 
 	None

	 }`



* **Sample Call:**

  ```javascript
    $.ajax({
      url: "/event",
      dataType: "json",
      type : "GET",
      success : function(r) {
        console.log(r);
    }
  });
  ```
  **Get  createdEvents**
----
  Returns json data about all createdEvents

* **URL**

  /user/:username/event/createdEvents

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**
 
    `username=[String]`


* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{ 
 	[
    {
        "id": 8,
        "title": "Event view 1",
        "note": "No note",
        "date": "2021-03-27T10:45:00.000+00:00",
        "duration": 60,
        "repetition": "NEVER",
        "eventCreator": {
            "id": 6,
            "username": "root",
            "firstName": "g",
            "secondName": "root",
            "email": "user1@gmail.com",
            "role": "ROLE_SUPERUSER",
            "enabled": true,
            "userId": 0
        },
        "invitedUsers": [],
        "participants": [],
        "reminderDates": []
    },
    {
        "id": 9,
        "title": "Event view 2",
        "note": "No note 2",
        "date": "2021-03-23T11:55:00.000+00:00",
        "duration": 60,
        "repetition": "NEVER",
        "eventCreator": {
            "id": 6,
            "username": "root",
            "firstName": "g",
            "secondName": "root",
            "email": "user1@gmail.com",
            "role": "ROLE_SUPERUSER",
            "enabled": true,
            "userId": 0
        }

	 }`
* **Error Response:**

  * **Code:** 500 Internal Server Error <br />
    **Content:** `{ {
    "timestamp": "2021-03-22T19:36:14.993+00:00",
    "status": 500,
    "error": "Internal Server Error",
    "message": "",
    "path": "/user/rootd/event/createdEvents"
} }`

* **Sample Call:**

  ```javascript
    $.ajax({
      url: " /user/{username}/event/createdEvents",
      dataType: "json",
      type : "GET",
      success : function(r) {
        console.log(r);
    }
  });
  ```
  
  
  Deletes choosen event

* **URL**

  /user/event/:id

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**
 
    `id=[long]`


* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 OK<br />
    **Content:** `{ {
    "id": 8,
    "title": "Event view 1",
    "note": "No note",
    "date": "2021-03-27T10:45:00.000+00:00",
    "duration": 60,
    "repetition": "NEVER",
    "eventCreator": {
        "id": 6,
        "username": "root",
        "firstName": "g",
        "secondName": "root",
        "email": "user1@gmail.com",
        "role": "ROLE_SUPERUSER",
        "enabled": true,
        "userId": 0
    },
    "invitedUsers": [],
    "participants": [],
    "reminderDates": []
}

	 }`
* **Error Response:**

  * **Code:** 400 Bad Request <br />
    **Content:** `{ {
    "timestamp": "2021-03-22T19:45:01.193+00:00",
    "status": 400,
    "error": "Bad Request",
    "message": "",
    "path": "/event/8ds"
}}`

* **Sample Call:**

  ```javascript
    $.ajax({
      url: " /user/event/{id}",
      dataType: "json",
      type : "DELETE",
      success : function(r) {
        console.log(r);
    }
  });
  ```
 
  
  
  **Get  one Event**
----
  Returns json data about choosen event

* **URL**

  /user/event/:id

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**
 
    `id=[long]`


* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 OK<br />
    **Content:** `{ {
    "id": 8,
    "title": "Event view 1",
    "note": "No note",
    "date": "2021-03-27T10:45:00.000+00:00",
    "duration": 60,
    "repetition": "NEVER",
    "eventCreator": {
        "id": 6,
        "username": "root",
        "firstName": "g",
        "secondName": "root",
        "email": "user1@gmail.com",
        "role": "ROLE_SUPERUSER",
        "enabled": true,
        "userId": 0
    },
    "invitedUsers": [],
    "participants": [],
    "reminderDates": []
}

	 }`
* **Error Response:**

  * **Code:** 400 Bad Request <br />
    **Content:** `{ {
    "timestamp": "2021-03-22T19:41:24.911+00:00",
    "status": 400,
    "error": "Bad Request",
    "message": "",
    "path": "/event/8dssd%22e2wds"
}}`

* **Sample Call:**

  ```javascript
    $.ajax({
      url: " /user/event/{id}",
      dataType: "json",
      type : "GET",
      success : function(r) {
        console.log(r);
    }
  });
  ```  
  
  **Get  UpcomingEvents**
----
  Returns json data about all upcomingevents

* **URL**

  /user/:username/event/upcomingEvents

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**
 
	 `username=[String]`


* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{ 
 	[]

	 }`
* **Error Response:**

  * **Code:** 500 Internal Server Error <br />
    **Content:** `{ {
    "timestamp": "2021-03-22T19:24:55.532+00:00",
    "status": 500,
    "error": "Internal Server Error",
    "message": "",
    "path": "/user/root2/event/upcomingEvents"
} }`

* **Sample Call:**

  ```javascript
    $.ajax({
      url: " /user/{username}/event/upcomingEvents",
      dataType: "json",
      type : "GET",
      success : function(r) {
        console.log(r);
    }
  });
  ```
  **Get Notification**
----
Returns json data about a Notification.

* **URL**

  /notification/:id

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**
 
    `id=[long]`
    


* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 OK<br />
    **Content:** `{ {
    "id": 14,
    "title": "Einladung zu Termin",
    "description": "roothat dich zum Termin schnitzewel eingeladen",
    "event": {
        "id": 13,
        "title": "schnitzewel",
        "note": "",
        "date": "2021-03-27T17:13:00.000+00:00",
        "duration": 50,
        "repetition": "NEVER",
        "eventCreator": {
            "id": 6,
            "username": "root",
            "firstName": "g",
            "secondName": "root",
            "email": "user1@gmail.com",
            "role": "ROLE_SUPERUSER",
            "enabled": true,
            "userId": 0
        },
        "invitedUsers": [
            {
                "id": 7,
                "username": "user2",
                "firstName": "user2",
                "secondName": "user2",
                "email": "user2@gmail.com",
                "role": "ROLE_USER",
                "enabled": true,
                "userId": 0
            }
        ],
        "participants": [],
        "reminderDates": []
    },
    "notificationtype": "invitation",
    "receivers": [
        {
            "id": 7,
            "username": "user2",
            "firstName": "user2",
            "secondName": "user2",
            "email": "user2@gmail.com",
            "role": "ROLE_USER",
            "enabled": true,
            "userId": 0
        }
    ],
    "sendDate": "2021-03-22T20:13:41.000+00:00",
    "seen": false,
    "sender": {
        "id": 6,
        "username": "root",
        "firstName": "g",
        "secondName": "root",
        "email": "user1@gmail.com",
        "role": "ROLE_SUPERUSER",
        "enabled": true,
        "userId": 0
    }
}
	 }`


* **Sample Call:**

  ```javascript
    $.ajax({
      url: " /notification/{notificationid}",
      dataType: "json",
      type : "GET",
      success : function(r) {
        console.log(r);
    }
  });
  ```
  
  **Get LatestNotification**
----
 Get the latest Notification

* **URL**

  /notification/:userid/:count

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**
 
    `userid=[long]`
    `count=[int]`


* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 OK<br />
    **Content:** `{ [
    {
        "id": 14,
        "title": "Einladung zu Termin",
        "description": "roothat dich zum Termin schnitzewel eingeladen",
        "event": {
            "id": 13,
            "title": "schnitzewel",
            "note": "",
            "date": "2021-03-27T17:13:00.000+00:00",
            "duration": 50,
            "repetition": "NEVER",
            "eventCreator": {
                "id": 6,
                "username": "root",
                "firstName": "g",
                "secondName": "root",
                "email": "user1@gmail.com",
                "role": "ROLE_SUPERUSER",
                "enabled": true,
                "userId": 0
            },
            "invitedUsers": [
                {
                    "id": 7,
                    "username": "user2",
                    "firstName": "user2",
                    "secondName": "user2",
                    "email": "user2@gmail.com",
                    "role": "ROLE_USER",
                    "enabled": true,
                    "userId": 0
                }
            ],
            "participants": [],
            "reminderDates": []
        },
        "notificationtype": "invitation",
        "receivers": [
            {
                "id": 7,
                "username": "user2",
                "firstName": "user2",
                "secondName": "user2",
                "email": "user2@gmail.com",
                "role": "ROLE_USER",
                "enabled": true,
                "userId": 0
            }
        ],
        "sendDate": "2021-03-22T20:13:41.000+00:00",
        "seen": false,
        "sender": {
            "id": 6,
            "username": "root",
            "firstName": "g",
            "secondName": "root",
            "email": "user1@gmail.com",
            "role": "ROLE_SUPERUSER",
            "enabled": true,
            "userId": 0
        }
    }
]

	 }`
* **Error Response:**

  * **Code:** 500 Internal Server Error <br />
    **Content:** `{ {
    "timestamp": "2021-03-22T20:26:55.364+00:00",
    "status": 500,
    "error": "Internal Server Error",
    "message": "",
    "path": "/notification/9/3"
}}`

* **Sample Call:**

  ```javascript
    $.ajax({
      url: " /notification/{userId}/{count}",
      dataType: "json",
      type : "GET",
      success : function(r) {
        console.log(r);
    }
  });
  ```

#### Dynamisches Modell
![dyn](https://i.ibb.co/hfX9Gvk/Bildschirmfoto-2021-03-22-um-18-18-41.png)

## Getting Started
Damit das Programm ausgeführt werden kann, muss das Server-Projekt zum Build Path des Client Projekts hinzugefügt werden. Außerdem muss in der Application.Properties (Server/src/main/resources) das entsprechende Passwort für die MySQL Datenbank eingetragen werden.

#### Vorraussetzungen
Java 8
JavaFX 
MySQL 8
Maven 3.6.3
Lombok (in IDE installieren!)


#### Installation und Deployment

Nachdem Application.Properties mit denen der eigenen DB übereinstimmen, einfach /Server/src/main/java/de/htwsaar/pib/zms/server/Start.java ausführen.
Anschließend wenn die DB durch Hibernate initialisiert wurde, den Client /Client/src/main/java/de/htwsaar/pib/vs/zms/client/CalendarClientStarter.java ausführen.


## Built With


* [Maven](https://maven.apache.org/) - Dependency Management
* [Spring Boot Initializr](https://start.spring.io) - Build SpringBoot Application

## License

This project is licensed under the GNU General Public License v3.0

## Acknowledgments

