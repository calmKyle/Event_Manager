package General;

public class Repertoire {
    private int ID;
    private String Name;

    private Day day;
    private Time time;
    private Time time1;
    private int priority;

    private Stage CurrentStage;

    private boolean Sort;



    public Repertoire() {

    }

    public Repertoire(int ID, String name, Day day, Time time, int priority) {
        this.ID = ID;
        Name = name;
        this.day = new Day("0/0/0");
        this.time = time;
        this.time1 = new Time("0:0:0");
        this.priority = priority;
        CurrentStage = null;
        Sort = false;
    }

    public Repertoire(String read) {
            String[] Txt = read.split("-");
            this.ID = Integer.parseInt(Txt[0]);
            Name = Txt[1];
            this.day = new Day("0/0/0");
            this.time = new Time(Txt[3]);
            this.time1 = new Time("0:0:0");
            this.priority = Integer.parseInt(Txt[4]);
            CurrentStage = null;
            Sort = false;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Stage getCurrentStage() {
        return CurrentStage;
    }

    public void setCurrentStage(Stage currentStage) {
        CurrentStage = currentStage;
    }

    public boolean isSort() {
        return Sort;
    }

    public void setSort(boolean sort) {
        Sort = sort;
    }

    public Time getTime1() {
        return time1;
    }

    public void setTime1(Time time1) {
        this.time1 = time1;
    }

    public String toString() {
        return Integer.toString(ID) + "-" + getName() + "-" + day.toString() + "-" + time.toString() + "-" + Integer.toString(priority);
    }
}
