package model;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class EventMap
{
  private Map<GregorianCalendar, ArrayList<SimpleEvent>> eventMap;
  
  public EventMap()
  {
    this.eventMap = new HashMap();
  }
  
  public Map<GregorianCalendar, ArrayList<SimpleEvent>> getEventMap()
  {
    return this.eventMap;
  }
  
  public void addEvent(SimpleEvent event) {}
}
