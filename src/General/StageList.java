package General;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StageList {
    private ArrayList<Stage> stagesList;

    public StageList(ArrayList<Stage> stagesList) {
        this.stagesList = stagesList;
    }

    public StageList() {
        stagesList = new ArrayList<Stage>();
    }

    public ArrayList<Stage> getStagesList() {
        return stagesList;
    }

    public void setStagesList(ArrayList<Stage> stagesList) {
        this.stagesList = stagesList;
    }

    public void AddStage(Stage stage) {
        stagesList.add(stage);
    }

    public void addFromFile(String file) throws IOException {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        while (true) {
            line = br.readLine();
            if (line == null) {
                break;
            }
            stagesList.add(new Stage(line));
        }
    }

    public void getStageAtleast(Repertoire repertoire) {
        Stage stage = stagesList.get(0);
        for (int i = 0; i < stagesList.size(); i++) {
            if (stagesList.get(i).getNumberOfrepertoires() < stage.getNumberOfrepertoires() && stagesList.get(i).iSaddRepertoire(repertoire)) {
                if (!stagesList.get(i).isHave(repertoire)) {
                    stagesList.get(i).addRepertoire(repertoire);
                }
            }
        }
    }

    public boolean theSame() {
        if (stagesList.size() <= 1) {
            return false;
        }
        if (stagesList.size() > 1) {
            for (int i = 0; i < stagesList.size(); i++) {
                if (stagesList.get(i).getNumberOfrepertoires() != stagesList.get(0).getNumberOfrepertoires()) {
                    return false;
                }

            }
        }
        return true;
    }

    public int numberOfRepertoire() {
        return stagesList.size();
    }
}
