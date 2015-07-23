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

public class MonthView
  extends JPanel
{
  private static final long serialVersionUID = 1L;
  private JButton previousButton;
  private JButton todayButton;
  private JButton nextButton;
  private GregorianCalendar selectedDay;
  private JPanel monthTable;
  private JLabel monthLabel;
  private CalendarController controller;
  
  public MonthView(GregorianCalendar selected, CalendarController c)
  {
    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(550, 400));
    setBackground(new Color(240, 244, 248));
    
    this.controller = c;
    this.selectedDay = selected;
    
    int month = CalendarDataProvider.getMonth(this.selectedDay);
    int year = CalendarDataProvider.getYear(this.selectedDay);
    int firstDayOfMonth = CalendarDataProvider.getFirstDayOfMonth(this.selectedDay);
    String monthName = CalendarDataProvider.getMonthName(this.selectedDay);
    
    this.monthTable = getMonthPanel(year, month, firstDayOfMonth);
    this.monthLabel = new JLabel(monthName + ", " + year);
    
    JPanel topPanel = new JPanel(new BorderLayout());
    topPanel.setPreferredSize(new Dimension(530, 30));
    topPanel.add(this.monthLabel, "West");
    topPanel.add(getButtonPanel(), "East");
    topPanel.setBackground(new Color(240, 244, 248));
    
    add(topPanel, "East");
    add(this.monthTable, "South");
  }
  
  public JPanel getButtonPanel()
  {
    this.previousButton = new JButton("Previous");
    this.todayButton = new JButton("Today");
    this.nextButton = new JButton("Next");
    
    this.previousButton.setBorderPainted(false);
    this.todayButton.setBorderPainted(false);
    this.nextButton.setBorderPainted(false);
    
    this.previousButton.setActionCommand("previousMonth");
    this.todayButton.setActionCommand("today");
    this.nextButton.setActionCommand("nextMonth");
    
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
  
  public JPanel getMonthPanel(int year, int month, int firstDay)
  {
    Integer[][] monthArray = new Integer[6][7];
    int startDayOfMonth = firstDay - 1;
    int dayIndex = startDayOfMonth;
    int endDateOfMonth = CalendarDataProvider.getDaysInMonth(month, year);
    int weekIndex = 0;
    for (int dayCounter = 1; dayCounter <= endDateOfMonth; dayCounter++)
    {
      monthArray[weekIndex][dayIndex] = Integer.valueOf(dayCounter);
      dayIndex++;
      if (dayIndex > 6)
      {
        dayIndex = 0;
        weekIndex++;
      }
    }
    JTable monthTable = new JTable(monthArray, CalendarDataProvider.DAYS_OF_WEEK);
    monthTable.setPreferredScrollableViewportSize(new Dimension(500, 325));
    monthTable.setFillsViewportHeight(true);
    monthTable.setRowHeight(54);
    monthTable.setShowGrid(true);
    monthTable.setGridColor(Color.LIGHT_GRAY);
    
    JPanel tablePanel = new JPanel();
    tablePanel.add(new JScrollPane(monthTable));
    tablePanel.setBackground(new Color(240, 244, 248));
    return tablePanel;
  }
}
