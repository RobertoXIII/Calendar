package model;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Observable;

public class NewCalendar
  extends Observable
{
  private GregorianCalendar selectedDay;
  private ViewBy currentView;
  private ViewBy previousView;
  private EventMap eventMap;
  
  public static enum ViewBy
  {
    DAY,  WEEK,  MONTH,  AGENDA;
    
    private ViewBy() {}
  }
  
  public NewCalendar()
  {
    this.selectedDay = new GregorianCalendar();
    this.eventMap = new EventMap();
    this.currentView = ViewBy.DAY;
    this.previousView = this.currentView;
  }
  
  public GregorianCalendar getSelectedDay()
  {
    return this.selectedDay;
  }
  
  public Map<GregorianCalendar, ArrayList<SimpleEvent>> getEventMap()
  {
    return this.eventMap.getEventMap();
  }
  
  public void setSelectedDay(GregorianCalendar day)
  {
    this.selectedDay = day;
    setChanged();
    notifyObservers();
    clearChanged();
  }
  
  public ViewBy getCurrentView()
  {
    return this.currentView;
  }
  
  public ViewBy getPreviousView()
  {
    return this.previousView;
  }
  
  public void setCurrentView(ViewBy view)
  {
    this.previousView = this.currentView;
    this.currentView = view;
  }
  
  public void addEvent(GregorianCalendar day, String name)
  {
    this.eventMap.addEvent(new SimpleEvent(day, name));
  }
}
