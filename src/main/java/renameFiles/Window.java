package main.java.renameFiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Window extends JFrame {
    private JPanel panel = new JPanel();
    private JButton actionBtn = new JButton("Rename");
    private JButton btnSelectFile = new JButton("Select Folder");
    private JTextField txtFieldWFilePath = new JTextField();
    private JTextArea txtAreaWResults = new JTextArea();
    private String pathOfSelectedFolder = "";


    public Window(){
        setTitle("Rename Files");
        setMinimumSize(new Dimension(640, 340));
        setMaximumSize(new Dimension(640, 340));
        setResizable(false);

        setLocationRelativeTo(null);
        setVisible(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel.setLayout(null);
        add(panel);
        
        addComponents();
    }    
    
    private void addComponents(){
    	addTitle();
        addSelectionDescription();
        addSelectionTxtField();
        addActionButton();
        addFileChooser();
    }
    
    private void addTitle(){
    	JLabel label1 = new JLabel("Rename files with random numbers");
        label1.setBounds(200,20,300,30);
        label1.setFont(new Font("Arial", 0, 20));
        panel.add(label1);
    }

    private void addSelectionDescription(){     
        String txt = "Select folder with files you want to rename";
        JLabel label2 = new JLabel(txt);
        label2.setBounds(10,60,620,50);
        label2.setFont(new Font("Arial", 0, 15));
        panel.add(label2);

    }
    
    private void addSelectionTxtField(){
    	txtFieldWFilePath.setBounds(10, 100, 475, 30 );
    	txtFieldWFilePath.setText(pathOfSelectedFolder);
        panel.add(txtFieldWFilePath);
        btnSelectFile.setBounds(495, 100, 120, 30 );
        panel.add(btnSelectFile);
    }
    
    private void addActionButton(){
    	actionBtn.setBounds(10, 160, 605, 30 );
        panel.add(actionBtn);
        txtAreaWResults.setBounds(10, 200, 605, 90 );
        txtAreaWResults.setEditable(false);
        panel.add(txtAreaWResults);
    }

    private void addFileChooser(){
        final JFileChooser fileChooser = new JFileChooser();        
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        ActionListener al = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseFiles(fileChooser);
            }
        };

        btnSelectFile.addActionListener(al);
        panel.add(fileChooser);
    }
    
    private void chooseFiles(JFileChooser fileChooser){
    	switch(fileChooser.showSaveDialog(Window.this)){
	        case JFileChooser.APPROVE_OPTION:
	        	pathOfSelectedFolder = fileChooser.getSelectedFile().getPath();
	        	txtFieldWFilePath.setText(pathOfSelectedFolder);
	            break;
	        case JFileChooser.ERROR_OPTION:
	            JOptionPane.showMessageDialog(Window.this, "Error", "ToRename", JOptionPane.OK_OPTION);
	            break;
	    }
    }

    public void setActionBtnListener(final Renamer nj){
        ActionListener ai = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                nj.rename(pathOfSelectedFolder);
            }
        };

        actionBtn.addActionListener(ai);
    }   

    public void printText(String txt){
    	txtAreaWResults.append(txt);
    }
}
