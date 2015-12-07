package main;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * This is the date class to keep the date of auction as well as times.
 * 
 * @author Stepan Adespya
 * @edited by Mindy Huynh 12/5/2015
 * @since November 14, 2015
 */
@SuppressWarnings("serial")
public class Date implements java.io.Serializable
{
	// holds the date entered
	/**
	 * This is the String value of date.
	 */
	private String date;
	
	/**
	 * This is the constructor for the date entered.
	 * 
	 * @Pre String with MM/dd/yyyy HH:mm:ss format
	 * @Post creates a date choosen by user
	 */
	public Date(String date)
	{
		this.date = date;
	}
	
	/**
	 * This is the default constructor. If date is not entered, it takes the
	 * current date and time.
	 * 
	 * @Pre nothing
	 * @Post takes the current date
	 */
	public Date()
	{
		Calendar cal = Calendar.getInstance();
		this.date = cal.get(Calendar.MONTH) + 1 + "/"
				+ cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.YEAR)
				+ " " + cal.get(Calendar.HOUR_OF_DAY) + ":"
				+ cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
	}
	
	/**
	 * This is the getter for the month.
	 * 
	 * @return int month of the date
	 * @throws ParseException
	 * @Pre nothing
	 * @Post returns integer of month
	 */
	public int getMonth() throws ParseException
	{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTime(df.parse(date));
		return c.get(Calendar.MONTH) + 1;
	}
	
	/**
	 * This is the getter for day.
	 * 
	 * @return int day of the date
	 * @throws ParseException
	 * @Pre nothing
	 * @Post returns integer of day
	 */
	public int getDay() throws ParseException
	{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTime(df.parse(date));
		return c.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * This is the getter for year.
	 * 
	 * @return int year of the date
	 * @throws ParseException
	 * @Pre nothing
	 * @Post returns integer of year
	 */
	public int getYear() throws ParseException
	{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTime(df.parse(date));
		return c.get(Calendar.YEAR);
	}
	
	/**
	 * This is the getter for the hour of the date.
	 * 
	 * @return int hour of date
	 * @throws ParseException
	 * @Pre nothing
	 * @Post returns integer of hour
	 */
	public int getHour() throws ParseException
	{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTime(df.parse(date));
		return c.get(Calendar.HOUR_OF_DAY);
	}
	
	/**
	 * This is the getter for the minutes of the date.
	 * 
	 * @return int minutes of the date
	 * @throws ParseException
	 * @Pre nothing
	 * @Post returns integer of minutes
	 */
	public int getMinutes() throws ParseException
	{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTime(df.parse(date));
		return c.get(Calendar.MINUTE);
	}
	
	/**
	 * This is the getter for the seconds of the date.
	 * 
	 * @return int seconds
	 * @throws ParseException
	 * @Pre nothing
	 * @Post returns integer of seconds
	 */
	public int getSeconds() throws ParseException
	{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTime(df.parse(date));
		return c.get(Calendar.SECOND);
	}
	
	/**
	 * This is the setter for month of date.
	 * 
	 * @param month
	 *            the new month of the date.
	 * @throws ParseException
	 * @Pre integer greater than 0 but less than 12
	 * @Post sets the date to correct month
	 */
	public void setMonth(int month) throws ParseException
	{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTime(df.parse(date));
		String newDate = month + "/" + this.getDay() + "/" + this.getYear()
				+ " " + this.getHour() + ":" + this.getMinutes() + ":"
				+ this.getSeconds();
		this.date = newDate;
	}
	
	/**
	 * This is the setter for day.
	 * 
	 * @param day
	 *            new the day of the date.
	 * @throws ParseException
	 * @Pre integer greater than 0 but less than 32
	 * @Post sets the date to correct day
	 */
	public void setDay(int day) throws ParseException
	{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTime(df.parse(date));
		String newDate = this.getMonth() + "/" + day + "/" + this.getYear()
				+ " " + this.getHour() + ":" + this.getMinutes() + ":"
				+ this.getSeconds();
		this.date = newDate;
	}
	
	/**
	 * This is the setter for year of date.
	 * 
	 * @param year
	 *            the new year of date.
	 * @throws ParseException
	 * @Pre integer greater than 0
	 * @Post sets the date to correct year
	 */
	public void setYear(int year) throws ParseException
	{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTime(df.parse(date));
		String newDate = this.getMonth() + "/" + this.getDay() + "/" + year
				+ " " + this.getHour() + ":" + this.getMinutes() + ":"
				+ this.getSeconds();
		this.date = newDate;
	}
	
	/**
	 * This is the setter for the hour of date.
	 * 
	 * @param hour
	 *            the new hour to be changed.
	 * @throws ParseException
	 * @Pre integer non negative and less than 24
	 * @Post sets the date to correct hour
	 */
	public void setHour(int hour) throws ParseException
	{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTime(df.parse(date));
		String newDate = this.getMonth() + "/" + this.getDay() + "/"
				+ this.getYear() + " " + hour + ":" + this.getMinutes() + ":"
				+ this.getSeconds();
		this.date = newDate;
	}
	
	/**
	 * This is the setter for the minutes of the date.
	 * 
	 * @param minutes
	 *            the new minute of the date.
	 * @throws ParseException
	 * @Pre integer non negative and less than 60
	 * @Post sets the date to correct minute
	 */
	public void setMinutes(int minutes) throws ParseException
	{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTime(df.parse(date));
		String newDate = this.getMonth() + "/" + this.getDay() + "/"
				+ this.getYear() + " " + this.getHour() + ":" + minutes + ":"
				+ this.getSeconds();
		this.date = newDate;
	}
	
	/**
	 * This is the setter for the seconds of date.
	 * 
	 * @param seconds
	 *            the new second of the date.
	 * @throws ParseException
	 * @Pre integer non negative and less than 60
	 * @Post sets the date to correct second
	 */
	public void setSeconds(int seconds) throws ParseException
	{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTime(df.parse(date));
		String newDate = this.getMonth() + "/" + this.getDay() + "/"
				+ this.getYear() + " " + this.getHour() + ":"
				+ this.getMinutes() + ":" + seconds;
		this.date = newDate;
	}
	
	/**
	 * This is the getter for difference bettween two dates in hours.
	 * 
	 * @param date
	 *            the date we want to compare with.
	 * @return the difference between this date and the new date.
	 * @throws ParseException
	 * @Pre date that is not NULL
	 * @Post hours between the two dates)
	 */
	public long getDiffHours(Date date) throws ParseException
	{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTime(df.parse(this.date));
		Calendar c1 = Calendar.getInstance();
		c1.setTime(df.parse(date.date));
		long diff = c1.getTimeInMillis() - c.getTimeInMillis();
		return TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	/**
	 * This method gives the difference of two dates by day.
	 * 
	 * @param date
	 *            the date to be compared to this.date.
	 * @return the number of hours in difference for the two dates.
	 * @throws ParseException
	 * @Pre date that is not NULL
	 * @Post days between the two dates
	 */
	public long getDiffDay(Date date) throws ParseException
	{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTime(df.parse(this.date));
		Calendar c1 = Calendar.getInstance();
		c1.setTime(df.parse(date.date));
		long diff = c1.getTimeInMillis() - c.getTimeInMillis();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	/**
	 * This method checks if current date is before another date.
	 * 
	 * @param date
	 *            the date to compare to, to see if one is before the other.
	 * @return true iff current date is before the date entered
	 * @throws ParseException
	 * @Pre date that is not NULL
	 */
	public boolean before(Date date) throws ParseException
	{
		if (this.getYear() < date.getYear())
		{
			return true;
		} else if (this.getYear() == date.getYear())
		{
			if (this.getMonth() < date.getMonth())
			{
				return true;
			} else if (this.getMonth() == date.getMonth())
			{
				if (this.getDay() < date.getDay())
				{
					return true;
				} else if (this.getDay() == date.getDay())
				{
					if (this.getHour() < date.getHour())
					{
						return true;
					} else if (this.getHour() == date.getHour())
					{
						if (this.getMinutes() < date.getMinutes())
						{
							return true;
						} else if (this.getMinutes() == date.getMinutes())
						{
							if (this.getSeconds() < date.getSeconds())
							{
								return true;
							} else
							{
								return false;
							}
						} else
						{
							return false;
						}
					} else
					{
						return false;
					}
				} else
				{
					return false;
				}
			} else
			{
				return false;
			}
		} else
		{
			return false;
		}
	}
	
	/**
	 * This method adds days to the current date.
	 * 
	 * @param days
	 *            the number of days we want added to current date.
	 * @throws ParseException
	 * @Pre integer of days wanted to add
	 * @Post date is corrected to what is added)
	 */
	public void addDays(int days) throws ParseException
	{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTime(df.parse(date));
		c.add(java.util.Calendar.DATE, days);
		String newDate = c.get(Calendar.MONTH) + 1 + "/"
				+ c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.YEAR)
				+ " " + c.get(Calendar.HOUR_OF_DAY) + ":"
				+ c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
		this.date = newDate;
	}
	
	/**
	 * This method adds hours to the date.
	 * 
	 * @param hours
	 *            the number of hours we want to add to current date.
	 * @throws ParseException
	 * @Pre integer of hours wanted to add
	 * @Post date is corrected to what is added
	 */
	public void addHours(int hours) throws ParseException
	{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTime(df.parse(date));
		c.add(java.util.Calendar.HOUR_OF_DAY, hours);
		String newDate = c.get(Calendar.MONTH) + 1 + "/"
				+ c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.YEAR)
				+ " " + c.get(Calendar.HOUR_OF_DAY) + ":"
				+ c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
		this.date = newDate;
	}
	
	/**
	 * This method clones the date to another object.
	 * 
	 * @Pre Date not NULL
	 * @Post have the same date
	 */
	public Date clone()
	{
		return new Date(this.date);
	}
	
	/**
	 * This method returns the date in string.
	 * @return the date.
	 * @Pre date is in correct format.
	 * @Post correct date
	 */
	public String toString()
	{
		return date;
	}
	
}