package General;

public class Time {
    private int Hour;
    private int Minute;
    private int second;


    public Time(int hour, int minute, int second) {
        Hour = hour;
        Minute = minute;
        this.second = second;
    }

    public Time(int second) {
        this.Hour = second/3600;
        this.Minute = (second - this.Hour*3600)/60;
        this.second = second - Hour*3600 - Minute*60;
    }

    public Time(String clock) {
        String txt[] = clock.split(":");
        Hour = Integer.parseInt(txt[0]);
        Minute = Integer.parseInt(txt[1]);
        second = Integer.parseInt(txt[2]);
    }

    public String toString() {
        return Integer.toString(Hour) + ":" + Integer.toString(Minute) + ":" + Integer.toString(second);
    }

    public int getHour() {
        return Hour;
    }

    public void setHour(int hour) {
        Hour = hour;
    }

    public int getMinute() {
        return Minute;
    }

    public void setMinute(int minute) {
        Minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int convertToSecond() {
        return ( Hour*60 + getMinute() ) * 60 + second;
    }

    public void secondToTime(int second) {
        this.Hour = second/3600;
        this.Minute = (second - this.Hour*3600)/60;
        this.second = second - Hour*3600 - Minute*60;
    }


}
