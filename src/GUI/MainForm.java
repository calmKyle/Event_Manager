package GUI;

import General.Repertoire;
import General.Stage;
import General.StageList;
import General.repertoireList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
    private JButton removeStageButton;
    private DefaultTableModel DefaultStage;
    private DefaultTableModel DefaultSchedule;
    private repertoireList repertoireList = new repertoireList();
    private StageList stageList = new StageList();

    private String dataStage = "D:\\New folder\\stage\\src\\Data\\Stage.txt";
    private String dataRepertoire = "D:\\New folder\\stage\\src\\Data\\Repertoire.txt";

    private static JFrame frame;

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
                textField2.setText(StageTable.getValueAt(StageTable.getSelectedRow(),0).toString());
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
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                repertoireList.getList().add(rep);
                repertoireList.stageDistribution(stageList);
                DefaultSchedule.addRow(new Object[] {rep.getID(),rep.getName(),
                        rep.getDay().toString(),rep.getTime().toString(),
                        rep.getCurrentStage().getNameOfStage()});
                frame.setVisible(false);
                try {
                    frame = new MainForm("Stage");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frame.setVisible(true);
            }
        });
        addStageButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Stage stage1 = new Stage();
                try {
                    stage1 = WritetoStage();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                stageList.getStagesList().add(stage1);
                repertoireList.stageDistribution(stageList);
                DefaultStage.addRow(new Object[] {stage1.getID(),stage1.getNameOfStage()});
                frame.setVisible(false);
                try {
                    frame = new MainForm("Stage");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frame.setVisible(true);
            }
        });
        removeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ArrayList<String> save = new ArrayList<String>();
                int idSelect = Integer.parseInt(String.valueOf(scheduleTable.getValueAt(scheduleTable.getSelectedRow(),0)));
                for (int i = 0 ; i < repertoireList.getList().size() ; i++) {
                    if (repertoireList.getList().get(i).getID() != idSelect) {
                        save.add(repertoireList.getList().get(i).toString());
                    }
                }

                FileWriter fileWriter = null;
                BufferedWriter bufferedWriter = null;
                try {
                    fileWriter = new FileWriter(dataRepertoire);
                    bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(save.get(0));
                    bufferedWriter.close();
                    fileWriter.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                for (int i = 1 ; i < save.size() ; i++) {
                    if (save.get(i) != null) {
                        try {
                            fileWriter = new FileWriter(dataRepertoire,true);
                            bufferedWriter = new BufferedWriter(fileWriter);
                            bufferedWriter.newLine();
                            bufferedWriter.write(save.get(i));
                            bufferedWriter.close();
                            fileWriter.close();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    } else {
                        break;
                    }
                }
                frame.setVisible(false);
                try {
                    frame = new MainForm("Stage");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frame.setVisible(true);
            }
        });
        removeStageButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ArrayList<String> save = new ArrayList<String>();
                int idSelect = Integer.parseInt(String.valueOf(StageTable.getValueAt(StageTable.getSelectedRow(),0)));
                for (int i = 0 ; i < stageList.getStagesList().size() ; i++) {
                    if (stageList.getStagesList().get(i).getID() != idSelect) {
                        save.add(stageList.getStagesList().get(i).toString());
                    }
                }

                FileWriter fileWriter = null;
                BufferedWriter bufferedWriter = null;
                try {
                    fileWriter = new FileWriter(dataStage);
                    bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(save.get(0));
                    bufferedWriter.close();
                    fileWriter.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                for (int i = 1 ; i < save.size() ; i++) {
                    if (save.get(i) != null) {
                        try {
                            fileWriter = new FileWriter(dataStage,true);
                            bufferedWriter = new BufferedWriter(fileWriter);
                            bufferedWriter.newLine();
                            bufferedWriter.write(save.get(i));
                            bufferedWriter.close();
                            fileWriter.close();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    } else {
                        break;
                    }
                }
                frame.setVisible(false);
                try {
                    frame = new MainForm("Stage");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frame.setVisible(true);

            }
        });
    }

    public static void main(String[] args) throws IOException {
        frame = new MainForm("Stage");
        frame.setVisible(true);
    }

    private void createStageTable() throws IOException {
        DefaultStage = new DefaultTableModel();
        DefaultStage.addColumn("ID");
        DefaultStage.addColumn("Stage's Name");
        StageTable.setModel(DefaultStage);
        stageList.addFromFile(dataStage);
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
        DefaultSchedule.addColumn("Stage");
        scheduleTable.setModel(DefaultSchedule);
        repertoireList.addFromFile(dataRepertoire);
        repertoireList.stageDistribution(stageList);
        for (int i = 0 ; i < repertoireList.getList().size() ; i++) {
            DefaultSchedule.addRow(new Object[] {repertoireList.getList().get(i).getID(),repertoireList.getList().get(i).getName(),
                    repertoireList.getList().get(i).getDay().toString(),repertoireList.getList().get(i).getTime().toString(),
                    repertoireList.getList().get(i).getCurrentStage().getNameOfStage()});
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
        return a;
    }

    public Repertoire WritetoRepertoire() throws IOException {
        FileWriter fw = new FileWriter(dataRepertoire,true);
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


        public Stage WritetoStage() throws IOException {
        FileWriter fw = new FileWriter(dataStage,true);
        BufferedWriter bw = new BufferedWriter(fw);
        String rep = textField2.getText() + "-" + textField5.getText() + "-" + "0";
        Stage a = new Stage(rep);
        bw.newLine();
        bw.write(rep);
        bw.close();
        fw.close();
        return a;
    }


}
