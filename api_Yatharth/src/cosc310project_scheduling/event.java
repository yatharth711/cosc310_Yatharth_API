package cosc310project_scheduling;

import java.util.Date;

public class event{
	private String ueid, name, des, location, sk;
	private Date dueDate; //Date it is happening or when it's due
	private boolean fixed, repeat;
	
	public event(String ueid, String name, String des, Date dueDate, String location, String sk, boolean fixed) {
		this.setUeid(ueid);
		this.setName(name); 
		this.setDes(des);
		this.setLocation(location);
		this.setSk(sk);
		this.setDueDate(dueDate); 
	}

	public event() {
		this.setUeid("00000");
		this.setName("John Doe"); 
		this.setDes("Sample Description");
		this.setLocation("Sample Location");
		this.setSk(sk);
		this.setDueDate(dueDate);
	}

	public int getDaysUntilDue() {
		 return (int) (((dueDate.getTime()) - (new Date().getTime()))/1000) / 3600;	
	}

	public String getUeid() {
		return ueid;
	}

	public void setUeid(String ueid) {
		this.ueid = ueid;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSk() {
		return sk;
	}

	public void setSk(String sk) {
		this.sk = sk;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public boolean isFixed() {
		return fixed;
	}

	public void setFixed(boolean fixed) {
		this.fixed = fixed;
	}

	public boolean isRepeat() {
		return repeat;
	}

	public void setRepeat(boolean repeat) {
		this.repeat = repeat;
	}
	
}
