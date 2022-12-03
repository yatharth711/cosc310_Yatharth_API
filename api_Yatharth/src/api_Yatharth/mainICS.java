package api_Yatharth;

import java.util.Calendar;

import cosc310project_scheduling.event;

public class mainICS {
	Calendar c = new Calendar();
	event e = new event();
	//"EN" is the language.
	c.getProperties().add(new ProdId("-//weightedSchduler/export_as_ICS//EN"));
	c.getProperties().add(Version.VERSION_2_0);
	c.getProperties().add(CalScale.GREGORIAN);

	//Add an event to the calendar
	c.getComponents().add(event.add());
	String icsExport = c.toString();
	
}