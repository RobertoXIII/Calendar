package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import model.NewCalendar;
import model.NewCalendar.ViewBy;

public class CalendarController
  implements ActionListener
{
  public NewCalendar cal;
  private GregorianCalendar selectedDay;
  private NewCalendar.ViewBy currentView;
  
  public CalendarController(NewCalendar c)
  {
    this.cal = c;
    this.selectedDay = this.cal.getSelectedDay();
    this.currentView = this.cal.getCurrentView();
  }
  
  public void actionPerformed(ActionEvent e)
  {
    try
    {
      String[] day = e.getActionCommand().split(" ");
      if (day[0].equals("addevent"))
      {
        int date = Integer.parseInt(day[2]);
        int month = Integer.parseInt(day[4]);
        int year = Integer.parseInt(day[3]);
        String monthName = day[1];
        String input = JOptionPane.showInputDialog("Date: " + monthName + " " + date + ", " + year + "\n\n Event Title:");
        
        this.selectedDay = new GregorianCalendar(year, month, date);
        this.cal.addEvent(this.selectedDay, input);
      }
      else if (e.getActionCommand().equals("DayView"))
      {
        this.currentView = NewCalendar.ViewBy.DAY;
      }
      else if (e.getActionCommand().equals("MonthView"))
      {
        this.currentView = NewCalendar.ViewBy.MONTH;
      }
      else if (e.getActionCommand().equals("WeekView"))
      {
        this.currentView = NewCalendar.ViewBy.WEEK;
      }
      else if ("previousMonth".equals(e.getActionCommand()))
      {
        this.selectedDay.add(2, -1);
      }
      else if ("nextMonth".equals(e.getActionCommand()))
      {
        this.selectedDay.add(2, 1);
      }
      else if ("previousDay".equals(e.getActionCommand()))
      {
        this.selectedDay.add(5, -1);
      }
      else if ("nextDay".equals(e.getActionCommand()))
      {
        this.selectedDay.add(5, 1);
      }
      else if ("previousWeek".equals(e.getActionCommand()))
      {
        this.selectedDay.add(3, -1);
      }
      else if ("nextWeek".equals(e.getActionCommand()))
      {
        this.selectedDay.add(3, 1);
      }
      else
      {
        this.selectedDay = new GregorianCalendar();
      }
      this.cal.setCurrentView(this.currentView);
      this.cal.setSelectedDay(this.selectedDay);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      JOptionPane.showMessageDialog(null, "Error", "Error", 0);
    }
  }
}
