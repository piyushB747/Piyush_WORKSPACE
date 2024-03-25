package net.piyush.commons;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class CommonUtils {

	public static String nullToBlank(String f_strInputValue, boolean f_blnQuotesFlag) {
        if (f_strInputValue == null) {
            return CommonConstants.EMPTY_STRING;
        }
        if (f_blnQuotesFlag) {
            if (f_strInputValue.startsWith(CommonConstants.SINGLE_QUOTE) && f_strInputValue.endsWith(CommonConstants.SINGLE_QUOTE)) {
                f_strInputValue = f_strInputValue.substring(1, f_strInputValue.length() - 1);
            }
        }
        return f_strInputValue;
    }
	
	
	 public static int getNoOfDaysInMonthCurrentMonthWithMonthNo(int m_strMonth, int year) {
	        int l_intdays = 0;
	        try {
	            int[] monthDays = {31, 29, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	            if (m_strMonth == 1) {
	                l_intdays = monthDays[0];
	            } else if (m_strMonth == 2) {

	                if (year % 4 == 0) {
	                    if (year % 400 == 0 || (year % 100 != 0)) {
	                        l_intdays = monthDays[1];
	                    }
	                } else {
	                    l_intdays = monthDays[2];
	                }
	            } else if (m_strMonth == 3) {
	                l_intdays = monthDays[3];
	            } else if (m_strMonth == 4) {
	                l_intdays = monthDays[4];
	            } else if (m_strMonth == 5) {
	                l_intdays = monthDays[5];
	            } else if (m_strMonth == 6) {
	                l_intdays = monthDays[6];
	            } else if (m_strMonth == 7) {
	                l_intdays = monthDays[7];
	            } else if (m_strMonth == 8) {
	                l_intdays = monthDays[8];
	            } else if (m_strMonth == 9) {
	                l_intdays = monthDays[9];
	            } else if (m_strMonth == 10) {
	                l_intdays = monthDays[10];
	            } else if (m_strMonth == 11) {
	                l_intdays = monthDays[11];
	            } else if (m_strMonth == 12) {
	                l_intdays = monthDays[12];
	            }
	            return l_intdays;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return l_intdays;
	    }
	 
	 public static String getQarterByYear(String m_strQarter) {
		    String m_strFromMonth="";
	       	String m_strToMonth="";
			if(m_strQarter.equals("Q1")) {
				m_strFromMonth="1";
				m_strToMonth="3";
				//m_strQ=new ArrayList<String>(Arrays.asList("1","2","3"));
			}else if(m_strQarter.equals("Q2")) {
				m_strFromMonth="4";
				m_strToMonth="6";
				//m_strQ=new ArrayList<String>(Arrays.asList("4","5","6"));
			}else if(m_strQarter.equals("Q3")) {
				m_strFromMonth="7";
				m_strToMonth="9";
				//m_strQ=new ArrayList<String>(Arrays.asList("7","8","9"));
			}else if(m_strQarter.equals("Q4")) {
				m_strFromMonth="10";
				m_strToMonth="12";
				//m_strQ=new ArrayList<String>(Arrays.asList("10","11","12"));
			}
			 return  m_strFromMonth+"-"+m_strToMonth;
	 }
	 
	 public static List<String>  getQarterByYearList(String m_strQarter) {
	       	List<String> m_strQ=new ArrayList<String>();
	       	if(m_strQarter.equals("Q1")) {
				m_strQ=new ArrayList<String>(Arrays.asList("1","2","3"));
			}else if(m_strQarter.equals("Q2")) {
				m_strQ=new ArrayList<String>(Arrays.asList("4","5","6"));
			}else if(m_strQarter.equals("Q3")) {
				m_strQ=new ArrayList<String>(Arrays.asList("7","8","9"));
			}else if(m_strQarter.equals("Q4")) {
				m_strQ=new ArrayList<String>(Arrays.asList("10","11","12"));
			}
			 return m_strQ;
	 }
	 
	 
	 public static String getQarterByPFY(String m_strQarter,String part1PreviousYear,String part2CurrentYear) {
		    
		    String m_strFromMonth="";String m_strToMonth="";String m_strYearForPfy="";
			if(m_strQarter.equals("Q1")) {
         	m_strYearForPfy=part1PreviousYear;
				m_strFromMonth="4";
				m_strToMonth="6";
			}else if(m_strQarter.equals("Q2")) {
				m_strYearForPfy=part1PreviousYear;
				m_strFromMonth="7";
				m_strToMonth="9";
			}else if(m_strQarter.equals("Q3")) {
				m_strYearForPfy=part1PreviousYear;
				m_strFromMonth="10";
				m_strToMonth="12";
			}else if(m_strQarter.equals("Q4")) {
				m_strYearForPfy=part2CurrentYear;
				m_strFromMonth="1";
				m_strToMonth="3";
			}
			return m_strFromMonth+"-"+m_strToMonth+"-"+m_strYearForPfy;
	 }
	
	 public static String getMonthName(int monthNumber) {
		 Month month = Month.of(monthNumber);
		 //return  month.name();   //OCTOBER
	      return month.getDisplayName(TextStyle.SHORT, java.util.Locale.ENGLISH);

	 }
	 public  static String generateUniqueId() {
	        // Generate a random UUID and convert it to a string
	        return UUID.randomUUID().toString();
	    }
}
