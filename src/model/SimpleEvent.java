package model;

import java.util.GregorianCalendar;

public class SimpleEvent
{
  private GregorianCalendar day;
  private String name;
  
  public SimpleEvent(GregorianCalendar d, String n)
  {
    this.day = d;
    this.name = n;
  }
  
  public GregorianCalendar getDay()
  {
    return this.day;
  }
  
  public String getName()
  {
    return this.name;
  }
}
