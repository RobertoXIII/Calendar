package view;

import controller.CalendarController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import model.CalendarDataProvider;
import model.NewCalendar;

public class SmallMonthView
  extends JPanel
{
  private static final long serialVersionUID = 1L;
  private JButton previousButton;
  private JButton nextButton;
  private GregorianCalendar selectedDay;
  private JPanel monthTable;
  private JLabel monthLabel;
  private CalendarController controller;
  
  public SmallMonthView(GregorianCalendar selected, CalendarController c)
  {
    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(280, 200));
    
    this.controller = c;
    this.selectedDay = selected;
    
    int month = CalendarDataProvider.getMonth(this.selectedDay);
    int year = CalendarDataProvider.getYear(this.selectedDay);
    int firstDayOfMonth = CalendarDataProvider.getFirstDayOfMonth(this.selectedDay);
    String monthName = CalendarDataProvider.getMonthName(this.selectedDay);
    
    this.monthTable = getMonthPanel(year, month, firstDayOfMonth);
    this.monthLabel = new JLabel(monthName + ", " + year);
    
    JPanel topPanel = new JPanel(new BorderLayout());
    topPanel.setPreferredSize(new Dimension(280, 50));
    topPanel.add(this.monthLabel, "North");
    topPanel.add(getButtonPanel(), "Center");
    
    add(topPanel, "North");
    add(this.monthTable, "Center");
  }
  
  private JPanel getButtonPanel()
  {
    this.previousButton = new JButton("<");
    this.nextButton = new JButton(">");
    
    this.previousButton.setActionCommand("previousMonth");
    this.nextButton.setActionCommand("nextMonth");
    
    this.previousButton.addActionListener(this.controller);
    this.nextButton.addActionListener(this.controller);
    
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(this.previousButton);
    buttonPanel.add(this.nextButton);
    return buttonPanel;
  }
  
  private JPanel getMonthPanel(int year, int month, int firstDay)
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
    final JTable monthTable = new JTable(monthArray, CalendarDataProvider.DAYS_OF_WEEK);
    monthTable.setPreferredScrollableViewportSize(new Dimension(270, 130));
    monthTable.setFillsViewportHeight(true);
    monthTable.setRowHeight(18);
    
    JPanel tablePanel = new JPanel();
    tablePanel.add(new JScrollPane(monthTable));
    
    monthTable.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent e)
      {
        int row = monthTable.rowAtPoint(e.getPoint());
        int col = monthTable.columnAtPoint(e.getPoint());
        
        int month = CalendarDataProvider.getMonth(SmallMonthView.this.selectedDay);
        int year = CalendarDataProvider.getYear(SmallMonthView.this.selectedDay);
        int dayOfMonth = row * 7 + col + 1;
        GregorianCalendar newDate = new GregorianCalendar(year, month, dayOfMonth);
        SmallMonthView.this.controller.cal.setSelectedDay(newDate);
      }
    });
    monthTable.setRowSelectionAllowed(false);
    
    return tablePanel;
  }
}
