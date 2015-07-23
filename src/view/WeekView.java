package view;

import controller.CalendarController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.CalendarDataProvider;

public class WeekView
  extends JPanel
{
  private static final long serialVersionUID = 1L;
  private JButton previousButton;
  private JButton todayButton;
  private JButton nextButton;
  private GregorianCalendar selectedDay;
  private JPanel weekTable;
  private JLabel weekLabel;
  private CalendarController controller;
  
  public WeekView(GregorianCalendar selected, CalendarController c)
  {
    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(550, 400));
    setBackground(new Color(240, 244, 248));
    
    this.controller = c;
    this.selectedDay = selected;
    
    int year = CalendarDataProvider.getYear(this.selectedDay);
    int weekNumber = CalendarDataProvider.getWeekInYear(this.selectedDay);
    int date = CalendarDataProvider.getDateInMonth(this.selectedDay);
    if ((weekNumber == 1) && (date > 7)) {
      year++;
    }
    this.weekTable = getWeekPanel();
    this.weekLabel = new JLabel("Week " + weekNumber + " of " + year);
    
    JPanel topPanel = new JPanel(new BorderLayout());
    topPanel.setPreferredSize(new Dimension(530, 30));
    topPanel.add(this.weekLabel, "West");
    topPanel.add(getButtonPanel(), "East");
    topPanel.setBackground(new Color(240, 244, 248));
    
    add(topPanel, "East");
    add(this.weekTable, "South");
  }
  
  public JPanel getButtonPanel()
  {
    this.previousButton = new JButton("Previous");
    this.todayButton = new JButton("Today");
    this.nextButton = new JButton("Next");
    
    this.previousButton.setBorderPainted(false);
    this.todayButton.setBorderPainted(false);
    this.nextButton.setBorderPainted(false);
    
    this.previousButton.setActionCommand("previousWeek");
    this.todayButton.setActionCommand("today");
    this.nextButton.setActionCommand("nextWeek");
    
    this.previousButton.addActionListener(this.controller);
    this.todayButton.addActionListener(this.controller);
    this.nextButton.addActionListener(this.controller);
    
    JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 5, 5));
    buttonPanel.add(this.previousButton);
    buttonPanel.add(this.todayButton);
    buttonPanel.add(this.nextButton);
    buttonPanel.setBackground(new Color(240, 244, 248));
    buttonPanel.setPreferredSize(new Dimension(350, 30));
    return buttonPanel;
  }
  
  public JPanel getWeekPanel()
  {
    GregorianCalendar[] weekArray = CalendarDataProvider.getCurrentWeek(this.selectedDay);
    
    JPanel daysPanel = new JPanel(new GridLayout(1, 7, -1, 0));
    JPanel tableTopPanel = new JPanel(new GridLayout(1, 1));
    JPanel tablePanel = new JPanel(new BorderLayout());
    
    daysPanel.setBackground(new Color(240, 244, 248));
    tableTopPanel.setBackground(new Color(240, 244, 248));
    tablePanel.setBackground(new Color(240, 244, 248));
    
    tableTopPanel.add(new JLabel(new ImageIcon("daysOfWeek.png")));
    
    String monthName = "";
    for (int dayCount = 0; dayCount < 7; dayCount++)
    {
      int year = CalendarDataProvider.getYear(weekArray[dayCount]);
      int month = CalendarDataProvider.getMonth(weekArray[dayCount]);
      int date = CalendarDataProvider.getDateInMonth(weekArray[dayCount]);
      monthName = CalendarDataProvider.getMonthShortName(weekArray[dayCount]);
      
      JButton currentDay = new JButton(monthName + " " + date);
      
      currentDay.setActionCommand("addevent " + monthName + " " + date + " " + year + " " + month);
      
      currentDay.addActionListener(this.controller);
      currentDay.setPreferredSize(new Dimension(72, 325));
      daysPanel.add(currentDay);
    }
    tablePanel.add(tableTopPanel, "North");
    tablePanel.add(daysPanel, "South");
    return tablePanel;
  }
}
