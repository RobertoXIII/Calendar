package model;

import java.util.GregorianCalendar;

public class CalendarDataProvider
{
  public static final int MAX_WEEKS_IN_MONTH = 6;
  public static final int DAYS_IN_WEEK = 7;
  public static final String[] DAYS_OF_WEEK_LONG_NAMES = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
  public static final String[] DAYS_OF_WEEK = { "Sun", "Mon", "Tues", "Wed", "Thurs", "Fri", "Sat" };
  public static final String[] MONTH_NAMES = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
  public static final String[] MONTH_SHORT_NAMES = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
  
  public static int getYear(GregorianCalendar day)
  {
    return day.get(1);
  }
  
  public static int getMonth(GregorianCalendar day)
  {
    return day.get(2);
  }
  
  public static String getMonthName(GregorianCalendar day)
  {
    return MONTH_NAMES[getMonth(day)];
  }
  
  public static String getMonthShortName(GregorianCalendar day)
  {
    return MONTH_SHORT_NAMES[getMonth(day)];
  }
  
  public static int getDateInMonth(GregorianCalendar day)
  {
    return day.get(5);
  }
  
  public static int getWeekInYear(GregorianCalendar day)
  {
    return day.get(3);
  }
  
  public static int getWeekDay(GregorianCalendar day)
  {
    return day.get(7);
  }
  
  public static String getWeekDayName(GregorianCalendar day)
  {
    return DAYS_OF_WEEK[(getWeekDay(day) - 1)];
  }
  
  public static String getWeekDayLongName(GregorianCalendar day)
  {
    return DAYS_OF_WEEK_LONG_NAMES[(getWeekDay(day) - 1)];
  }
  
  public static int getFirstDayOfMonth(GregorianCalendar day)
  {
    GregorianCalendar firstDay = new GregorianCalendar(getYear(day), getMonth(day), 1);
    return firstDay.get(7);
  }
  
  public static int getDaysInMonth(GregorianCalendar day)
  {
    int[] DAYS_IN_MONTH = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    if (day.isLeapYear(getYear(day))) {
      DAYS_IN_MONTH[1] = 29;
    }
    return DAYS_IN_MONTH[getMonth(day)];
  }
  
  public static int getDaysInMonth(int month, int year)
  {
    GregorianCalendar day = new GregorianCalendar(year, month, 1);
    return getDaysInMonth(day);
  }
  
  public static GregorianCalendar[] getCurrentWeek(GregorianCalendar day)
  {
    GregorianCalendar currentDay = (GregorianCalendar)day.clone();
    GregorianCalendar[] weekArray = new GregorianCalendar[7];
    int numberOfDaysAfterSunday = getWeekDay(day) * -1;
    currentDay.add(5, numberOfDaysAfterSunday + 1);
    for (int dayCount = 0; dayCount < 7; dayCount++)
    {
      weekArray[dayCount] = ((GregorianCalendar)currentDay.clone());
      currentDay.add(5, 1);
    }
    return weekArray;
  }
}
