import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class TextEditor implements ActionListener {
    JFrame frame;

    JMenuBar menubar;

    JMenu file, edit;

    JMenuItem newfile,openfile,savefile;
    JMenuItem cut,copy,paste,selectall,close;

    JTextArea textarea;
    TextEditor(){

        frame = new JFrame();

        menubar = new JMenuBar();

        textarea = new JTextArea();

        file = new JMenu("File");
        edit = new JMenu("Edit");

        newfile = new JMenuItem("New Window");
        openfile = new JMenuItem("Open File");
        savefile = new JMenuItem("Save File");

        newfile.addActionListener(this);
        openfile.addActionListener(this);
        savefile.addActionListener(this);

        file.add(newfile);
        file.add(openfile);
        file.add(savefile);

        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectall = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectall.addActionListener(this);
        close.addActionListener(this);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectall);
        edit.add(close);

        menubar.add(file);
        menubar.add(edit);

        frame.setJMenuBar(menubar);

        frame.add(textarea);

        frame.setBounds(0,0,400,400);
        frame.setVisible(true);
        frame.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource()==cut){
                textarea.cut();
        }
        if(actionEvent.getSource()==copy){
                textarea.copy();
        }
        if(actionEvent.getSource()==paste){
                textarea.paste();
        }
        if(actionEvent.getSource()==selectall){
                textarea.selectAll();
        }
        if(actionEvent.getSource()==close){
                System.exit(0);
        }
        if(actionEvent.getSource()==openfile){
            JFileChooser fileChooser = new JFileChooser("c:");
            int chooseOption;
            chooseOption = fileChooser.showOpenDialog(null);
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                String filepath = file.getPath();
                try{
                    FileReader fileReader = new FileReader(filepath);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermidiet = " ", output=" ";
                    while((intermidiet= bufferedReader.readLine())!=null){
                        output+=intermidiet+"\n";
                    }
                    textarea.setText(output);
                }
                catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }

        if(actionEvent.getSource()==savefile){
            JFileChooser fileChoosen = new JFileChooser("c:");
            int choosenOption = fileChoosen.showSaveDialog(null);
            if(choosenOption==JFileChooser.APPROVE_OPTION){
                File file = new File(fileChoosen.getSelectedFile().getAbsolutePath()+".txt");
                    try{
                        FileWriter fileWriter = new FileWriter(file);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        textarea.write(bufferedWriter);
                        bufferedWriter.close();
                    }
                    catch(IOException ioException){
                        ioException.printStackTrace();
                    }
            }
        }

    }
    public static void main(String[] args) {
        TextEditor texteditor = new TextEditor();
    }
}