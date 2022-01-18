package gui.util;

public class DateCheckker {

    private String date;

    public DateCheckker(){
        date = "";
    }


    /**
     * converts a string into a date represented as a int number.
     * @param stringMonth the month
     * @param day day of month
     * @param year the year.
     * @return
     */
    public int getDate(String stringMonth, String day, int year){
        switch (stringMonth){

            case "Jan":
                date = year + "01" + day ;
                break;

            case "Feb":
                date = year + "02" + day ;
                break;
            case "Mar":
                date = year + "03" + day ;
                break;
            case "Apr":
                date = year + "04" + day ;
                break;
            case "May":
                date = year + "05" + day ;
                break;
            case "Jun":
                date = year + "06" + day ;
                break;
            case "Jul":
                date = year + "07" + day ;
                break;
            case "Aug":
                date = year + "08" + day ;
                break;
            case "Sep":
                date = year + "09" + day ;
                break;
            case "Oct":
                date = year + "10" + day ;
                break;
            case "Nov":
                date = year + "11" + day ;
                break;
            case "Dec":
                date = year + "12" + day ;
                break;

        }
        return Integer.parseInt(date);
    }

    /**
     * checks if more than 2 years have passed from a specific date.
     * @param movieTime
     * @param todayTime
     * @return
     */
    public boolean checkForMoreThan2Years(String movieTime, String todayTime){

        int movieDate = getDate(movieTime.substring(4,7), movieTime.substring(8, 10), Integer.parseInt(movieTime.substring(movieTime.length() - 4)));
        int todayDate = getDate(todayTime.substring(4,7), todayTime.substring(8, 10), Integer.parseInt(todayTime.substring(movieTime.length() - 4)) -2);
        if (movieDate <= todayDate){
            return true;
        }
        return false;
    }
}
