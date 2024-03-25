package net.piyush.commons;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class CommonConstants {
	
	public static final String db_Name="erprmwise";
	
	public static final String EMPTY_STRING = "";
	
	public static final String SINGLE_QUOTE = "'";
	
	public static final String reset = "\u001B[0m";
	public static final String red = "\u001B[31m";
	public static final String green = "\u001B[32m";
	public static final String yellow = "\u001B[33m";
	public static final String blue = "\u001B[34m";
	public static final String purple = "\u001B[35m";
	public static final String cyan = "\u001B[36m";
	public static final String white = "\u001B[37m";

    // ANSI escape codes for background colors
	public static final String redBackground = "\u001B[41m";
	public static final String greenBackground = "\u001B[42m";
	public static final String yellowBackground = "\u001B[43m";
	public static final String blueBackground = "\u001B[44m";
	public static final String purpleBackground = "\u001B[45m";
	public static final String cyanBackground = "\u001B[46m";
	public static final String whiteBackground = "\u001B[47m";

    // ANSI escape codes for text formatting
	public static final String bold = "\u001B[1m";
	public static final String underline = "\u001B[4m";
	public static final String blink = "\u001B[5m";
	public static final String reverseVideo = "\u001B[7m";
	
    public static  LocalDate currentDate = LocalDate.now();                       //2023-10-18
    public static int currentMonthNumeric = currentDate.getMonthValue();          // 10 
    public static int currentYearValue = currentDate.getYear();                   //2023
    public static int currentDateValue = currentDate.getDayOfMonth();             //Todays Date
    public static  String currentMonthText = currentDate.getMonth().toString();   //OCTOBER
    
    
    public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final String formattedDate = currentDate.format(dateFormatter);       //2023-10-18
    
    public static final String dayOfWeek = currentDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);  //WEDNESDAY
    
    public static LocalTime currentTime = LocalTime.now();
    public static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static String formattedTime = currentTime.format(timeFormatter);        //
    
    public static Month month = Month.of(currentMonthNumeric);
    public static String monthName = month.toString();       //OCTOBER

     public static Year currentYear = Year.now();    //2023
    

}
