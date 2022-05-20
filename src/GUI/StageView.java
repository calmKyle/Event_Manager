package GUI;

import General.Stage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;

public class StageView extends JFrame{
    private JPanel panel1;
    private JTable stageview;
    private DefaultTableModel DefaultStage;

    public void createStageTable(Stage stage) throws IOException {
        DefaultStage = new DefaultTableModel();
        DefaultStage.addColumn("ID");
        DefaultStage.addColumn("Repertoire's Name");
        DefaultStage.addColumn("Day");
        DefaultStage.addColumn("Time of Repertoire");
        DefaultStage.addColumn("Priority");
        stageview.setModel(DefaultStage);
        addObject(stage);
    }

    public StageView(String title,Stage stage) throws IOException {
        super(title);
        createStageTable(stage);

        this.setContentPane(panel1);
        this.pack();
    }
    
    public void addObject(Stage stage) {
        for (int i = 0 ; i < stage.getRepertoiresInStage().size();i++) {
            DefaultStage.addRow(new Object[] {stage.getRepertoiresInStage().get(i).getID(),stage.getRepertoiresInStage().get(i).getName(),
                    stage.getRepertoiresInStage().get(i).getDay().toString(),stage.getRepertoiresInStage().get(i).getTime().toString(),
                    stage.getRepertoiresInStage().get(i).getPriority()});
        }
    }
}
