package model;

import java.util.ArrayList;

public class RecurringEvent
{
  private String title;
  private int year;
  private int startingMonth;
  private int endingMonth;
  private String days;
  private String startingTime;
  private String endingTime;
  private ArrayList<SimpleEvent> eventList;
  
  public RecurringEvent(String title, int year, int startingMonth, int endingMonth, String days, String startingTime, String endingTime)
  {
    this.title = title;
    this.year = year;
    this.startingMonth = startingMonth;
    this.endingMonth = endingMonth;
    this.days = days;
    this.startingTime = startingTime;
    this.endingTime = endingTime;
    this.eventList = new ArrayList();
  }
  
  public ArrayList<SimpleEvent> getEventList()
  {
    return this.eventList;
  }
  
  public String getStartingTime()
  {
    return this.startingTime;
  }
  
  public String getEndingTime()
  {
    return this.endingTime;
  }
  
  public String getTitle()
  {
    return this.title;
  }
  
  public int getStartingMonth()
  {
    return this.startingMonth;
  }
  
  public int getEndingMonth()
  {
    return this.endingMonth;
  }
  
  public int getYear()
  {
    return this.year;
  }
  
  public String getDays()
  {
    return this.days;
  }
}
