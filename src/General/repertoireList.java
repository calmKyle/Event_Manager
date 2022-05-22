package General;

import java.io.*;
import java.util.ArrayList;

public class repertoireList {
    private ArrayList<Repertoire> list;


    public repertoireList(ArrayList<Repertoire> list) {
        this.list = list;
    }

    public repertoireList() {
        list = new ArrayList<Repertoire>();
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
            list.add(new Repertoire(line));
        }
    }

    public ArrayList<Repertoire> getList() {
        return list;
    }

    public void setList(ArrayList<Repertoire> list) {
        this.list = list;
    }

    public void sortListByPriority() {
        for (int i = 0 ; i < list.size() ; i++) {
            for (int j = i + 1 ; j < list.size() ; j++) {

                    if (list.get(i).getPriority() > list.get(j).getPriority()) {
                        Repertoire repertoire = list.get(j);
                        list.set(j, list.get(i));
                        list.set(i, repertoire);
                    }

            }
        }
    }

    public void sortByDay() {
        for (int i = 0 ; i < list.size() ; i++) {
            for (int j = i + 1 ; j < list.size() ; j++) {
                    if (list.get(i).getDay().getDay() > list.get(j).getDay().getDay()) {
                        Repertoire repertoire = list.get(j);
                        list.set(j, list.get(i));
                        list.set(i, repertoire);
                    }
                }

        }
    }

    public boolean isHave(Repertoire repertoire, ArrayList<Repertoire> list1) {
        for (Repertoire repertoire1 : list1) {
            if (repertoire1 == repertoire) {
                return true;
            }
        }
        return false;
    }

    public void clearAll(StageList stageList) {
        for (Stage stage : stageList.getStagesList()) {
            for (int i = 0 ; i < stage.getRepertoiresInStage().size() ; i++) {
                stage.getRepertoiresInStage().set(i,null);
            }
        }
    }

    public void stageDistribution(StageList stageList) {
        clearAll(stageList);
        sortListByPriority();
        ArrayList<Repertoire> list1 = list;
        sortListByPriority();
        for (int i = 0 ; i < list.size(); i++) {
            if (isHave(list.get(i),list1)) {
                if (!stageList.theSame()) {
                    stageList.getStageAtleast(list.get(i));
                }
                if (stageList.theSame()) {
                    for (Stage stage : stageList.getStagesList()) {
                        if (stage.iSaddRepertoire(list.get(i)) && !stage.isHave(list.get(i)) && !list.get(i).isSort()) {
                            stage.addRepertoire(list.get(i));
                        }
                    }
                }
            }
        }
    }

    public int numberOfRepertoire() {
        return list.size();
    }


}
