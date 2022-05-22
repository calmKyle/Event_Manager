package General;

import java.util.ArrayList;

public class Stage {
    private int ID;
    private String NameOfStage;
    private int NumberOfrepertoires = 0;
    private Time currentTime = new Time("8:00:00");

    private ArrayList<Repertoire> repertoiresInStage = new ArrayList<Repertoire>();

    public Stage(int ID, String nameOfStage, int numberOfrepertoires) {
        this.ID = ID;
        NameOfStage = nameOfStage;
        NumberOfrepertoires = numberOfrepertoires;
    }

    public Stage() {
    }

    public Stage(String stage) {
        String[] Txt = stage.split("-");
        this.ID = Integer.parseInt(Txt[0]);
        NameOfStage = Txt[1];

        this.NumberOfrepertoires = 0;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNameOfStage() {
        return NameOfStage;
    }

    public void setNameOfStage(String nameOfStage) {
        NameOfStage = nameOfStage;
    }

    public int getNumberOfrepertoires() {
        return NumberOfrepertoires;
    }

    public void setNumberOfrepertoires(int numberOfrepertoires) {
        NumberOfrepertoires = numberOfrepertoires;
    }

    public ArrayList<Repertoire> getRepertoiresInStage() {
        return repertoiresInStage;
    }

    public void setRepertoiresInStage(ArrayList<Repertoire> repertoiresInStage) {
        this.repertoiresInStage = repertoiresInStage;
    }

    public Time getCurrentTime() {
        int time = 28800;
        Time time1 = null;
        if (repertoiresInStage == null) {
            time1 = new Time(8, 0, 0);
        }
        if (repertoiresInStage != null) {
            for (Repertoire repertoire : repertoiresInStage) {
                time = time + repertoire.getTime().convertToSecond();
            }
            time1 = new Time(time / 3600, (time - (time / 3600) * 3600) / 60, time - ((time - (time / 3600) * 3600) / 60) * 60);
        }
        return time1;
    }

    public void addRepertoire(Repertoire repertoire) {
        NumberOfrepertoires += 1;
        repertoiresInStage.add(repertoire);
        repertoire.setCurrentStage(this);
        int second = repertoire.getTime().convertToSecond();
        repertoiresInStage.get(repertoiresInStage.size() - 1).setTime1(new Time(currentTime.convertToSecond() + 600));
        currentTime.secondToTime(currentTime.convertToSecond() + 600 + second);
        if (currentTime.convertToSecond() > 82800) {
            repertoire.setDay(new Day("22/12/2021"));
            currentTime.secondToTime(currentTime.convertToSecond()-86400);
        } else {
            repertoire.setDay(new Day("21/12/2021"));
        }
        repertoire.setSort(true);

    }

    public boolean iSaddRepertoire(Repertoire repertoire) {
        if (repertoiresInStage != null) {
            if (currentTime.convertToSecond() + repertoire.getTime().convertToSecond() + 600 < 169200 && !repertoire.isSort()) {
                return true;
            } else {
                return false;
            }
        }
            return false;

    }

    public boolean isHave(Repertoire repertoire) {
        if (repertoiresInStage.size() != 0) {
            for (Repertoire a : repertoiresInStage) {
                if (a != null) {
                    if (repertoire.getName().equals(a.getName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public String toString() {
        return Integer.toString(ID) + "-" + getNameOfStage() + "-" + "0";
    }
}
