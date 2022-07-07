package GUI;

//import lib 
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

public class GenrateForm
extend JFrame implements ActionListener{
	private JPanel MainPan1;
	private JButton addButton, removeButton, addstageButton, showButton;
	private JTable stageTable, scheduleTable;
	private JTextField textField1, textField2, textField3, textField4;
	private JList repList, stageList;

	public GenrateForm(String title) {

		super(title);
		setLayout(new GridLayout());

	}
}
