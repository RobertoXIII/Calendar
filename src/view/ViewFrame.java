package view;

import controller.CalendarController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JPanel;
import model.NewCalendar;
import model.NewCalendar.ViewBy;
import model.SimpleEvent;

public class ViewFrame
  extends JPanel
  implements Observer
{
  private static final long serialVersionUID = 1L;
  private JButton createButton;
  private JButton dayButton;
  private JButton weekButton;
  private JButton monthButton;
  private JButton agendaButton;
  private JButton fromFileButton;
  private JPanel eastPanel;
  private JPanel buttonPanel;
  private DayView dayPanel;
  private MonthView monthPanel;
  private WeekView weekPanel;
  private SmallMonthView smallMonthPanel;
  private NewCalendar.ViewBy currentView;
  private NewCalendar.ViewBy previousView;
  private NewCalendar cal;
  private GregorianCalendar selectedDay;
  private CalendarController controller;
  
  public ViewFrame(NewCalendar c)
  {
    setLayout(new BorderLayout());
    
    this.cal = c;
    this.controller = new CalendarController(this.cal);
    this.selectedDay = this.cal.getSelectedDay();
    this.currentView = this.cal.getCurrentView();
    Map<GregorianCalendar, ArrayList<SimpleEvent>> eventMap = this.cal.getEventMap();
    
    this.dayPanel = new DayView(this.selectedDay, this.controller);
    this.buttonPanel = setUpButtonPanel();
    this.smallMonthPanel = new SmallMonthView(this.selectedDay, this.controller);
    this.eastPanel = new JPanel(new BorderLayout());
    
    this.buttonPanel.setPreferredSize(new Dimension(550, 30));
    this.eastPanel.setPreferredSize(new Dimension(550, 450));
    this.smallMonthPanel.setPreferredSize(new Dimension(280, 220));
    
    this.eastPanel.add(this.buttonPanel, "East");
    this.eastPanel.add(this.dayPanel, "South");
    add(this.eastPanel, "East");
    add(this.smallMonthPanel, "West");
    
    this.cal.addObserver(this);
  }
  
  public JPanel setUpButtonPanel()
  {
    this.createButton = new JButton("Create");
    this.dayButton = new JButton("Day");
    this.weekButton = new JButton("Week");
    this.monthButton = new JButton("Month");
    this.agendaButton = new JButton("Agenda");
    this.fromFileButton = new JButton("From File");
    
    this.createButton.setActionCommand("Create");
    this.dayButton.setActionCommand("DayView");
    this.weekButton.setActionCommand("WeekView");
    this.monthButton.setActionCommand("MonthView");
    this.agendaButton.setActionCommand("AgendaView");
    this.fromFileButton.setActionCommand("FromFile");
    
    this.createButton.addActionListener(this.controller);
    this.dayButton.addActionListener(this.controller);
    this.weekButton.addActionListener(this.controller);
    this.monthButton.addActionListener(this.controller);
    this.agendaButton.addActionListener(this.controller);
    this.fromFileButton.addActionListener(this.controller);
    
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(this.createButton);
    buttonPanel.add(this.dayButton);
    buttonPanel.add(this.weekButton);
    buttonPanel.add(this.monthButton);
    buttonPanel.add(this.agendaButton);
    buttonPanel.add(this.fromFileButton);
    return buttonPanel;
  }
  
  public void update(Observable calendar, Object msg)
  {
    if ((this.cal instanceof NewCalendar))
    {
      this.selectedDay = ((NewCalendar)calendar).getSelectedDay();
      this.currentView = ((NewCalendar)calendar).getCurrentView();
      this.previousView = ((NewCalendar)calendar).getPreviousView();
      removedPreviousPanel();
      if (this.currentView.equals(NewCalendar.ViewBy.DAY))
      {
        this.dayPanel = new DayView(this.selectedDay, this.controller);
        this.eastPanel.add(this.dayPanel, "South");
      }
      else if (this.currentView.equals(NewCalendar.ViewBy.MONTH))
      {
        this.monthPanel = new MonthView(this.selectedDay, this.controller);
        this.eastPanel.add(this.monthPanel, "South");
      }
      else if (this.currentView.equals(NewCalendar.ViewBy.WEEK))
      {
        this.weekPanel = new WeekView(this.selectedDay, this.controller);
        this.eastPanel.add(this.weekPanel, "South");
      }
      this.smallMonthPanel = new SmallMonthView(this.selectedDay, this.controller);
      add(this.smallMonthPanel, "West");
      validate();
    }
    else if (!this.currentView.equals(NewCalendar.ViewBy.AGENDA)) {}
  }
  
  public void removedPreviousPanel()
  {
    if (this.previousView.equals(NewCalendar.ViewBy.DAY)) {
      this.eastPanel.remove(this.dayPanel);
    } else if (this.previousView.equals(NewCalendar.ViewBy.MONTH)) {
      this.eastPanel.remove(this.monthPanel);
    } else if (this.previousView.equals(NewCalendar.ViewBy.WEEK)) {
      this.eastPanel.remove(this.weekPanel);
    }
    remove(this.smallMonthPanel);
  }
}
