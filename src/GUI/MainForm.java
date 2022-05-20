package GUI;

import General.Repertoire;
import General.Stage;
import General.StageList;
import General.repertoireList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MainForm extends JFrame {
    private JPanel Mainpanel;
    private JButton addButton;
    private JButton removeButton;
    private JTable StageTable;
    private JTable scheduleTable;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton addStageButton;
    private JButton showButton;
    private JTextField textField6;
    private DefaultTableModel DefaultStage;
    private DefaultTableModel DefaultSchedule;
    private repertoireList repertoireList = new repertoireList();
    private StageList stageList = new StageList();

    public MainForm(String title) throws IOException {
        super(title);
        createStageTable();
        createScheduleTable();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Mainpanel);
        this.pack();
        showButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame = null;
                try {
                    frame = new StageView("StageView",getSelect());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frame.setVisible(true);
            }
        });
        StageTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textField5.setText(StageTable.getValueAt(StageTable.getSelectedRow(),1).toString());
            }
        });
        scheduleTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textField1.setText(scheduleTable.getValueAt(scheduleTable.getSelectedRow(),1).toString());
                textField3.setText(scheduleTable.getValueAt(scheduleTable.getSelectedRow(),4).toString());
                textField4.setText(scheduleTable.getValueAt(scheduleTable.getSelectedRow(),3).toString());
                textField6.setText(scheduleTable.getValueAt(scheduleTable.getSelectedRow(),0).toString());
            }
        });
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Repertoire rep = new Repertoire();
                try {
                    rep = WritetoRepertoire();
                    System.out.println(rep.getID());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                repertoireList.getList().add(rep);
                repertoireList.stageDistribution(stageList);
                DefaultSchedule.addRow(new Object[] {rep.getID(),rep.getName(),
                        rep.getDay().toString(),rep.getTime().toString(),
                        rep.getPriority()});

            }
        });
    }

    public static void main(String[] args) throws IOException {
        JFrame frame = new MainForm("Stage");
        frame.setVisible(true);
    }

    private void createStageTable() throws IOException {
        DefaultStage = new DefaultTableModel();
        DefaultStage.addColumn("ID");
        DefaultStage.addColumn("Stage's Name");
        StageTable.setModel(DefaultStage);
        stageList.addFromFile("D:\\stage\\src\\Data\\Stage.txt");
        for (int i = 0 ; i < stageList.numberOfRepertoire() ; i++) {
            DefaultStage.addRow(new Object[] {stageList.getStagesList().get(i).getID(),stageList.getStagesList().get(i).getNameOfStage()});
        }
    }

    private void createScheduleTable() throws IOException {
        DefaultSchedule = new DefaultTableModel();
        DefaultSchedule.addColumn("ID");
        DefaultSchedule.addColumn("Repertoire's Name");
        DefaultSchedule.addColumn("Day");
        DefaultSchedule.addColumn("Time of Repertoire");
        DefaultSchedule.addColumn("Priority");
        scheduleTable.setModel(DefaultSchedule);
        repertoireList.addFromFile("D:\\stage\\src\\Data\\Repertoire.txt");
        repertoireList.stageDistribution(stageList);
        for (int i = 0 ; i < repertoireList.numberOfRepertoire() ; i++) {
            DefaultSchedule.addRow(new Object[] {repertoireList.getList().get(i).getID(),repertoireList.getList().get(i).getName(),
                    repertoireList.getList().get(i).getDay().toString(),repertoireList.getList().get(i).getTime().toString(),
                    repertoireList.getList().get(i).getPriority()});
        }
    }

    public Stage getSelect() {
        Stage a = null;
        for (Stage stage : stageList.getStagesList()) {
            if (stage.getNameOfStage().equals(StageTable.getValueAt(StageTable.getSelectedRow(),1).toString())) {
                a = stage;
                break;
            }
        }
        System.out.println(a.getNameOfStage());
        return a;
    }

    public Repertoire WritetoRepertoire() throws IOException {
        FileWriter fw = new FileWriter("D:\\stage\\src\\Data\\Repertoire.txt",true);
        BufferedWriter bw = new BufferedWriter(fw);
        String rep = textField6.getText() + "-" + textField1.getText() + "-" + "0/0/0" + "-" + textField4.getText() +
                "-" + textField3.getText();
        Repertoire repertoire = new Repertoire(rep);
        bw.newLine();
        bw.write(rep);
        bw.close();
        fw.close();
        return repertoire;
    }
}
