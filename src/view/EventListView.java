package view;

import controller.CalendarController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import model.CalendarDataProvider;
import model.SimpleEvent;

public class EventListView
  extends JPanel
{
  private static final long serialVersionUID = 1L;
  JButton addEventButton;
  
  public EventListView(ArrayList<SimpleEvent> meetingList, CalendarController controller)
  {
    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(240, 450));
    setBackground(new Color(240, 244, 248));
    
    this.addEventButton = new JButton("ADD EVENT");
    
    add(getInstructionPanel(), "North");
    add(getMeetingListPanel(meetingList), "South");
  }
  
  public JPanel getInstructionPanel()
  {
    Font font = new Font("SansSerif", 2, 10);
    
    JLabel instruction = new JLabel("Click any day on the calendar to add meetings.");
    instruction.setFont(font);
    
    JPanel instructionPanel = new JPanel();
    instructionPanel.add(instruction);
    instructionPanel.setPreferredSize(new Dimension(240, 100));
    instructionPanel.setBackground(new Color(240, 244, 248));
    return instructionPanel;
  }
  
  public JPanel getMeetingListPanel(ArrayList<SimpleEvent> meetingList)
  {
    String eventString = "";
    String dateString = "";
    if (meetingList.size() > 0) {
      for (int i = 0; i < meetingList.size(); i++)
      {
        GregorianCalendar day = ((SimpleEvent)meetingList.get(i)).getDay();
        dateString = CalendarDataProvider.getDateInMonth(day) + " " + CalendarDataProvider.getMonthShortName(day) + " " + CalendarDataProvider.getYear(day);
        
        eventString = eventString + dateString + "\n" + ((SimpleEvent)meetingList.get(i)).getName() + "\n\n";
      }
    } else {
      eventString = "No Meeting Yet";
    }
    JTextArea eventLabel = new JTextArea(eventString);
    JPanel tablePanel = new JPanel();
    tablePanel.add(eventLabel);
    
    tablePanel.add(new JScrollPane(eventLabel));
    tablePanel.setPreferredSize(new Dimension(230, 325));
    tablePanel.setBackground(new Color(240, 244, 248));
    return tablePanel;
  }
}
