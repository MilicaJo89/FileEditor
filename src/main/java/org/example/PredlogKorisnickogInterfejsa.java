package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class PredlogKorisnickogInterfejsa extends JDialog {
    private JPanel contentPane;
    private JButton buttonClose;
    private JButton buttonSave;

    private JButton buttonOpenTop;
    private JButton buttonOpenBottom;

    private JButton buttonGetSelectionTop;
    private JButton buttonGetSelectionBottom;
    private JTextArea textAreaTop;
    private JTextArea textAreaBottom;
    private JTextArea textAreaNew;
    String directory;
    String selection;

    public PredlogKorisnickogInterfejsa() {
        setContentPane(contentPane);
        setModal(true);
        //    getRootPane().setDefaultButton(buttonOpen);

        buttonClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onButtonClose();
            }
        });


        // call onCancel() when cross is clicked
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onButtonClose();
            }
        });
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PredlogKorisnickogInterfejsa.this.onButtonSave();
            }
        });
        buttonOpenTop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onButtonOpenTop();
            }
        });

        buttonOpenBottom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onButtonOpenBottom();
            }
        });

        buttonGetSelectionTop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onButtonGetSelectionTop();
            }
        });

        buttonGetSelectionBottom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onButtonGetSelectionBottom();
            }
        });

    }

    private void onButtonClose() {
        // add your code here if necessary
        dispose();
    }
    private void onButtonSave() {
        FileDialog f = new FileDialog(this, "Otvori fajl", 1);
        f.setDirectory(this.directory);
        f.setVisible(true);
        this.directory = f.getDirectory();
        this.saveFile(this.directory, f.getFile());
    }

    private void saveFile(String directory, String filename) {
        if (filename != null && filename.length() != 0) {
            FileWriter out = null;
            try {
                File file = new File(directory, filename);
                out = new FileWriter(file);
                this.textAreaNew.getLineCount();
                String s = this.textAreaNew.getText();
                out.write(s);
            } catch (IOException var14) {
                this.textAreaNew.setText(var14.getClass().getName() + ": " + var14.getMessage());
                this.setTitle("FileViewer: " + filename + ": I/O Exception");
            } finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                } catch (IOException var13) {
                }

            }

        }
    }

    private void onButtonOpenTop() {
        FileDialog f = new FileDialog(this, "Otvori fajl", 0);
        f.setDirectory(this.directory);
        f.setVisible(true);
        this.directory = f.getDirectory();
        this.loadAndDisplayFileTop(this.directory, f.getFile());
        f.dispose();
    }

    private void onButtonOpenBottom() {
        FileDialog f = new FileDialog(this, "Otvori fajl", 0);
        f.setDirectory(this.directory);
        f.setVisible(true);
        this.directory = f.getDirectory();
        this.loadAndDisplayFileBotom(this.directory, f.getFile());
        f.dispose();
    }

    private void loadAndDisplayFileTop(String directory, String filename) {
        if (filename != null && filename.length() != 0) {
            FileReader in = null;

            try {
                File file = new File(directory, filename);
                in = new FileReader(file);
                char[] buffer = new char[4096];
                this.textAreaTop.setText("");

                int len;
                while((len = in.read(buffer)) != -1) {
                    String s = new String(buffer, 0, len);
                    this.textAreaTop.append(s);
                }

                this.setTitle("FileViewer: " + filename);
                this.textAreaTop.setCaretPosition(0);
            } catch (IOException var16) {
                this.textAreaTop.setText(var16.getClass().getName() + ": " + var16.getMessage());
                this.setTitle("FileViewer: " + filename + ": I/O Exception");
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException var15) {
                }
            }

        }
    }
    private void loadAndDisplayFileBotom(String directory, String filename) {
        if (filename != null && filename.length() != 0) {
            FileReader in = null;

            try {
                File file = new File(directory, filename);
                in = new FileReader(file);
                char[] buffer = new char[4096];
                this.textAreaBottom.setText("");

                int len;
                while((len = in.read(buffer)) != -1) {
                    String s = new String(buffer, 0, len);
                    this.textAreaBottom.append(s);
                }

                this.setTitle("FileViewer: " + filename);
                this.textAreaBottom.setCaretPosition(0);
            } catch (IOException var16) {
                this.textAreaBottom.setText(var16.getClass().getName() + ": " + var16.getMessage());
                this.setTitle("FileViewer: " + filename + ": I/O Exception");
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException var15) {
                }
            }

        }
    }

    private void onButtonGetSelectionTop(){
        selection = textAreaTop.getSelectedText();
        textAreaNew.append(selection);
    }


    private void onButtonGetSelectionBottom(){
        selection = textAreaBottom.getSelectedText();
        textAreaNew.append(selection);
    }

    public static void main(String[] args) {
        PredlogKorisnickogInterfejsa dialog = new PredlogKorisnickogInterfejsa();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
