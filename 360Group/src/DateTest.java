import java.text.ParseException;

public class DateTest
{
	
	public static void main(String[] args) throws ParseException
	{
		Date d = new Date();
		// System.out.println(d.getMonth());
		// System.out.println(d.getDay());
		// System.out.println(d.getYear());
		// System.out.println(d.getHour());
		// System.out.println(d.getMinutes());
		// System.out.println(d.getSeconds());
		d.addDays(90);
		System.out.println(d.toString());
		Date d1 = new Date();
		d1.addDays(91);
		System.out.println(d1.toString());
		if (d1.before(d))
		{
			System.out.println("Future");
		} else
		{
			System.out.println("Past");
		}
		System.out.println(d1.getDiffDay(d) + "Days");
		
	}
	
}
