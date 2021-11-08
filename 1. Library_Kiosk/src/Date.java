import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * The Date class handles the creation of Date objects and provides all values for said objects.
 * Additionally, it holds the method, isValid(), which verifies the validity of a date represented by the Date object.
 * Also, it holds a testbed main method to test a set of testcases to confirm the competency of isValid().
 * @author Anuraj Dubey, Chenghao Lin
 *
 */
public class Date {

    private int year;
    private int month;
    private int day;

    //all constant variables
    public static final int JANUARY = 1;
    public static final int FEBRUARY = 2;
    public static final int MARCH = 3;
    public static final int APRIL = 4;
    public static final int MAY = 5;
    public static final int JUNE = 6;
    public static final int JULY = 7;
    public static final int AUGUST = 8;
    public static final int SEPTEMBER = 9;
    public static final int OCTOBER = 10;
    public static final int NOVEMBER = 11;
    public static final int DECEMBER = 12;
    public static final int CURRENT_YEAR = 2021;
    public static final int YEAR_1900 = 1900;
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUARTERCENTENNIAL = 400;
    public static final int LEAP_DAY = 29;
    public static final int DAY_30 = 30;
    public static final int DAY_31 = 31;

    /**
     * This method creates a Date object by using an input of a String (date) of the format mm/dd/yyyy.
     * @param date in MM/DD/YYYY format to be tokenized
     */
    public Date(String date) {  //taking mm/dd/yyyy and create a Date object
        StringTokenizer str = new StringTokenizer(date,"/", false);  //create StringTokenizer object

        this.month = Integer.parseInt(str.nextToken());
        this.day = Integer.parseInt(str.nextToken());
        this.year = Integer.parseInt(str.nextToken());
    }

    /**
     * This method creates an object of the current date today.
     */
    public Date() {  //create an object with today’s date (see Calendar class)

        Calendar currDate = Calendar.getInstance();

        this.day = currDate.get(Calendar.DAY_OF_MONTH);
        this.year = currDate.get(Calendar.YEAR);
        this.month = currDate.get(Calendar.MONTH) + 1;   // IS THIS MAGIC NUMBER???


    }

    //getter methods
    /**
     * A method to get the month value of the object.
     * @return the month value of the object
     */
    public int getMonth(){
        return month;
    }

    /**
     * A method to get the day value of the object.
     * @return the day value of the object
     */
    public int getDay(){
        return day;
    }

    /**
     * A method to get the year value of the object.
     * @return the year value of the object
     */
    public int getYear(){
        return year;
    }

    /**
     * This method checks a Date object, and confirms if it is indeed a valid date of publishment, based on sensible criteria.
     * @return True if the date is valid, or false if the date is invalid.
     */
    public boolean isValid() {

        Calendar currDate = Calendar.getInstance();

        if (this.year <= 0 || this.day <= 0 || this.month <= 0) //check if day, month, year is a negative value or 0
            return false;
        if (this.month > DECEMBER) //check if month value exceeds the max month of December (12)
            return false;
        if ( this.year < YEAR_1900 || this.year > CURRENT_YEAR) //check if year exceeds current year, or if year preceeds 1900
            return false;
        if ( this.year == CURRENT_YEAR){ //if year value is of our current year
            if (this.month > (currDate.get(Calendar.MONTH) + 1)){ //if month value exceeds current month, invalid date
                return false;
            }
            if (this.month == (currDate.get(Calendar.MONTH) + 1)){ //if month value is current month
                if (this.day > currDate.get(Calendar.DAY_OF_MONTH)) //if day value is greater than current day, invalid date
                    return false;

            }
        }

        if (this.month == JANUARY && this.day > DAY_31) //check if day in January is invalid
            return false;

        if ( this.month == FEBRUARY){
            if (this.day > LEAP_DAY) return false; //if day in February is more than 29, return false

            if (this.day == LEAP_DAY){
                if (!(this.year%QUADRENNIAL == 0)) //if year is not divisible by 4, return false
                    return false;
                else if (this.year%CENTENNIAL == 0){ //if year is divisible by 4 and divisible by 100...
                    if (!(this.year%QUARTERCENTENNIAL == 0)) //if year is divisible by 4 and 100, but not 400, return false
                        return false;
                }
            }
        }

        if (this.month == MARCH && this.day > DAY_31) //check if day in March is invalid
            return false;

        if (this.month == APRIL && this.day > DAY_30) //check if day in April is invalid
            return false;

        if (this.month == MAY && this.day > DAY_31) //check if day in May is invalid
            return false;

        if (this.month == JUNE && this.day > DAY_30) //check if day in June is invalid
            return false;

        if (this.month == JULY && this.day > DAY_31) //check if day in July is invalid
            return false;

        if (this.month == AUGUST && this.day > DAY_31) //check if day in August is invalid
            return false;

        if (this.month == SEPTEMBER && this.day > DAY_30) //check if day in September is invalid
            return false;

        if (this.month == OCTOBER && this.day > DAY_31) //check if day in October is invalid
            return false;

        if (this.month == NOVEMBER && this.day > DAY_30) //check if day in November is invalid
            return false;

        if (this.month == DECEMBER && this.day > DAY_31) //check if day in December is invalid
            return false;

        return true; //if all conditions are passed, date is valid
    }
    
    /**
     *
     * @param args
     * This is a main method, which is used to execute a given set of Date testcases in order to test the validity of these Dates.
     */
    public static void main(String[] args){ //testbed main for isValid()
        System.out.println("Testbed main running");

        //test1, testing today's date,tomorrow's date, yesterday's date
        System.out.println("\nTest block 1, testing today, tomorrow, and yesterdays date");
        Date test1_case1=new Date("2/9/2021");
        if (test1_case1.isValid()){
            System.out.println("Creating date that is today's date. PASSED");
        }
        else{
            System.out.println("Creating date that is today's date. FAILED");
        }
        Date test1_case2=new Date("2/10/2021");
        if (!test1_case2.isValid()){
            System.out.println("Creating date that is tomorrow's date. PASSED");
        }
        else{
            System.out.println("Creating date that is tomorrow's date. FAILED");
        }
        Date test1_case3=new Date("2/8/2021");
        if (test1_case3.isValid()){
            System.out.println("Creating date that is yesterday's date. PASSED");
        }
        else{
            System.out.println("Creating date that is yesterday's date. FAILED");
        }

        //test 2, testing year ranges, too old(before 1900), in the future(after 2021), right at the cutoff(1900)
        System.out.println("\nTest block 2, testing years that are too old, right at cutoff, in the future");
        Date test2_case1=new Date("1/10/1899");
        if (!test2_case1.isValid()){
            System.out.println("Creating date that is too old. PASSED");
        }
        else{
            System.out.println("Creating date that is too old. FAILED");
        }
        Date test2_case2=new Date("1/10/1900");
        if (test2_case2.isValid()){
            System.out.println("Creating date that is right at cutoff(1900). PASSED");
        }
        else{
            System.out.println("Creating date that is right at cutoff(1900). FAILED");
        }
        Date test2_case3=new Date("1/10/2022");
        if (!test2_case3.isValid()){
            System.out.println("Creating date that is in the future. PASSED");
        }
        else{
            System.out.println("Creating date that is in the future. FAILED");
        }

        //test 3, testing months too large/small, days too large/small
        System.out.println("\nTest block 3, testing months and days too large/small");
        Date test3_case1=new Date("31/2/2000");
        if (!test3_case1.isValid()){
            System.out.println("Creating date with month value too large. PASSED");
        }
        else{
            System.out.println("Creating date with month value too large. FAILED");
        }
        Date test3_case2=new Date("13/2/2020");
        if (!test3_case2.isValid()){
            System.out.println("Creating date with month value barely too large. PASSED");
        }
        else{
            System.out.println("Creating date with month value barely too large. FAILED");
        }
        Date test3_case3=new Date("0/5/1999");
        if (!test3_case3.isValid()){
            System.out.println("Creating date with month value too small. PASSED");
        }
        else{
            System.out.println("Creating date with month value too small. FAILED");
        }
        Date test3_case4=new Date("1/0/1999");
        if (!test3_case4.isValid()){
            System.out.println("Creating date with day value too small. PASSED");
        }
        else{
            System.out.println("Creating date with day value too small. FAILED");
        }
        Date test3_case5=new Date("1/50/1999");
        if (!test3_case5.isValid()){
            System.out.println("Creating date with day value too large. PASSED");
        }
        else{
            System.out.println("Creating date with day value too large. FAILED");
        }
        Date test3_case6=new Date("2/32/1999");
        if (!test3_case6.isValid()){
            System.out.println("Creating date with day value barely too large. PASSED");
        }
        else{
            System.out.println("Creating date with day value barely too large. FAILED");
        }

        //test 4, testing months with their respective number of days
        System.out.println("\nTest block 4, testing months with their respective number of days");
        Date test4_case1=new Date("1/31/2000");
        if (test4_case1.isValid()){
            System.out.println("Creating date on last day of January. PASSED");
        }
        else{
            System.out.println("Creating date on last day of January. FAILED");
        }
        Date test4_case2=new Date("4/31/2000");
        if (!test4_case2.isValid()){
            System.out.println("Creating date on last day of April. PASSED");
        }
        else{
            System.out.println("Creating date on last day of April. FAILED");
        }
        Date test4_case3=new Date("5/31/2000");
        if (test4_case3.isValid()){
            System.out.println("Creating date on last day of May. PASSED");
        }
        else{
            System.out.println("Creating date on last day of May. FAILED");
        }
        Date test4_case4=new Date("6/31/2000");
        if (!test4_case4.isValid()){
            System.out.println("Creating date on last day of June. PASSED");
        }
        else{
            System.out.println("Creating date on last day of June. FAILED");
        }

        //test 5, testing leap years
        System.out.println("\nTest block 4, testing for leap years");
        Date test5_case1=new Date("2/29/2020");
        if (test5_case1.isValid()){
            System.out.println("Creating date on existing leap day. PASSED");
        }
        else{
            System.out.println("Creating date on existing leap day. FAILED");
        }
        Date test5_case2=new Date("2/29/2009");
        if (!test5_case2.isValid()){
            System.out.println("Creating date on non-existing leap day. PASSED");
        }
        else{
            System.out.println("Creating date on non-existing leap day. FAILED");
        }
        Date test5_case3=new Date("2/29/2000");
        if (test5_case3.isValid()){
            System.out.println("Test a leap year that is multiple of 100, but divisible by 400. PASSED");
        }
        else{
            System.out.println("Test a leap year that is multiple of 100, but divisible by 400. FAILED");
        }
        Date test5_case4=new Date("2/29/1900");
        if (!test5_case4.isValid()){
            System.out.println("Test a leap year that that is multiple of 100, but not divisible by 400. PASSED");
        }
        else{
            System.out.println("Test a leap year that that is multiple of 100, but not divisible by 400. FAILED");
        }
        Date test5_case5=new Date("2/29/1904");
        if (test5_case5.isValid()){
            System.out.println("Test a leap year after skipped leap year. PASSED");
        }
        else{
            System.out.println("Test a leap year after skipped leap year. FAILED");
        }

        //test 5, testing valid dates
        System.out.println("\nTest block 4, testing that isValid() also works for valid dates");
        Date test6_case1=new Date("1/15/1950");
        if (test6_case1.isValid()){
            System.out.println("Testing Valid dates. PASSED");
        }
        else{
            System.out.println("Testing Valid dates. FAILED");
        }
        Date test6_case2=new Date("10/31/1969");
        if (test6_case2.isValid()){
            System.out.println("Testing Valid dates. PASSED");
        }
        else{
            System.out.println("Testing Valid dates. FAILED");
        }
        Date test6_case3=new Date("4/20/1969");
        if (test6_case3.isValid()){
            System.out.println("Testing Valid dates. PASSED");
        }
        else{
            System.out.println("Testing Valid dates. FAILED");
        }
    }
}
