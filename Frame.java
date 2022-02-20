/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygoals;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.BorderLayout;
import static java.awt.BorderLayout.CENTER;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import static java.nio.file.Files.size;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import javax.imageio.ImageIO;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.*;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import javax.swing.ButtonGroup;

/**
 *
 * @author user
 */

   
public class Frame extends  JFrame{
   
    
    class MyHighLightPainter extends DefaultHighlighter.DefaultHighlightPainter// DefaultHighLighter.DefaultHighLightPainter 
    {
        
        public MyHighLightPainter(Color color) {
            super(color);
        }
        
    }
    
    
    Highlighter.HighlightPainter highlightPainter = new MyHighLightPainter(Color.yellow); 
    
    public void clean(JTextComponent textComp)
    {
        Highlighter hl = textComp.getHighlighter();
        Highlighter.Highlight [] hlArr= hl.getHighlights();
        for(int i=0;i<hlArr.length;i++)
        {
            if(hlArr[i].getPainter() instanceof MyHighLightPainter)
            {
                hl.removeHighlight(hlArr[i]);
            }
        }
    }
    
    public void Highlight(JTextComponent textComp, String key)
    {
        clean(textComp); // remove highlight
        try{
            Highlighter hl = textComp.getHighlighter();
            Document doc = textComp.getDocument();
            String txt = doc.getText(0, doc.getLength());
            int position =0;
            
            if(txt.toLowerCase().indexOf(key)==-1){
                JOptionPane.showMessageDialog(this, key+"  Not Found !");// if the word not found in file content
            }
            else {
            
            while(position>=0)     // if the word found
            {
                position = txt.toLowerCase().indexOf(key.toLowerCase(), position);
                hl.addHighlight(position, position+key.length(), highlightPainter);
                position+=key.length();
               
            }}
            
            
        }catch(Exception e)
        {
          
            //System.out.println(e.getMessage());
        
        }
     }   
  
    
  private JLabel messageLabel1; // A message to the user
  private JLabel messageLabel2; 
  private JLabel messageLabel3;
  private JTextField year; 
  private JTextField goal;
  private JTextField mysearch;
  private JButton Read; // To select red foreground
  private JButton Write;
  private JButton Search;
  private JButton browse;
  private JRadioButton jRadioButton1;
  private JRadioButton jRadioButton2;
  private JRadioButton jRadioButton3;
  private JTextPane message;
  private JPanel readbuttons;
  private JFileChooser openFileChooser;
  private JLabel back;

 private final int WINDOW_WIDTH = 380; // Window width
 private final int WINDOW_HEIGHT = 500; // Window height
 
 
 /**
 Constructor
 */

 public Frame ()
 {
    
 
 // Set the text for the title bar.
 setTitle("File");

 // Set the size of the window.
 setSize(WINDOW_WIDTH,WINDOW_HEIGHT);

 // Specify an action for the close button.
 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 getContentPane().setBackground(new Color(225, 191, 255));

 // Create a label.
 messageLabel1 = new JLabel("Year ");
 messageLabel2 = new JLabel("Goal ");
 messageLabel3=new JLabel("Search ");
 message=new JTextPane();
 message.setSize(200,200);



  year = new JTextField("Enter year");
  goal = new JTextField("Enter goal");
  mysearch=new JTextField("What want to search ?");
  year.setColumns(20);
  goal.setColumns(20);
  mysearch.setColumns(19);


ImageIcon icon = new ImageIcon("C:\\Users\\user\\Desktop\\file.jpeg");  
JLabel back = new JLabel();
back.setIcon(icon);
Image img = icon.getImage();
Image newimg = img.getScaledInstance(300, 200,  java.awt.Image.SCALE_SMOOTH);
ImageIcon newIcon = new ImageIcon(newimg);
back.setIcon(newIcon);
 Read = new JButton("Read");
 Write = new JButton("Write");
 Search= new JButton("Search");
 browse=new  JButton("browse");
jRadioButton1=new JRadioButton("From Begin !");
jRadioButton2=new JRadioButton("From Current !");
jRadioButton3=new JRadioButton("From End !");
 Read.setBackground(Color.white);
 Write.setBackground(Color.white);
 Search.setBackground(Color.white);
// Add an item listener to the check boxes.
Read.addActionListener(new ButtonListener());
Write.addActionListener(new ButtonListener());
Search.addActionListener(new ButtonListener());
browse.addActionListener(new ButtonListener());



 
// Add a FlowLayout manager to the content pane.

setLayout(new FlowLayout(FlowLayout.CENTER,0, 20));
JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER,20, 5));
JPanel panel4 = new JPanel(new FlowLayout(FlowLayout.CENTER,20, 5));
JPanel panel5=new JPanel(new FlowLayout(FlowLayout.CENTER,20, 5));
JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT,30, 20));
JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER,10, 0));

ButtonGroup bg1 = new ButtonGroup( );

bg1.add(jRadioButton1);
bg1.add(jRadioButton2);
bg1.add(jRadioButton3);
add(panel3);
panel3.add(messageLabel1);
panel3.add(year);
panel3.setBackground(new Color(225, 191, 255));
add(panel4);
panel4.add(messageLabel2);
panel4.add(goal);
panel4.setBackground(new Color(225, 191, 255));
add(panel5);
panel5.add(messageLabel3);
panel5.add(mysearch);
panel5.setBackground(new Color(225, 191, 255));
add(panel);

panel.setBackground(new Color(225, 191, 255));

panel.add(Read);
panel.add(Write);
panel.add(Search);
add(panel2);
panel2.setBackground(new Color(225, 191, 255));
panel2.add(jRadioButton1);
panel2.add(jRadioButton2);
panel2.add(jRadioButton3);
add(message);
message.setVisible(false);
add(back);

// add( browse);



 // Display the window.
 setVisible(true);
 
 }
 

 /**
 Private inner class that handles the event when
 the user clicks one of the check boxes.
 */

 private class  ButtonListener implements ActionListener
 { Scanner x ; 
  File MyFile; 
  String fileName;
  
  
    @Override
 public void  actionPerformed(ActionEvent e){
         
 
   




  
     

 if (e.getSource() == Search)
 {

        String search1=mysearch.getText();
        Highlight(message,search1);


 }



 if(e.getSource()==Read){
     
        JFileChooser fo=new JFileChooser();
        fo.setCurrentDirectory(new java.io.File("C:\\Users"));
        fo.setDialogTitle("hello world");
        fo.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        if(fo.showOpenDialog(browse)==JFileChooser.APPROVE_OPTION){
          MyFile=fo.getSelectedFile();}
          fileName =MyFile.getAbsolutePath();
       
     String data="";
       if(jRadioButton1.isSelected()){
   try{ 
      data=new String(Files.readAllBytes(Paths.get(fileName)));
     
  }catch (IOException  ex){
           JOptionPane.showMessageDialog(null, "error !!");

  }
 data=data.substring(0,data.length());
 
   message.setText(data);
    
 }  else if(jRadioButton2.isSelected()){
  try{ 
      data=new String(Files.readAllBytes(Paths.get(fileName)));
  }catch (IOException  ex){
           JOptionPane.showMessageDialog(null, "error !!");

  }
 data=data.substring(data.length()/2,data.length());
 
   message.setText(data);
} else if(jRadioButton3.isSelected()){
     try{ 
      data=new String(Files.readAllBytes(Paths.get(fileName)));
  }catch (IOException  ex){
           JOptionPane.showMessageDialog(null, "error !!");

  }
 data=data.substring(data.length(),data.length());
 
   message.setText(data);
   
  
}
message.setVisible(true);
 }


 if((e.getSource() == Write))
    
 {
        JFileChooser fo=new JFileChooser();
        fo.setCurrentDirectory(new java.io.File("C:\\Users"));
        fo.setDialogTitle("hello world");
        fo.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        if(fo.showOpenDialog(browse)==JFileChooser.APPROVE_OPTION){
          MyFile=fo.getSelectedFile();}
          fileName =MyFile.getAbsolutePath();
       
        String data =""; 
      
        try { 
            data = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "error !!");
        }
        String data1="";
        String data2="";
        

          FileWriter fw=null;
          String Year=year.getText();
          String Goal=goal.getText();
          String newStr="";
         
         if(jRadioButton1.isSelected())
         {
             
              newStr+=Year+","+Goal+"\n"+data;
             JOptionPane.showMessageDialog(null, "text written at the BEGINNING of the file");
         
         }
         else if(jRadioButton2.isSelected())
         {      
                  if(data.length()<44){
                 data1= data.substring(0, 14);
                 data2= data.substring(14 , data.length());

                }else if (data.length()>44){
               data1= data.substring(0, 44);
               data2= data.substring(44 , data.length());} 
                   // for general situation positioning the code well be 
                  //  data1= data.substring(0, data.length()/2);
                 //   data2= data.substring(data.length()/2 , data.length());
                // but ! the previous code we implemented for particular files, we used it in this project 
               // سابقا بالكود تم تحديد الـبوسشن يدويا ولكن يمكن تعديله كما ذكرت بالكومنت حتى يكون اكثر عموم وشكرا  
                  
              newStr+= data1 +"\n"+Year+","+Goal+data2;
              JOptionPane.showMessageDialog(null, "text written at the CURRENT Position of the file");
         }
         else if(jRadioButton3.isSelected())
         { 
             newStr+= data+Year+","+Goal;
             JOptionPane.showMessageDialog(null, "text written at the END of the file");
         
         }
        try {
            fw = new FileWriter(MyFile.getAbsolutePath());
            for (int i = 0; i < newStr.length(); i++) 
                fw.write(newStr.charAt(i));
        } catch (IOException ex) {
        JOptionPane.showMessageDialog(null, "error !!");
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
               JOptionPane.showMessageDialog(null, "error !!");
            }
        }
    }
}
   


 }
 }

   
