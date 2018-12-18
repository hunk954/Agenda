package Agenda;


public class Date {
	private int m_year;
	private int m_month;
	private int m_day;
	private int m_hour;
	private int m_minute;
	private static int months[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	private static int monthsLeap[] = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	public static boolean isLeap(int year) {
		if((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)) 
			return true;
		return false;
	}
	
	public Date() {
		m_year = 0;
		m_month = 0;
		m_day = 0;
		m_hour = 0;
		m_minute = 0;
	}
	
	public Date(int t_year, int t_month, int t_day, int t_hour, int t_minute) {
		m_year = t_year;
		m_month = t_month;
		m_day = t_day;
		m_hour = t_hour;
		m_minute = t_minute;
	}
	
	public Date(final String dateString) {
		boolean flag = true;
		final char str[] = dateString.toCharArray();
		if(str[4] != '-') flag = false;
		else if(str[7] != '-') flag = false;
		else if(str[10] != '/') flag = false;
		else if(str[13] != ':') flag = false;
		for(int i = 0; i < str.length && flag; i++) {
			if((i-1) % 3 == 0 && i != 1) continue;
			else if(!Character.isDigit(str[i]))flag = false;
		}
		if(flag){
			int year =  (str[0] - '0') * 1000 + (str[1] - '0') * 100 +(str[2] - '0') * 10 +(str[3] - '0');
			int month = (str[5] - '0') * 10 + (str[6] - '0');
			int day = (str[8] - '0') * 10 + (str[9] - '0');
			int hour = (str[11] - '0') * 10 + (str[12] - '0');
			int minute = (str[14] - '0') * 10 + (str[15] - '0');
			Date temp  = new Date(year, month, day, hour, minute);
			if(isValid(temp)){
				m_year = year;
				m_month = month;
				m_day = day;
				m_hour = hour;
				m_minute = minute;
			}
			else{
				m_year = 0;
				m_month = 0;
				m_day = 0;
				m_hour = 0;
				m_minute = 0;
			}
		}
		else{
			m_year = 0;
			m_month = 0;
			m_day = 0;
			m_hour = 0;
			m_minute = 0;
		}
	}
	public int getYear() {
		return m_year;
	}
	public void setYear(int t_year) {
		m_year = t_year;
	}
	public int getMonth() {
		return m_month;
	}
	public void setMonth(int t_month) {
		m_month = t_month;
	}
	public int getDay() {
		return m_day;
	}
	public void setDay(int t_day) {
		m_day = t_day;
	}
	public int getHour() {
		return m_hour;
	}
	public void setHour(int t_hour) {
		m_hour = t_hour;
	}
	public int getMinute() {
		return m_minute;
	}
	public void setMinute(int t_minute) {
		m_minute = t_minute;
	}
	public boolean isValid(Date t_date) {
		if(t_date.m_year < 1000 || t_date.m_year > 9999) return false;
		if(t_date.m_month < 0 || t_date.m_month > 12) return false;
		if(isLeap(t_date.m_year)){
			if(t_date.m_day > monthsLeap[t_date.m_month-1] || t_date.m_day <= 0)
				return false;
		}
		else{
			if(t_date.m_day > months[t_date.m_month-1] || t_date.m_day <= 0)
				return false;
		}
		if(t_date.m_hour < 0 || t_date.m_hour >= 24) return false;
		if(t_date.m_minute < 0 || t_date.m_minute >= 60) return false;
		return true;
	}
	public Date stringToDate(String t_dateString) {
		Date date = new Date(t_dateString);
		return date;
	}
	public String dateToString(Date t_date) {
		if (!isValid(t_date)) {
			String strDate = "0000-00-00/00:00";
			return strDate;
		}
		else {
			String strDate = "";
			int temp;
			//getYear
			temp = t_date.getYear();
			strDate += temp;
			strDate += "-";
			
			//getMonth
			temp = t_date.getMonth();
			if(temp < 10) strDate += "0";
			strDate += temp;
			strDate += "-";
			
			//getDay
			temp = t_date.getDay();
			if(temp < 10) strDate += "0";
			strDate += temp;
			strDate += "/";
			
			//getHour
			temp = t_date.getHour();
			if(temp < 10) strDate += "0";
			strDate += temp;
			strDate += ":";
			
			//getMinute
			temp = t_date.getMinute();
			if(temp < 10) strDate += "0";
			strDate += temp;
			 
			return strDate;
		}
	}
	Date assign(Date t_date) {
		m_year= t_date.getYear();
		m_month = t_date.getMonth();
		m_day = t_date.getDay();
		m_hour = t_date.getHour();
		m_minute = t_date.getMinute();
		return this;
	}
	
	boolean equal(Date t_date) {
		if(m_year == t_date.getYear() && m_month == t_date.m_month && m_day == t_date.m_day && m_hour == t_date.m_hour && m_minute == t_date.m_minute)
			return true;
		return false;
	}
	
	boolean greaterThan(Date t_date) {
		if(m_year > t_date.m_year)	return true;
		else if(m_year == t_date.m_year){
			if(m_month > t_date.m_month) return true;
			else if(m_month == t_date.m_month){
				if(m_day > t_date.m_day) return true;
				else if(m_day == t_date.m_day){
					if(m_hour > t_date.m_hour) return true;
					else if(m_hour == t_date.m_hour){
						if(m_minute > t_date.m_minute) return true;
						else return false;
					}
					else return false;
				}
				else return false;
			}
			else return false;
		}
		else return false;
	}
	boolean lessThan(Date t_date) {
		if(m_year < t_date.m_year)	return true;
		else if(m_year == t_date.m_year){
			if(m_month < t_date.m_month) return true;
			else if(m_month == t_date.m_month){
				if(m_day < t_date.m_day) return true;
				else if(m_day == t_date.m_day){
					if(m_hour < t_date.m_hour) return true;
					else if(m_hour == t_date.m_hour){
						if(m_minute < t_date.m_minute) return true;
						else return false;
					}
					else return false;
				}
				else return false;
			}
			else return false;
		}
		else return false;
	}
	boolean greaterOrEqual(Date t_date) {
		if(m_year > t_date.m_year)	return true;
		else if(m_year == t_date.m_year){
			if(m_month > t_date.m_month) return true;
			else if(m_month == t_date.m_month){
				if(m_day > t_date.m_day) return true;
				else if(m_day == t_date.m_day){
					if(m_hour > t_date.m_hour) return true;
					else if(m_hour == t_date.m_hour){
						if(m_minute >= t_date.m_minute) return true;
						else return false;
					}
					else return false;
				}
				else return false;
			}
			else return false;
		}
		else return false;
	}
	boolean lessOrEqual(Date t_date) {
		if(m_year < t_date.m_year)	return true;
		else if(m_year == t_date.m_year){
			if(m_month < t_date.m_month) return true;
			else if(m_month == t_date.m_month){
				if(m_day < t_date.m_day) return true;
				else if(m_day == t_date.m_day){
					if(m_hour < t_date.m_hour) return true;
					else if(m_hour == t_date.m_hour){
						if(m_minute <= t_date.m_minute) return true;
						else return false;
					}
					else return false;
				}
				else return false;
			}
			else return false;
		}
		else return false;
	}
}