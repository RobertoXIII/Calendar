package view;

import controller.CalendarController;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.GregorianCalendar;
import javax.swing.JTable;
import model.CalendarDataProvider;
import model.NewCalendar;

class SmallMonthView$1 extends MouseAdapter
{
  private Object val$monthTable;

SmallMonthView$1(SmallMonthView paramSmallMonthView, JTable paramJTable) {}
  

/*
  public void mouseClicked(MouseEvent e)
  {
    int row = this.val$monthTable.rowAtPoint(e.getPoint());
    int col = this.val$monthTable.columnAtPoint(e.getPoint());
    
    int month = CalendarDataProvider.getMonth(SmallMonthView.access$000(this.this$0));
    int year = CalendarDataProvider.getYear(SmallMonthView.access$000(this.this$0));
    int dayOfMonth = row * 7 + col + 1;
    GregorianCalendar newDate = new GregorianCalendar(year, month, dayOfMonth);
    SmallMonthView.access$100(this.this$0).cal.setSelectedDay(newDate);
  }*/
}
