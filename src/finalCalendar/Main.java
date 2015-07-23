package finalCalendar;

import java.awt.Dimension;
import javax.swing.JFrame;
import model.NewCalendar;
import view.ViewFrame;

public class Main
{
	public static void main(String[] args)
	{
		NewCalendar cal = new NewCalendar();
	    JFrame calendarFrame = new JFrame();
	    calendarFrame.setDefaultCloseOperation(3);
	    calendarFrame.setTitle("Calendar");
	    calendarFrame.setResizable(false);
	    calendarFrame.setPreferredSize(new Dimension(850, 500));
	    calendarFrame.add(new ViewFrame(cal));
	    calendarFrame.pack();
	    calendarFrame.setVisible(true);
	}
}
