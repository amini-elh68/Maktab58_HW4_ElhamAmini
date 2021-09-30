package Maktab58_HW4_ElhamAmini.five;

public class MyDate {
    private int month;
    private int year;
    private int day;

    // private  MyDate myDate;

    public MyDate(int year, int month, int day) {
        this.month = month;
        this.year = year;
        this.day = day;
        //  myDate= new MyDate(month,year,day);
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public boolean isValidDate(int year, int month, int day) {
        if (year < 1 || year > 9999) {
            return false;
        }
        //month between 1-12

        if (month < 1 || month > 12) {
            return false;
        }

        //totalDay=30 or 31
        int totalDay = getMonthLastDay(month);
        //if day not between 1-31
        return day >= 1 && day <= totalDay;
    }

    public int getMonthLastDay(int month) {
        if (month >= 1 && month <= 6) {
            return 31;
        }
        return 30;
    }

    public MyDate nextDate() {
        MyDate myDate = new MyDate(month, year, day + 1);
        return myDate;
    }

    private String monthToString() {
        switch (getMonth()) {
            case 1:
                return "Farvardin";
            case 2:
                return "Ordibehesht";
            case 3:
                return "Khordad";
            case 4:
                return "Tir";
            case 5:
                return "Mordad";
            case 6:
                return "Shahrivar";
            case 7:
                return "Mehr";
            case 8:
                return "Aban";
            case 9:
                return "Azar";
            case 10:
                return "Dey";
            case 11:
                return "Bahman";
            case 12:
                return "Esfand";

        }
        return null;
    }

    @Override
    public String toString() {
        return getDay() + " " + monthToString() + " " + getYear();
    }
}
