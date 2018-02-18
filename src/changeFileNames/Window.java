package com.changeFileNames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Window extends JFrame {

    private Rename rename;

    private JPanel panel = new JPanel();
    private JButton btnRename = new JButton("Rename");
    private JButton btnSelectFile = new JButton("Select Folder");
    private JTextField txtFieldFilePath = new JTextField();
    private JTextArea txtAreaResults = new JTextArea();
    private String selectedFolderPath = "";


    Window(Rename rename){

        this.rename = rename;

        setTitle("Rename Files");
        setMinimumSize(new Dimension(640, 340));
        setMaximumSize(new Dimension(640, 340));
        setResizable(false);

        setLocationRelativeTo(null);
        setVisible(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ImageIcon img = new ImageIcon("D:\\p\\java\\bomberman\\src\\rsc");
        setIconImage(img.getImage());

        panel.setLayout(null);
        add(panel);

        addWelcomeText();

        addButtons();

        renameBtnListener();

        addFileChooser();
    }

    private void addWelcomeText(){



        JTextArea txtAWelcomeTxt = new JTextArea();
        txtAWelcomeTxt.setBounds(10, 10, 605, 80);
        txtAWelcomeTxt.setBackground(new Color(UIManager.getColor("control").getRGB()));
        txtAWelcomeTxt.setEditable(false);

        //panel.add(txtAWelcomeTxt);
        JLabel label1 = new JLabel("Randomly rename files");
        label1.setBounds(200,20,300,30);
        label1.setFont(new Font("Arial", 0, 20));
        panel.add(label1);

        String txt = "Select folder with files you want to rename";
        JLabel label2 = new JLabel(txt);
        label2.setBounds(10,60,620,50);
        label2.setFont(new Font("Arial", 0, 15));
        panel.add(label2);

    }

    public void printText(String txt){
        txtAreaResults.append(txt);
    }

    private void addButtons(){

        txtFieldFilePath.setBounds(10, 100, 475, 30 );
        txtFieldFilePath.setText(selectedFolderPath);
        panel.add(txtFieldFilePath);
        btnSelectFile.setBounds(495, 100, 120, 30 );
        panel.add(btnSelectFile);

        btnRename.setBounds(10, 160, 605, 30 );
        panel.add(btnRename);
        txtAreaResults.setBounds(10, 200, 605, 90 );
        txtAreaResults.setEditable(false);
        panel.add(txtAreaResults);
    }

    private void addFileChooser(){

        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        ActionListener al = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                switch(fileChooser.showSaveDialog(Window.this)){
                    case JFileChooser.APPROVE_OPTION:
                        selectedFolderPath = fileChooser.getSelectedFile().getPath();
                        txtFieldFilePath.setText(selectedFolderPath);
                        break;
                    case JFileChooser.ERROR_OPTION:
                        JOptionPane.showMessageDialog(Window.this, "Error", "ToRename", JOptionPane.OK_OPTION);
                        break;
                }
            }
        };

        btnSelectFile.addActionListener(al);

        panel.add(fileChooser);
    }

    private void renameBtnListener(){

        Window window =  this;

        ActionListener ai = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                rename.rename(selectedFolderPath, window);
            }
        };

        btnRename.addActionListener(ai);
    }
}
