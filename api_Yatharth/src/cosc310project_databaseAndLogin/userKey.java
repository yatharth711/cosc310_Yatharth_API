package cosc310project_databaseAndLogin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cosc310project_scheduling.event;

public class userKey extends key{
	private String uuid; //DB identifier - uuid(Unique User ID)
	private ArrayList<event> events;
	private dbKey dbKey;
	
	public userKey(String user, String pw, dbKey dbKey){
		super(user,pw); //INIT super constructor
		//this.user = hash(user); pass = hash(pass);  //Hashing User name and password
		this.dbKey = dbKey;
		if (getUUID(dbKey)) {
			System.out.println("User Cred Validated!");
		}else System.out.println("User Cred Incorrect!");
		events = fillEvents(dbKey);	//Fill Events
	}
	
	public String getUUID() {	//Return user's UUID
		return uuid;
	}
	
	public ArrayList<event> getEvents(){
		return events;
	}
	
	public dbKey getDBkey() {
		return dbKey;
	}
	
	private boolean getUUID(dbKey k) { //Get UUID from DB
		this.uuid = new userRequest(dbKey).getUUID(this.getUser(), this.getPass());
		return (uuid == null)?false:true;
	}
	
	//REDO
	private ArrayList<event> fillEvents(dbKey k) { //Get and Fill Events
		return new userRequest(dbKey).getEvents(uuid);
	}
}

class userRequest extends request{		//userRequest class extends abstract class request
	
	final String UUIDSQL = "SELECT uuid FROM login WHERE user = ? AND pass = ?;";	//SQL statements
	final String EVENTSQL = "SELECT * FROM events WHERE uuid = ?;";
	
	public userRequest(dbKey key) {	
		super(key);
	}
	
	public String getUUID(String user, String pass) {	//Method to return UUID by executing SQL query
		try {
			connect();
			PreparedStatement pstmt = conn.prepareStatement(UUIDSQL);
			pstmt.setString(1, user);
			pstmt.setString(2, pass);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			String uuid = rs.getString(0);
			close();
			return uuid;
		}catch(Exception e) {
			System.err.println("FATAL ERROR(ur-g): "+e);
			return null;
		}
	}
	
	public ArrayList<event> getEvents(String uuid){	//Method to fetch and return events of a user
		ArrayList<event> userEvents = new ArrayList<event>();
		try {
			connect();
			PreparedStatement pstmt = conn.prepareStatement(EVENTSQL);
			pstmt.setString(1, uuid);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {		// Cycle through resultset and create an event object of each event in the resultset.
				userEvents.add(new event(rs.getString("ueid"), rs.getString("ename"), 
						rs.getString("des"), rs.getDate("dueDate"), 
						rs.getString("location"), rs.getString("sk"), rs.getBoolean("fixed")));
			}
			return userEvents;
		}catch(Exception e) {
			System.err.println("FATAL ERROR(ur-e): "+e);
			return null;
		}
	}
}
