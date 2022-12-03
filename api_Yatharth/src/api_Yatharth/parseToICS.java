package api_Yatharth;

import java.awt.Component;
import java.rmi.server.UID;
import java.time.Duration;
import java.util.Properties;

import cosc310project_scheduling.event;
import jdk.jfr.Description;

public class parseToICS {
	public <DtStamp, DtStart, Summary> Calendar importCalendar(String calendarSerialized) throws Exception {
	    Calendar c = new CalendarBuilder().build(new StringReader(calendarSerialized));
	    c.validate();
	    event event = (event) c.getComponent(Component.event);
	    DtStamp dtStamp = event.getDateStamp();
	    UID uid = event.getUid();
	    DtStart startDate = event.getStartDate();
	    String tz = startDate.getTimeZone().getVTimeZone().getTimeZoneId().getValue();
	    Properties props = event.getProperties();
	    Duration d1 = props.getProperty(Property.DURATION);//Any property can be retrieved this way.
	    Duration d2 = event.getDuration();//d2 is the same as d1.
	    Summary summary = event.getSummary();
	    Description description = event.getDescription();
	    return c;
	}
}
