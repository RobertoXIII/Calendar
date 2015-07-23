package view;

import controller.CalendarController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import model.CalendarDataProvider;

public class DayView
  extends JPanel
{
  private static final long serialVersionUID = 1L;
  private JButton previousButton;
  private JButton todayButton;
  private JButton nextButton;
  private GregorianCalendar selectedDay;
  private JPanel dayTable;
  private CalendarController controller;
  private JLabel dayLabel;
  
  public DayView(GregorianCalendar selected, CalendarController c)
  {
    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(550, 400));
    setBackground(new Color(240, 244, 248));
    
    this.controller = c;
    this.selectedDay = selected;
    
    String monthShortName = CalendarDataProvider.getMonthShortName(this.selectedDay);
    String dayOfWeek = CalendarDataProvider.getWeekDayLongName(this.selectedDay);
    int year = CalendarDataProvider.getYear(this.selectedDay);
    int date = CalendarDataProvider.getDateInMonth(this.selectedDay);
    
    this.dayTable = getDayPanel(dayOfWeek);
    this.dayLabel = new JLabel(monthShortName + " " + date + ", " + year);
    
    JPanel topPanel = new JPanel(new BorderLayout());
    topPanel.setPreferredSize(new Dimension(530, 30));
    topPanel.add(this.dayLabel, "West");
    topPanel.add(getButtonPanel(), "East");
    topPanel.setBackground(new Color(240, 244, 248));
    
    add(topPanel, "East");
    add(this.dayTable, "South");
  }
  
  public JPanel getButtonPanel()
  {
    this.previousButton = new JButton("Previous");
    this.todayButton = new JButton("Today");
    this.nextButton = new JButton("Next");
    
    this.previousButton.setActionCommand("previousDay");
    this.todayButton.setActionCommand("today");
    this.nextButton.setActionCommand("nextDay");
    
    this.previousButton.addActionListener(this.controller);
    this.todayButton.addActionListener(this.controller);
    this.nextButton.addActionListener(this.controller);
    
    JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 5, 5));
    buttonPanel.add(this.previousButton);
    buttonPanel.add(this.todayButton);
    buttonPanel.add(this.nextButton);
    buttonPanel.setBackground(new Color(240, 244, 248));
    buttonPanel.setPreferredSize(new Dimension(350, 20));
    return buttonPanel;
  }
  
  public JPanel getDayPanel(String dayOfWeek)
  {
    String[][] dayArray = new String[2][1];
    dayArray[0][0] = "No meetings.";
    dayArray[1][0] = "Hello.";
    String[] todayString = { dayOfWeek };
    
    JTable dayTable = new JTable(dayArray, todayString);
    dayTable.setPreferredScrollableViewportSize(new Dimension(500, 325));
    dayTable.setFillsViewportHeight(true);
    dayTable.setRowHeight(100);
    dayTable.setShowGrid(true);
    dayTable.setGridColor(Color.LIGHT_GRAY);
    
    JPanel tablePanel = new JPanel();
    tablePanel.add(new JScrollPane(dayTable));
    tablePanel.setBackground(new Color(240, 244, 248));
    return tablePanel;
  }
}
